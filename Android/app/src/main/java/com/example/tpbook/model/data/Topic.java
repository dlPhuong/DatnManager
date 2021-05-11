package com.example.tpbook.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topic {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("topicName")
    @Expose
    private String topicName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("idTeacher")
    @Expose
    private String idTeacher;

    @SerializedName("idStudent")
    @Expose
    private String idStudent;

    public Topic(Integer id, String topicName, String description, String status, String idTeacher, String idStudent) {
        this.id = id;
        this.topicName = topicName;
        this.description = description;
        this.status = status;
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", idTeacher='" + idTeacher + '\'' +
                ", idStudent='" + idStudent + '\'' +
                '}';
    }
}