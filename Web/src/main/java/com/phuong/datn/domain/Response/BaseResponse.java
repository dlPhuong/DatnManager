package com.phuong.datn.domain.Response;

public class BaseResponse<O> {
    private String status;
    private String mess;
    private O data;

    public BaseResponse(String status, String mess, O data) {
        this.status = status;
        this.mess = mess;
        this.data = data;
    }

    public O getData() {
        return data;
    }

    public void setData(O data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
