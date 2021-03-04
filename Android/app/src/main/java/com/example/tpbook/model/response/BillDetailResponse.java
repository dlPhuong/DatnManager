package com.example.tpbook.model.response;

import com.example.tpbook.model.data.DetailBill;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillDetailResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<DetailBill> data;
    public BillDetailResponse(String status, String mess, List<DetailBill> data) {
        super(status, mess);
        this.data=data;
    }

    public List<DetailBill> getData() {
        return data;
    }

    public void setData(List<DetailBill> data) {
        this.data = data;
    }
}

