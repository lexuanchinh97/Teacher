package com.teacher.vn.model;

public class ResponseSignin {
    private Integer status;
    private String message;
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getResult() {
        return data;
    }

    public void setResult(Data result) {
        this.data = result;
    }
}
