package web.kursach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.kursach.model.Month;

@Repository
public interface MonthRepo extends JpaRepository<Month, Long>{

}
