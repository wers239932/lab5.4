package api;

import java.io.Serializable;
import java.io.StringReader;

public class Response<T> implements Serializable {
    private T data;
    private String error;
    private String requestStatus;

    public String getError() {
        return error;
    }

    public Response(T data, String status, String error){
        this.data = data;
        this.requestStatus = status;
        if(error!=null) this.error = error;
    }
    public Response(String status, String error)
    {
        this.requestStatus = status;
        this.error = error;
    }

    public T getData() {
        return data;
    }
    public String getRequestStatus() {
        return this.requestStatus;
    }
}
