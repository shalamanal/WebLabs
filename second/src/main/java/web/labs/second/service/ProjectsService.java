package web.labs.second.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.labs.second.model.Projects;
import web.labs.second.pojo.ProjectsPojo;
import web.labs.second.repositrory.ProjectsRepository;


@Service
public class ProjectsService {
    
    @Autowired
    private ProjectsRepository projectRepository;

    public List<ProjectsPojo> getAllProjects(String search) {
        List<Projects> projects;
        if (search != null && !search.isEmpty()) {
            projects = projectRepository.findAllWithFilter(search);
        } else {
            projects = projectRepository.findAll();
        }
        return projects.stream()
                        .map(ProjectsPojo::fromEntity)
                        .collect(Collectors.toList());
    }

    public ProjectsPojo getProjectById(Long projectId) {
        Projects project = projectRepository.findById(projectId)
                                           .orElse(null);
        if (project != null) {
            return ProjectsPojo.fromEntity(project);
        } else {
            return null;
        }
    }

    public ProjectsPojo saveProject(ProjectsPojo projectsPojo){
        try {
            //Преобразуем pojo 
            Projects project = ProjectsPojo.toEntity(projectsPojo);
            return ProjectsPojo.fromEntity(projectRepository.save(project));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ProjectsPojo updateProject(Long projectID, ProjectsPojo projectsPojo){
            Optional<Projects> projectOptional = projectRepository.findById(projectID);
            if (projectOptional.isEmpty()) return null;
            Projects project = projectOptional.get();
            project.setDescription(projectsPojo.getDescription());
            project.setEndDate(projectsPojo.getEndDate());
            project.setName(projectsPojo.getName());
            project.setStartDate(projectsPojo.getStartDate());
            return ProjectsPojo.fromEntity(projectRepository.save(project));
    }

    public boolean deleteProjectById(Long projectID){
        try{
            projectRepository.deleteById(projectID);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Map<Long, Long> getUnfinishedTaskCount() {
        List<Object[]> result = projectRepository.findUnfinishedTaskCount();
        Map<Long, Long> unfinishedTaskCountMap = new HashMap<>();
        for (Object[] row : result) {
            Long projectId = (Long) row[0];
            Long taskCount = (Long) row[1];
            unfinishedTaskCountMap.put(projectId, taskCount);
        }
        return unfinishedTaskCountMap;
    }
}
