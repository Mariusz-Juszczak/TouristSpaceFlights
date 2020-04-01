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
    public FlightController(TouristsRepo touristsRepo, FlightsRepo flightsRepo) {
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
            @PathVariable("id") Optional<Long> id, @RequestBody Flight flight) {
        if (id.isPresent()) {
            flight.setId(id.get());
            flightsRepo.save(flight);
        }
    }

    @PatchMapping("/flights/{id}")
    public void changeFlightSettings(
            @PathVariable("id") Optional<Long> id, @RequestParam("departureDateTime") Optional<LocalDateTime> departureDateTime,
            @RequestParam("arrivalDateTime") Optional<LocalDateTime> arrivalDateTime, @RequestParam("freeSeats") Optional<Integer> freeSeats,
            @RequestParam("ticketPrice") Optional<Double> ticketPrice, @RequestBody Optional<List<Long>> touristsIdList) {
        if (id.isPresent()) {
            Flight editedFlight = flightsRepo.findById(id.get()).get();
            {
                if (departureDateTime.isPresent()) {
                    editedFlight.setDepartureDateTime(departureDateTime.get());
                }
                if (arrivalDateTime.isPresent()) {
                    editedFlight.setArrivalDateTime(arrivalDateTime.get());
                }
                if (freeSeats.isPresent()) {
                    editedFlight.setFreeSeats(freeSeats.get());
                }
                if (ticketPrice.isPresent()) {
                    editedFlight.setTicketPrice(ticketPrice.get());
                }
                if (touristsIdList.isPresent()) {
                    for (int i = 0; i < editedFlight.getTouristsList().size(); i++) {
                        Tourist tempTourist = touristsRepo.findById(editedFlight.getTouristsList().get(i).getId()).get();
                        tempTourist.getFlightsList().remove(editedFlight);
                        touristsRepo.save(tempTourist);
                    }
                    editedFlight.getTouristsList().clear();
                    for (int i = 0; i < touristsIdList.get().size(); i++) {
                        Tourist tempTourist = touristsRepo.findById(touristsIdList.get().get(i)).get();
                        editedFlight.getTouristsList().add(tempTourist);
                        tempTourist.getFlightsList().add(editedFlight);
                        touristsRepo.save(tempTourist);
                    }
                }
                flightsRepo.save(editedFlight);
            }
        }
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(
            @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            flightsRepo.deleteById(id.get());
        }
    }
}