package com.example.tpbook.model.response;

import com.example.tpbook.model.data.Bill;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<Bill> data;
    public BillResponse(String status, String mess, List<Bill> data) {
        super(status, mess);
        this.data=data;
    }

    public List<Bill> getData() {
        return data;
    }

    public void setData(List<Bill> data) {
        this.data = data;
    }
}

