package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "test") //schema = "protected"
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTest;

    @Size(min = 3, message = "{given.size}")
    @Column(name = "test_given", nullable = false, length = 400)
    private String given;

    @Size(min = 3, message = "{when.size}")
    @Column(name = "test_when", nullable = false, length = 400)
    private String when;

    @Size(min = 3, message = "{then.size}")
    @Column(name = "test_then", nullable = false, length = 400)
    private String then;

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getThen() {
        return then;
    }

    public void setThen(String then) {
        this.then = then;
    }
}
