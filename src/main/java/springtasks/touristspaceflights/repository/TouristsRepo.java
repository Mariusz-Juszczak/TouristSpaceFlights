package springtasks.touristspaceflights.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springtasks.touristspaceflights.model.Tourist;

import java.util.List;

@Repository
public interface TouristsRepo extends CrudRepository<Tourist, Long> {

    List<Tourist> findAll();

    List<Tourist> findTouristByName(String name);

    Tourist save (Tourist tourist);
}

