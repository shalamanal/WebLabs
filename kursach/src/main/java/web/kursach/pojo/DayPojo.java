package web.kursach.pojo;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import web.kursach.model.Day;
import web.kursach.model.Visitor;

@Data
public class DayPojo {

    private Long id;
    private EmployeePojo employee;
    private MonthPojo month;
    private double revenue;
    private Date date;
    private List<VisitorPojo> visitors;

    // Геттеры и сеттеры

    public static DayPojo fromEntity(Day entity) {
        DayPojo day = new DayPojo();
        day.setId(entity.getId());
        day.setRevenue(entity.getRevenue());
        day.setDate(entity.getDate());
        // Преобразование сотрудника
        if (entity.getEmployee() != null) {
            day.setEmployee(EmployeePojo.fromEntity(entity.getEmployee()));
        }
        // Преобразование месяца
        if (entity.getMonth() != null) {
            day.setMonth(MonthPojo.fromEntity(entity.getMonth()));
        }
        // Преобразование списка посетителей
        if (entity.getVisitors() != null) {
            List<VisitorPojo> visitors = entity.getVisitors().stream()
                                            .map(VisitorPojo::fromEntity)
                                            .collect(Collectors.toList());
            day.setVisitors(visitors);
        }
        return day;
    }

    public Day toEntity() {
        Day entity = new Day();
        entity.setId(this.id);
        entity.setRevenue(this.revenue);
        entity.setDate(this.date);
        // Преобразование сотрудника
        if (this.employee != null) {
            //entity.setEmployee(this.employee.toEntity());
        }
        // Преобразование месяца
        if (this.month != null) {
            entity.setMonth(this.month.toEntity());
        }
        // Преобразование списка посетителей
        if (this.visitors != null) {
            List<Visitor> visitorEntities = this.visitors.stream()
                                                         .map(VisitorPojo::toEntity)
                                                         .collect(Collectors.toList());
            entity.setVisitors(visitorEntities);
        }
        return entity;
    }
}
