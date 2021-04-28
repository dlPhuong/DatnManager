package com.phuong.datn.domain.Response;

public class BaseResponse {
    private String status;
    private String mess;

    public BaseResponse(String status, String mess) {
        this.status = status;
        this.mess = mess;
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
