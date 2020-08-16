package com.sb.latest.hibernate.validate.exception;

public class CustomExceptionResponse {

    public String msg;
    public String stack;

    public CustomExceptionResponse(String msg, String stack) {
        this.msg = msg;
        this.stack = stack;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStack() {
        return this.stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
    
}