package com.sda.ade.petclinic.service.dto;

import com.sda.ade.petclinic.model.Client;

import java.util.Date;

public class PetDto {
    private Long id;
    private String race;
    private Date dateOfBirth;
    private boolean isVaccinated;
    private String ownerName;

    public PetDto() {
    }

    public PetDto(Long id, String race, Date dateOfBirth, boolean isVaccinated, String ownerName) {
        this.id = id;
        this.race = race;
        this.dateOfBirth = dateOfBirth;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "PetDto{" +
                "id=" + id +
                ", race='" + race + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", isVaccinated=" + isVaccinated +
                ", ownerName=" + ownerName +
                '}';
    }
}
