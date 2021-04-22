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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
