package web.kursach.model;



import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "Visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "work_day_id")
    private Day workDay;


    private int hours;

    private double payment;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}