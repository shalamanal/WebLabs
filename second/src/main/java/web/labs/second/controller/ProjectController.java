package web.labs.second.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.labs.second.pojo.ProjectsPojo;
import web.labs.second.service.ProjectsService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectsService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectsPojo>> getProjects(@RequestParam(required = false) String search) {
        List<ProjectsPojo> projects = projectService.getAllProjects(search);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectID}")
    public ResponseEntity<ProjectsPojo> getProjectById(@PathVariable Long projectID){
        ProjectsPojo project = projectService.getProjectById(projectID);
        if  (project == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectsPojo> createProject(@RequestBody ProjectsPojo projectsPojo){
        ProjectsPojo saved = projectService.saveProject(projectsPojo);
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);   
    }

    @PutMapping("/{projectID}")
    public ResponseEntity<ProjectsPojo> updateProject(@PathVariable Long projectID, @RequestBody ProjectsPojo projectsPojo) {
        ProjectsPojo updated = projectService.updateProject(projectID, projectsPojo);
        if(updated == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{projectID}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectID){
        boolean isDeleted = projectService.deleteProjectById(projectID);
        if(isDeleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/unfinished-task-count")
    public ResponseEntity<?> getUnfinishedTaskCount() {
        Map<Long, Long> unfinishedTaskCountMap = projectService.getUnfinishedTaskCount();
        return ResponseEntity.ok(unfinishedTaskCountMap);
    }
}
