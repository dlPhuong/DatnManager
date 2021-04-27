package com.example.tpbook.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reviewer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameReviewer")
    @Expose
    private String nameReviewer;
    @SerializedName("idGv1")
    @Expose
    private String idGv1;
    @SerializedName("idGv2")
    @Expose
    private String idGv2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameReviewer() {
        return nameReviewer;
    }

    public void setNameReviewer(String nameReviewer) {
        this.nameReviewer = nameReviewer;
    }

    public String getIdGv1() {
        return idGv1;
    }

    public void setIdGv1(String idGv1) {
        this.idGv1 = idGv1;
    }

    public String getIdGv2() {
        return idGv2;
    }

    public void setIdGv2(String idGv2) {
        this.idGv2 = idGv2;
    }

}