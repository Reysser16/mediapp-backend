package com.mitocode.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TestCaseObservationPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_testcase", nullable = false)
    private TestCase testcase;

    @ManyToOne
    @JoinColumn(name = "id_observation", nullable = false)
    private Observation observation;

    @Override
    public int hashCode() {
        return Objects.hash(testcase, observation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TestCaseObservationPK other = (TestCaseObservationPK) obj;
        return Objects.equals(testcase, other.testcase) && Objects.equals(observation, other.observation);
    }


}
