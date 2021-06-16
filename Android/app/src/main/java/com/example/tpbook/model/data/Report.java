package com.example.tpbook.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nameReport")
    @Expose
    private String nameReport;
    @SerializedName("mission")
    @Expose
    private String mission;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("process")
    @Expose
    private String process;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("idStudent")
    @Expose
    private String idStudent;
    @SerializedName("idTopic")
    @Expose
    private String idTopic;
    @SerializedName("idTeacher")
    @Expose
    private String idTeacher;
    @SerializedName("idFile")
    @Expose
    private String idFile;
    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("namestudent")
    @Expose
    private String namestudent;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getNamestudent() {
        return namestudent;
    }

    public void setNamestudent(String namestudent) {
        this.namestudent = namestudent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameReport() {
        return nameReport;
    }

    public void setNameReport(String nameReport) {
        this.nameReport = nameReport;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(String idTopic) {
        this.idTopic = idTopic;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", nameReport='" + nameReport + '\'' +
                ", mission='" + mission + '\'' +
                ", status='" + status + '\'' +
                ", process='" + process + '\'' +
                ", deadline='" + deadline + '\'' +
                ", idStudent='" + idStudent + '\'' +
                ", idTopic='" + idTopic + '\'' +
                ", idTeacher='" + idTeacher + '\'' +
                ", idFile='" + idFile + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}