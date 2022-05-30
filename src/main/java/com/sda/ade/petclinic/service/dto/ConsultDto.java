package com.sda.ade.petclinic.service.dto;

import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.model.Veterinarian;

import javax.persistence.*;
import java.util.Date;

public class ConsultDto {

    private Long id;

    private Date date;

    private String description;

    private Long veterinarianId;

    private Long petId;

    public ConsultDto() {
    }

    public ConsultDto(Long id, Date date, String description, Long veterinarianId, Long petId) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.veterinarianId = veterinarianId;
        this.petId = petId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Long veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @Override
    public String toString() {
        return "ConsultDto{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", veterinarianId=" + veterinarianId +
                ", petId=" + petId +
                '}';
    }
}

