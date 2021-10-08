package top.mochoong;



/**
 * @author tsai
 */
public class DataHolder<T> {

    private T data;

    public T getData() {
        return data;
    }

    protected void setData(T user) {
        this.data = user;
    }

}
