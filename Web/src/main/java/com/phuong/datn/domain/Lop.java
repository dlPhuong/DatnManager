package com.phuong.datn.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lop")
public class Lop extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idclass")
    private String idClass;

    @Column(name = "nameclass")
    private String nameClass;

    public Lop() {
    }

    public Lop(Long id, String idClass, String nameClass) {
        this.id = id;
        this.idClass = idClass;
        this.nameClass = nameClass;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
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
            ", idClass='" + idClass + '\'' +
            ", nameClass='" + nameClass + '\'' +
            '}';
    }
}
