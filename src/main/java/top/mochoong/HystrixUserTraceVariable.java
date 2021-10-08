package top.mochoong;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

/**
 * @author tsai
 */
public class HystrixUserTraceVariable implements TraceVariable {

    protected HystrixUserTraceVariable() {
    }

    private static final HystrixRequestVariableDefault<DataHolder> VARIABLE = new HystrixRequestVariableDefault<>();

    @Override
    public void initializeContext() {
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }
    }

    @Override
    public void shutdownContext() {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }

    @Override
    public void set(DataHolder value) {
        VARIABLE.set(value);
    }

    @Override
    public DataHolder get() {
        try {
            return VARIABLE.get();
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
