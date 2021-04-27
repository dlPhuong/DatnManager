package com.example.tpbook.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lop {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameClass")
    @Expose
    private String nameClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    @Override
    public String toString() {
        return "Lop{" +
                "id=" + id +
                ", nameClass='" + nameClass + '\'' +
                '}';
    }
}