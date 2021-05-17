package com.phuong.datn.domain;

import com.phuong.datn.service.dto.UserDTO;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "student")
public class Student extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "masinhvien")
    private String maSinhVien;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private String birthDay;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "idclass")
    private String idClass;

    @Column(name = "idteacher")
    private String idTeacher;

    @Column(name = "idtopic")
    private String idTopic;

    @Column(name = "image")
    private String image;

    @Column(name = "linkgithub")
    private String linkGithub;

    @Column(name = "idpb")
    private String idPb;

    @Column(name = "status")
    private String status;

    @Column(name = "note")
    private String note;

    @Column(name = "iduserauth")
    private Long idUserAuth;

    public Student() {
    }

    public Student(UserDTO dto) {
        this.idUserAuth = dto.getId();
        this.name = dto.getLastName();
        this.image = dto.getImageUrl();
    }

    public Long getIdUserAuth() {
        return idUserAuth;
    }

    public void setIdUserAuth(Long idUserAuth) {
        this.idUserAuth = idUserAuth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
