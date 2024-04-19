package web.labs.second.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import web.labs.second.pojo.TaskPojo;
import web.labs.second.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/projects")
public class TaskContoller {


    @Autowired
    private  TaskService taskService;

    @GetMapping("/{projectID}/tasks")
    public ResponseEntity<List<TaskPojo>> getAllTasksForProject(@PathVariable Long projectID){
        List<TaskPojo> tasks = taskService.findAll(projectID);
        if (tasks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<TaskPojo>>(tasks, HttpStatus.OK);
    }

    @GetMapping("{projectID}/tasks/{taskID}")
    public ResponseEntity<TaskPojo> getTaskByProjectIDAndTaskId(@PathVariable Long projectID, @PathVariable Long taskID){
        TaskPojo task = taskService.findByProjectAndTaskId(projectID, taskID);
        if (task == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<TaskPojo>(task,HttpStatus.OK);
    }

    @PostMapping("/{projectID}/tasks")
    public ResponseEntity<TaskPojo> createTask(@PathVariable Long projectID, @RequestBody TaskPojo taskPojo) {
        TaskPojo createdTask = taskService.createTask(projectID, taskPojo);
        if (createdTask == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{projectID}/tasks/{taskID}")
    public ResponseEntity<TaskPojo> updateTask(@PathVariable Long projectID, @PathVariable Long taskID, @RequestBody TaskPojo taskPojo) {
        TaskPojo updatedTask = taskService.updateTask(projectID, taskID, taskPojo);
        if (updatedTask == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    @DeleteMapping("/{projectID}/tasks/{taskID}")
    public ResponseEntity<?> deleteTask(@PathVariable Long projectID, @PathVariable Long taskID){
        boolean isDeleted = taskService.deleteTask(projectID, taskID);
        if (isDeleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{projectId}/tasks")
    public ResponseEntity<?> deleteCompleted(@PathVariable("projectId") long projectId){
        taskService.deleteCompletedTask(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
