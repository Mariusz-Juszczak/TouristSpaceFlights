package springtasks.touristspaceflights.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Tourist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String lastName;
    private String gender;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;

    @OneToMany
    private List<Flight> flightsList;

    public Tourist() {
    }

    public Tourist(String name, String lastName, String gender, String country, String notes, LocalDate dateOfBirth, List<Flight> flightsList) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
        this.notes = notes;
        this.dateOfBirth = dateOfBirth;
        this.flightsList = flightsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Flight> getFlightsList() {
        return flightsList;
    }

    public void setFlightsList(List<Flight> flightsList) {
        this.flightsList = flightsList;
    }
}