package api;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private T data;
    private String error;

    public String getError() {
        return error;
    }

    public Response(T data, String error){
        this.data = data;
        if(error!=null) this.error = error;
    }

    public T getData() {
        return data;
    }
}
