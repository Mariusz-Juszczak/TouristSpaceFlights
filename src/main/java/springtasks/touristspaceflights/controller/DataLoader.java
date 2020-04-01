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
        List<Flight> flightList = new ArrayList<>();
        Tourist tourist0 = new Tourist("John", "Kowalsky", "male", "PL", "-", LocalDate.of(1995, 11, 23), flightList );

        Flight flight0 = new Flight(LocalDateTime.of(2020, 5 , 30, 18, 12), LocalDateTime.of(2020, 6, 1, 5, 0), 120, 10.50, touristList );

        touristsRepo.save(tourist0);
        flightsRepo.save(flight0);
    }
}