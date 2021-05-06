package com.example.tpbook.model.response;

import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Teacher;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ReportResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Report> list;

    public ReportResponse(String status, String mess, List<Report> list) {
        super(status, mess);
        this.list = list;
    }

    public List<Report> getList() {
        return list;
    }

    public void setList(List<Report> list) {
        this.list = list;
    }
}

