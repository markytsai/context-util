package top.mochoong;

interface TraceVariable {

    /**
     * 初始化
     */
    void initializeContext();

    /**
     * 关闭
     */
    void shutdownContext();

    /**
     * set
     *
     * @param value
     */
    void set(DataHolder value);

    /**
     * get
     *
     * @return
     */
    DataHolder get();

}
