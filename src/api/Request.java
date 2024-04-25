package api;

import java.io.Serializable;

public class Request<T> implements Serializable {
    private String commandName;
    private T data;
    public Request(String commandName, T data){
        this.commandName = commandName;
        this.data = data;
    }

    public Request(String commandName) {
        this.commandName = commandName;
        this.data = null;
    }

    public String getCommandName() {
        return commandName;
    }

    public T getData() {
        return data;
    }
}