package web.labs.second.pojo;
import java.sql.Date;

import lombok.Data;
import web.labs.second.model.Projects;
@Data
public class ProjectsPojo {
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;


    public static ProjectsPojo fromEntity(Projects project) {
        ProjectsPojo projectPojo = new ProjectsPojo();
        projectPojo.setId(project.getId());
        projectPojo.setName(project.getName());
        projectPojo.setDescription(project.getDescription());
        projectPojo.setStartDate(project.getStartDate());
        projectPojo.setEndDate(project.getEndDate());
        return projectPojo;
    }

    public static Projects toEntity(ProjectsPojo projectPojo) {
        Projects project = new Projects();
        project.setId(projectPojo.getId());
        project.setName(projectPojo.getName());
        project.setDescription(projectPojo.getDescription());
        project.setStartDate(projectPojo.getStartDate());
        project.setEndDate(projectPojo.getEndDate());
        return project;
    }
}