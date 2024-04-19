package web.kursach.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "KPI")
public class Kpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private int workedHours;

    private double bonus;

    private double salary;
 
}