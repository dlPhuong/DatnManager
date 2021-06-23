package com.phuong.datn.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviewer")
public class Reviewer extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "namereviewer")
    private String nameReviewer;

    @Column(name = "idgv1")
    private String idGv1;

    @Column(name = "idgv2")
    private String idGv2;


    @Column(name = "sttgv1")
    private String sttGv1;

    @Column(name = "sttgv2")
    private String sttGv2;

    private String nameGv1;

    private String nameGv2;

    private String imageGv1;

    private String imageGv2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSttGv1() {
        return sttGv1;
    }

    public void setSttGv1(String sttGv1) {
        this.sttGv1 = sttGv1;
    }

    public String getSttGv2() {
        return sttGv2;
    }

    public void setSttGv2(String sttGv2) {
        this.sttGv2 = sttGv2;
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

    public String getNameGv1() {
        return nameGv1;
    }

    public void setNameGv1(String nameGv1) {
        this.nameGv1 = nameGv1;
    }

    public String getNameGv2() {
        return nameGv2;
    }

    public void setNameGv2(String nameGv2) {
        this.nameGv2 = nameGv2;
    }

    public String getImageGv1() {
        return imageGv1;
    }

    public void setImageGv1(String imageGv1) {
        this.imageGv1 = imageGv1;
    }

    public String getImageGv2() {
        return imageGv2;
    }

    public void setImageGv2(String imageGv2) {
        this.imageGv2 = imageGv2;
    }

    @Override
    public String toString() {
        return "Reviewer{" +
            "id=" + id +
            ", nameReviewer='" + nameReviewer + '\'' +
            ", idGv1='" + idGv1 + '\'' +
            ", idGv2='" + idGv2 + '\'' +
            '}';
    }
}
