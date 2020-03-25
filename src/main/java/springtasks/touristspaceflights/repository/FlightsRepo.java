package springtasks.touristspaceflights.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springtasks.touristspaceflights.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightsRepo extends CrudRepository<Flight, Long> {

    List<Flight> findAll();

    List<Flight> findFlightByDepartureDateTime(LocalDateTime dateTime);

    Flight save(Flight flight);
}
