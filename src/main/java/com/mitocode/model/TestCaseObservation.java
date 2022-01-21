package com.mitocode.model;

import javax.persistence.*;

@Entity
@Table(name = "testcase_observation")
@IdClass(TestCaseObservationPK.class)
public class TestCaseObservation {

    @Id
    @ManyToOne(optional = false)
    private TestCase testcase;

    @Id
    @ManyToOne(optional = false)
    private Observation observation;

    public TestCase getTestcase() {
        return testcase;
    }

    public void setTestcase(TestCase testcase) {
        this.testcase = testcase;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }
}
