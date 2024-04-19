package web.kursach.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import web.kursach.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
