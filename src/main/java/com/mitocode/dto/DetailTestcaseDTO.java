package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DetailTestcaseDTO {

    private Integer idDetailTestcase;
    @JsonIgnore
    private TestCaseDTO testCaseDTO;
    private String observation;


    public Integer getIdDetailTestcase() {
        return idDetailTestcase;
    }

    public void setIdDetailTestcase(Integer idDetailTestcase) {
        this.idDetailTestcase = idDetailTestcase;
    }

    public TestCaseDTO getTestCaseDTO() {
        return testCaseDTO;
    }

    public void setTestCaseDTO(TestCaseDTO testCaseDTO) {
        this.testCaseDTO = testCaseDTO;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
