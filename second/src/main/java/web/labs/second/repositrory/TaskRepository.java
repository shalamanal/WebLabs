package web.labs.second.repositrory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import web.labs.second.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByProjectId(Long project_id);

    Task findTaskByProjectIdAndId(Long project_id, Long task_id);
    
    @Modifying
    @Query(value = "DELETE FROM task t WHERE t.project_id = ?1 and completed = ?2", nativeQuery = true)
    void deleteByIdAndCompleted(long projectId, boolean completed);
}
