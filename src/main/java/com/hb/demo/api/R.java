
package com.hb.demo.api;

import java.io.Serializable;

public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private boolean success;
    private T data;
    private String msg;

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }
    private R(int code,  String msg) {
        this.code = code;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }

    public static <T> R<T> success(String msg) {
        return new R(ResultCode.SUCCESS.code, msg);
    }

    public static <T> R<T> success(T t, String msg) {
        return new R(ResultCode.SUCCESS.code,t, msg);
    }

    public static <T> R<T> fail(String msg) {
        return new R(ResultCode.FAILURE.code, msg);
    }

    public int getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", success=" + this.isSuccess() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }

    public R() {
    }
}
