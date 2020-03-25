package springtasks.touristspaceflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import springtasks.touristspaceflights.model.Flight;
import springtasks.touristspaceflights.model.Tourist;
import springtasks.touristspaceflights.repository.FlightsRepo;
import springtasks.touristspaceflights.repository.TouristsRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private TouristsRepo touristsRepo;
    private FlightsRepo flightsRepo;

    @Autowired
    public DataLoader(TouristsRepo touristsRepo, FlightsRepo flightsRepo) {
        this.flightsRepo = flightsRepo;
        this.touristsRepo = touristsRepo;
    }

    @Override
    public void run(ApplicationArguments args) {

        List<Tourist> touristList = new ArrayList<>();
        Tourist tourist0 = new Tourist("Andrzej", "Dębowy", "mężczyzna", "PL", "nie lubi brata", LocalDate.of(1995, 11, 23), null);
        touristList.add(tourist0);

        List<Flight> flightList = new ArrayList<>();
        Flight flight0 = new Flight(LocalDateTime.of(2020, 05 , 30, 18, 12), LocalDateTime.of(2020, 06, 01, 5, 00), 120, 10.50, touristList);

        touristsRepo.save(tourist0);
        flightsRepo.save(flight0);
    }
}