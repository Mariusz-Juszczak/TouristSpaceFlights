package springtasks.touristspaceflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springtasks.touristspaceflights.model.Flight;
import springtasks.touristspaceflights.model.Tourist;
import springtasks.touristspaceflights.repository.FlightsRepo;
import springtasks.touristspaceflights.repository.TouristsRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
public class FlightController {

    private TouristsRepo touristsRepo;
    private FlightsRepo flightsRepo;

    @Autowired
    public FlightController (TouristsRepo touristsRepo, FlightsRepo flightsRepo) {
        this.touristsRepo = touristsRepo;
        this.flightsRepo = flightsRepo;
    }

    @GetMapping("/flights")
    public List<Flight> getFlight(
            @RequestParam("dateAndTime") Optional<LocalDateTime> dateTime) {
        if (dateTime.isPresent()) {
            return flightsRepo.findFlightByDepartureDateTime(dateTime.get());
        } else {
            return flightsRepo.findAll();
        }
    }

    @PostMapping("/flights")
    public void addFlight(
            @RequestBody Flight flight) {
        flightsRepo.save(flight);
    }

    @PutMapping("/flights/{id}")
    public void changeFlight(
            @PathVariable ("id") Optional<Long> id, @RequestBody Flight flight) {
        if (id.isPresent()) {
            Flight tempFlight = flightsRepo.findById(id.get()).get();
            tempFlight = flight;
            flight.setId(id.get());
            flightsRepo.save(flight);
        }
    }

    @PatchMapping("/flights/{id}")
    public void changeFlightSettings(
            @PathVariable ("id") Optional<Long> id, @RequestParam ("departureDateTime") Optional<LocalDateTime> departureDateTime, @RequestParam ("arrivalDateTime") Optional<LocalDateTime> arrivalDateTime, @RequestParam ("freeSeats") Optional<Integer> freeSeats , @RequestParam ("ticketPrice") Optional<Double> ticketPrice, @RequestParam ("touristsList") Optional<List<Tourist>> touristList) {
        if (id.isPresent()) {
            Flight editedFlight = flightsRepo.findById(id.get()).get(); {
                if (departureDateTime.isPresent()) { editedFlight.setDepartureDateTime(departureDateTime.get()); }
                if (arrivalDateTime.isPresent()) { editedFlight.setArrivalDateTime(arrivalDateTime.get()); }
                if (freeSeats.isPresent()) { editedFlight.setFreeSeats(freeSeats.get()); }
                if (ticketPrice.isPresent()) { editedFlight.setTicketPrice(ticketPrice.get()); }
                if (touristList.isPresent()) { editedFlight.setTouristList(touristList.get()); }
            } flightsRepo.save(editedFlight);
        }
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(
            @PathVariable("id") Optional<Long>  id) {
        if (id.isPresent()) {
            flightsRepo.deleteById(id.get());
        }
    }
}