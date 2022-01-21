package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "testcase")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTestcase;

    @ManyToOne
    @JoinColumn(name = "id_test", nullable = false, foreignKey = @ForeignKey(name = "FK_testcase_test"))
    private Test test;

    @ManyToOne
    @JoinColumn(name = "id_tester", nullable = false, foreignKey = @ForeignKey(name = "FK_testcase_tester"))
    private Tester tester;

    @ManyToOne
    @JoinColumn(name = "id_condition", nullable = false, foreignKey = @ForeignKey(name = "FK_testcase_condition"))
    private Condition condition;

    @Column(name = "date", nullable = false)
    private LocalDateTime date; // ISODate yyyy-mm-ddTHH:mm:ss
    // Spring boot 1.5 -> pom.xml jsr310 UTC

    @OneToMany(mappedBy = "testcase", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DetailTestcase> detailTestcase;

    public Integer getIdTestcase() {
        return idTestcase;
    }

    public void setIdTestcase(Integer idTestcase) {
        this.idTestcase = idTestcase;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Tester getTester() {
        return tester;
    }

    public void setTester(Tester tester) {
        this.tester = tester;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<DetailTestcase> getDetailTestcase() {
        return detailTestcase;
    }

    public void setDetailTestcase(List<DetailTestcase> detailTestcase) {
        this.detailTestcase = detailTestcase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTestcase);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestCase other = (TestCase) obj;
        return Objects.equals(idTestcase, other.idTestcase);
    }


}
