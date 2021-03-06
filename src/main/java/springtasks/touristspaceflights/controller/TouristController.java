package springtasks.touristspaceflights.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springtasks.touristspaceflights.model.Flight;
import springtasks.touristspaceflights.model.Tourist;
import springtasks.touristspaceflights.repository.FlightsRepo;
import springtasks.touristspaceflights.repository.TouristsRepo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class TouristController {

    private TouristsRepo touristsRepo;
    private FlightsRepo flightsRepo;

    @Autowired
    public TouristController(TouristsRepo touristsRepo, FlightsRepo flightsRepo) {
        this.touristsRepo = touristsRepo;
        this.flightsRepo = flightsRepo;
    }

    @GetMapping("/tourists")
    public List<Tourist> getTourist(
            @RequestParam("name") Optional<String> name) {
        if (name.isPresent()) {
            return touristsRepo.findTouristByName(name.get());
        } else {
            return touristsRepo.findAll();
        }
    }

    @PostMapping("/tourists")
    public void addTourist(
            @RequestBody Tourist tourist) {
        touristsRepo.save(tourist);
    }

    @PutMapping("/tourists/{id}")
    public void changeTourist(
            @PathVariable("id") Optional<Long> id, @RequestBody Tourist tourist) {
        if (id.isPresent()) {
            tourist.setId(id.get());
            touristsRepo.save(tourist);
        }
    }

    @PatchMapping("/tourists/{id}")
    public void changeTouristSettings(
            @PathVariable("id") Optional<Long> id, @RequestParam("name") Optional<String> name, @RequestParam("lastName") Optional<String> lastName,
            @RequestParam("gender") Optional<String> gender, @RequestParam("country") Optional<String> country, @RequestParam("notes") Optional<String> notes,
            @RequestParam("dateOfBirth") Optional<LocalDate> dateOfBirth, @RequestBody Optional<List<Long>> flightIdList) {
        if (id.isPresent()) {
            Tourist editedTourist = touristsRepo.findById(id.get()).get();
            {
                if (name.isPresent()) {
                    editedTourist.setName(name.get());
                }
                if (lastName.isPresent()) {
                    editedTourist.setLastName(lastName.get());
                }
                if (gender.isPresent()) {
                    editedTourist.setGender(gender.get());
                }
                if (country.isPresent()) {
                    editedTourist.setCountry(country.get());
                }
                if (notes.isPresent()) {
                    editedTourist.setNotes(notes.get());
                }
                if (dateOfBirth.isPresent()) {
                    editedTourist.setDateOfBirth(dateOfBirth.get());
                }
                if (flightIdList.isPresent()) {
                    for (int i = 0; i < editedTourist.getFlightsList().size(); i++) {
                        Flight tempFlight = flightsRepo.findById(editedTourist.getFlightsList().get(i).getId()).get();
                        tempFlight.getTouristsList().remove(editedTourist);
                        flightsRepo.save(tempFlight);
                    }
                    editedTourist.getFlightsList().clear();
                    for (int i = 0; i < flightIdList.get().size(); i++) {
                        Flight tempFlight = flightsRepo.findById(flightIdList.get().get(i)).get();
                        editedTourist.getFlightsList().add(tempFlight);
                        tempFlight.getTouristsList().add(editedTourist);
                        flightsRepo.save(tempFlight);
                    }
                }
                touristsRepo.save(editedTourist);
            }
        }
    }


    @DeleteMapping("/tourists/{id}")
    public void deleteTourist(
            @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            touristsRepo.deleteById(id.get());
        }
    }
}