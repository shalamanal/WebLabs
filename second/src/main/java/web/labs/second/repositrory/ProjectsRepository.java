package web.labs.second.repositrory;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import web.labs.second.model.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long>{
    
    @Query(value = "SELECT * FROM project p where LOWER(p.project_name) LIKE LOWER('%'|| ?1 || '%') OR LOWER(p.project_description) LIKE LOWER('%'|| ?1 || '%') ", nativeQuery = true)
    List<Projects> findAllWithFilter(String search);


    @Query(value = "SELECT p.project_id, COUNT(t.project_id) " +
    "FROM project p " +
    "LEFT JOIN task t ON t.project_id = p.project_id AND (t.completed = false OR t.completed IS NULL) " +
    "GROUP BY p.project_id", nativeQuery = true)
    List<Object[]> findUnfinishedTaskCount();

}