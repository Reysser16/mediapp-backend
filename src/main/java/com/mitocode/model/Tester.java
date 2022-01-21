package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tester")
public class Tester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTester;

    @Column(name = "names", nullable = false, length = 70)
    private String names;

    @Column(name = "last_name", nullable = false, length = 70)
    private String lastName;

    @Column(name = "code", nullable = false, length = 6, unique = true)
    private String code;

    public Integer getIdTester() {
        return idTester;
    }

    public void setIdTester(Integer idTester) {
        this.idTester = idTester;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
