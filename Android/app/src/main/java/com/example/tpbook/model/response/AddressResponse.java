package com.example.tpbook.model.response;

import com.example.tpbook.model.data.Address;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<Address> data;

    public AddressResponse(String status, String mess, List<Address> data) {
        super(status, mess);
        this.data=data;
    }

    public List<Address> getData() {
        return data;
    }

    public void setData(List<Address> data) {
        this.data = data;
    }
}

