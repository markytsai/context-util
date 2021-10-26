package top.mochoong;

import org.springframework.util.ClassUtils;

import java.util.Optional;

/**
 * @author tsai
 */
public class UserTraceContext<T> {
    private UserTraceContext() {
    }

    private static final TraceVariable VARIABLE;

    static {
        boolean importHystrix = ClassUtils.isPresent("com.netflix.hystrix.HystrixCommand", null) && ClassUtils
                .isPresent("feign.hystrix.HystrixFeign", null);
        if (importHystrix) {
            VARIABLE = new HystrixUserTraceVariable();
        } else {
            VARIABLE = new ThreadUserTraceVariable();
        }
    }

    public static void initializeContext() {
        VARIABLE.initializeContext();
    }

    public static void shutdownContext() {
        VARIABLE.shutdownContext();
    }

    static void set(DataHolder value) {
        VARIABLE.set(value);
    }

    public static <T> void setData(T data) {
        DataHolder tracing = Optional.ofNullable(get()).orElse(new DataHolder());
        tracing.setData(data);
        set(tracing);
    }

    public static <T> T getData() {
        return (T) Optional.ofNullable(get()).map(DataHolder::getData).orElse(null);
    }

    public static DataHolder get() {
        return VARIABLE.get();
    }

}
