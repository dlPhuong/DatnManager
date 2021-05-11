package com.example.tpbook.model.response;

import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Teacher;
import com.example.tpbook.model.data.Topic;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ReportResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Topic> list;

    public ReportResponse(String status, String mess, List<Topic> list) {
        super(status, mess);
        this.list = list;
    }

    public List<Topic> getList() {
        return list;
    }

    public void setList(List<Topic> list) {
        this.list = list;
    }
}

