package com.mitocode.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TestCaseDTO {

    private Integer idTestcase;
    private TestDTO test;
    private TesterDTO tester;
    private ConditionDTO condition;
    private LocalDateTime date;
    private List<DetailTestcaseDTO> DetailTestcase;

    public Integer getIdTestcase() {
        return idTestcase;
    }

    public void setIdTestcase(Integer idTestcase) {
        this.idTestcase = idTestcase;
    }

    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }

    public TesterDTO getTester() {
        return tester;
    }

    public void setTester(TesterDTO tester) {
        this.tester = tester;
    }

    public ConditionDTO getCondition() {
        return condition;
    }

    public void setCondition(ConditionDTO condition) {
        this.condition = condition;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<DetailTestcaseDTO> getDetailTestcase() {
        return DetailTestcase;
    }

    public void setDetailTestcase(List<DetailTestcaseDTO> detailTestcase) {
        DetailTestcase = detailTestcase;
    }
}
