package com.mitocode.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idObservation;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = true, length = 150)
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

    @Override
    public int hashCode() {
        return Objects.hash(idObservation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Observation other = (Observation) obj;
        return Objects.equals(idObservation, other.idObservation);
    }


}
