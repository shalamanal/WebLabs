package web.kursach.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Month")
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double plannedRevenue;

    private double actualRevenue;

    @OneToMany(mappedBy = "month")
    private List<Day> workDays;

    private int monthNumber;

    private int year;

}
