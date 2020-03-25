package springtasks.touristspaceflights.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany
    private List<Tourist> touristList;

    public Flight() {
    }

    public Flight(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int freeSeats, double ticketPrice, List<Tourist> touristList) {
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.freeSeats = freeSeats;
        this.ticketPrice = ticketPrice;
        this.touristList = touristList;
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

    public List<Tourist> getTouristList() {
        return touristList;
    }

    public void setTouristList(List<Tourist> touristList) {
        this.touristList = touristList;
    }
}