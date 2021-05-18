package com.phuong.datn.service.dto;

import com.phuong.datn.domain.File;
import com.phuong.datn.domain.Report;
import com.phuong.datn.domain.Student;
import com.phuong.datn.domain.Teacher;

import javax.persistence.Column;

public class ReportDTO {
    private Long id;

    private String nameReport;

    private String mission;

    private String status;

    private String process;

    private String deadline;

    private String idStudent;

    private String idTopic;

    private String idTeacher;

    private String idFile;

    private String note;
        // dtos
    private String filename;

    private String namestudent;

    public ReportDTO() {
    }

    public ReportDTO(Report report, Student student, File filedto) {
        this.id = report.getId();
        this.nameReport = report.getNameReport();
        this.mission = report.getMission();
        this.status = report.getStatus();
        this.process = report.getProcess();
        this.deadline = report.getDeadline();
        this.idStudent = report.getIdStudent();
        this.idTopic = report.getIdTopic();
        this.idTeacher = report.getIdTeacher();
        this.idFile = report.getIdFile();
        this.note = report.getNote();
        this.filename = filedto.getFilename();
        this.namestudent = student.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "ReportDTO{" +
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
            ", filename='" + filename + '\'' +
            ", namestudent='" + namestudent + '\'' +
            '}';
    }
}
