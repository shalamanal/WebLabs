package web.labs.second.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import web.labs.second.model.Projects;
import web.labs.second.model.Task;
import web.labs.second.pojo.TaskPojo;
import web.labs.second.repositrory.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
   

    public List<TaskPojo> findAll(Long projectID){
        return taskRepository.findByProjectId(projectID).stream()
                .map(TaskPojo::fromEntity).collect(Collectors.toList());
    }

    public TaskPojo findByProjectAndTaskId(Long projectID, Long taskID){
        Task task = taskRepository.findTaskByProjectIdAndId(projectID, taskID);
        if (task == null) 
            return null;
        return TaskPojo.fromEntity(task);
    }

    public TaskPojo createTask(Long projectID, TaskPojo taskPojo){
        try {
            //Преобразуем pojo 
            Task task = TaskPojo.toEntity(taskPojo);
    
            //Устанавливаем ID
            Projects project = new Projects();
            project.setId(projectID);
            task.setProject(project);
             //Создаём task
            Task savedTask = taskRepository.save(task);
            return TaskPojo.fromEntity(savedTask);
        } catch (Exception e) {
            return null;
        }
    }

    public TaskPojo updateTask(Long projectID, Long taskID, TaskPojo taskPojo) {
        // Проверяем существует ли такая таска с заданными id 
        Task existingTask = taskRepository.findTaskByProjectIdAndId(projectID, taskID);
        if (existingTask == null)
            return null;      

        // Обновляем поля задачи
        existingTask.setName(taskPojo.getName());
        existingTask.setDescription(taskPojo.getDescription());
        existingTask.setCompleted(taskPojo.isCompleted());
        existingTask.setDueDate(taskPojo.getDueDate());

        // Сохраняем обновленную задачу в базе данных
        Task savedTask = taskRepository.save(existingTask);

        return TaskPojo.fromEntity(savedTask);
    }

    public boolean deleteTask(Long projectID, Long taskID){
        Task task = taskRepository.findTaskByProjectIdAndId(projectID, taskID);
        if(task != null){
            taskRepository.delete(task);
            return true;
        }
        else 
            return false;
    }

    @Transactional
    public void deleteCompletedTask(long projectId) {
        taskRepository.deleteByIdAndCompleted(projectId, true);
    }
}
