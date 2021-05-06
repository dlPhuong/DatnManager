package com.example.tpbook.model.response;

import com.example.tpbook.model.data.Report;
import com.example.tpbook.model.data.Teacher;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherResponse extends BaseResponse {

    @SerializedName("data")
    @Expose
    private List<Teacher> list;

    public List<Teacher> getList() {
        return list;
    }

    public void setList(List<Teacher> list) {
        this.list = list;
    }

    public TeacherResponse(String status, String mess, List<Teacher> list) {
        super(status, mess);
        this.list = list;
    }
}

