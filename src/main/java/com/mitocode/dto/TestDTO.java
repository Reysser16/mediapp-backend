package com.mitocode.dto;

import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Test DATA")
public class TestDTO {

    private Integer idTest;

    @Schema(description = "given test case")
    @Size(min = 3, message = "{given.size}")
    private String given;

    @Schema(description = "when test case")
    @Size(min = 3, message = "{when.size}")
    private String when;

    @Schema(description = "then test case")
    @Size(min = 3, message = "{then.size}")
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
