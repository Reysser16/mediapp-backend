package com.mitocode.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class TestCaseObservationDTO {

    @NotNull
    private TestCaseDTO testcase;

    @NotNull
    private List<ObservationDTO> lstObservation;

    public TestCaseDTO getTestcase() {
        return testcase;
    }

    public void setTestcase(TestCaseDTO testcase) {
        this.testcase = testcase;
    }

    public List<ObservationDTO> getLstObservation() {
        return lstObservation;
    }

    public void setLstObservation(List<ObservationDTO> lstObservation) {
        this.lstObservation = lstObservation;
    }
}
