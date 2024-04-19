package web.kursach.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private Date birthDate;

    @OneToOne(mappedBy = "employee")
    private Kpi kpi;

    @OneToMany(mappedBy = "employee")
    private List<Day> days;

}