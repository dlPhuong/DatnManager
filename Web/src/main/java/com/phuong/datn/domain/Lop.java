package com.phuong.datn.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lop")
public class Lop extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public Lop(Long id, String nameClass) {
        this.id = id;
        this.nameClass = nameClass;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameclass")
    private String nameClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            ", nameClass='" + nameClass + '\'' +
            '}';
    }
}
