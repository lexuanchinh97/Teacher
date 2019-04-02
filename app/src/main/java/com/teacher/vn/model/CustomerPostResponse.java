package com.teacher.vn.model;

public class CustomerPostResponse {
    private Integer status;
    private String message;
    private CustomerPost data;

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

    public CustomerPost getData() {
        return data;
    }

    public void setData(CustomerPost data) {
        this.data = data;
    }
}
