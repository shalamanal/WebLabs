package web.kursach.model;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Work_Day")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "month_id")
    private Month month;

    private double revenue;

    private Date date;

    @OneToMany(mappedBy = "workDay")
    private List<Visitor> visitors;

}
