package top.mochoong;

/**
 * @author tsai
 */
public class ThreadUserTraceVariable implements TraceVariable {

    protected ThreadUserTraceVariable() {
    }

    private static final ThreadLocal<DataHolder> VARIABLE = new ThreadLocal<>();

    @Override
    public void initializeContext() {
    }


    @Override
    public void shutdownContext() {
        VARIABLE.remove();
    }

    @Override
    public void set(DataHolder value) {
        VARIABLE.set(value);
    }

    @Override
    public DataHolder get() {
        return VARIABLE.get();
    }
}
