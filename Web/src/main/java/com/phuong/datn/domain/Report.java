package com.phuong.datn.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "report")
public class Report extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namereport")
    private String nameReport;

    @Column(name = "mission")
    private String mission;

    @Column(name = "status") // bao gồm inprogress , done, pending
    private String status;

    @Column(name = "process") // từ 0-100%
    private String process;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "idstudent")
    private String idStudent;

    @Column(name = "idtopic")
    private String idTopic;

    @Column(name = "idteacher")
    private String idTeacher;

    @Column(name = "idfile")
    private String idFile;

    @Column(name = "note")
    private String note;

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
