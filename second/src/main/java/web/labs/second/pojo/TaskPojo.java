package web.labs.second.pojo;

import lombok.Data;
import web.labs.second.model.Projects;
import web.labs.second.model.Task;

import java.sql.Date;


@Data
public class TaskPojo {
    private Long id;
    private String name;
    private String description;
    private Date dueDate;
    private boolean completed;
    private Long projectId;

    // геттеры и сеттеры

    public static TaskPojo fromEntity(Task task) {
        TaskPojo taskPojo = new TaskPojo();
        taskPojo.setId(task.getId());
        taskPojo.setName(task.getName());
        taskPojo.setDescription(task.getDescription());
        taskPojo.setDueDate(task.getDueDate());
        taskPojo.setCompleted(task.isCompleted());
        if (task.getProject() != null) {
            taskPojo.setProjectId(task.getProject().getId());
        }
        return taskPojo;
    }

    public static Task toEntity(TaskPojo taskPojo) {
        Task task = new Task();
        task.setId(taskPojo.getId());
        task.setName(taskPojo.getName());
        task.setDescription(taskPojo.getDescription());
        task.setDueDate(taskPojo.getDueDate());
        task.setCompleted(taskPojo.isCompleted());
    
    // Устанавливаем project только если projectId не null
        if (taskPojo.getProjectId() != null) {
            Projects project = new Projects();
            project.setId(taskPojo.getProjectId());
            task.setProject(project);
        }
        return task;
    }
}

