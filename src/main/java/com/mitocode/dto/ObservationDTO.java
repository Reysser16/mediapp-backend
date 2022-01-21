package com.mitocode.dto;

public class ObservationDTO {

    private Integer idObservation;
    private String name;
    private String description;

    public Integer getIdObservation() {
        return idObservation;
    }

    public void setIdObservation(Integer idObservation) {
        this.idObservation = idObservation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
