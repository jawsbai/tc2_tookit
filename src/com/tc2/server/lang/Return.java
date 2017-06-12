package com.tc2.server.lang;

public class Return<T> {
    private int code = 0;
    private T value;

    public Return(int code, T value) {
        this.code = code;
        this.value = value;
    }

    public Return() {
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isSuccess() {
        return code == 0;
    }

    public boolean isError() {
        return code != 0;
    }
}
