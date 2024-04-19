package web.kursach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.kursach.model.Day;

@Repository
public interface DayRepo extends JpaRepository<Day, Long> {

}
