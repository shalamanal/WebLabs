package web.labs.second.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_description")
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "completed")
    private boolean completed;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Projects project;

    // constructors, getters and setters

}