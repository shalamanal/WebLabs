package web.kursach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.kursach.model.Kpi;

@Repository
public interface KpiRepo extends JpaRepository<Kpi, Long> {

}
