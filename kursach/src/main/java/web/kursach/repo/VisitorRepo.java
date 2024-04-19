package web.kursach.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import web.kursach.model.Visitor;

public interface VisitorRepo extends JpaRepository<Visitor, Long>{

}
