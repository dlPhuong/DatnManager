package com.example.tpbook.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birthDay")
    @Expose
    private String birthDay;
    @SerializedName("maSinhVien")
    @Expose
    private String maSinhVien;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("idClass")
    @Expose
    private String idClass;
    @SerializedName("idTeacher")
    @Expose
    private String idTeacher;
    @SerializedName("idTopic")
    @Expose
    private String idTopic;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("linkGithub")
    @Expose
    private String linkGithub;
    @SerializedName("idPb")
    @Expose
    private String idPb;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("idUserAuth")
    @Expose
    private String idUserAuth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLinkGithub() {
        return linkGithub;
    }

    public void setLinkGithub(String linkGithub) {
        this.linkGithub = linkGithub;
    }

    public String getIdPb() {
        return idPb;
    }

    public void setIdPb(String idPb) {
        this.idPb = idPb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getIdUserAuth() {
        return idUserAuth;
    }

    public void setIdUserAuth(String idUserAuth) {
        this.idUserAuth = idUserAuth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", idClass='" + idClass + '\'' +
                ", idTeacher='" + idTeacher + '\'' +
                ", idTopic='" + idTopic + '\'' +
                ", image='" + image + '\'' +
                ", linkGithub='" + linkGithub + '\'' +
                ", idPb='" + idPb + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}