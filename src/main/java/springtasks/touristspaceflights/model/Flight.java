package springtasks.touristspaceflights.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Flight {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int freeSeats;
    private double ticketPrice;

    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany
    private List<Tourist> touristsList;

    public Flight() {
    }

    public Flight(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int freeSeats, double ticketPrice, List<Tourist> touristsList) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.freeSeats = freeSeats;
        this.ticketPrice = ticketPrice;
        this.touristsList = touristsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<Tourist> getTouristsList() {
        return touristsList;
    }

    public void setTouristsList(List<Tourist> touristsList) {
        this.touristsList = touristsList;
    }
}