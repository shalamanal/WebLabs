package web.kursach.pojo;

import lombok.Data;
import web.kursach.model.Visitor;


@Data
public class VisitorPojo {

    private Long id;
    private DayPojo workDay;
    private int hours;
    private double payment;
    private EmployeePojo employee;

    // Геттеры и сеттеры

    public static VisitorPojo fromEntity(Visitor entity) {
        VisitorPojo visitor = new VisitorPojo();
        visitor.setId(entity.getId());
        visitor.setHours(entity.getHours());
        visitor.setPayment(entity.getPayment());
        // Преобразование рабочего дня
        if (entity.getWorkDay() != null) {
            visitor.setWorkDay(DayPojo.fromEntity(entity.getWorkDay()));
        }
        // Преобразование сотрудника
        if (entity.getEmployee() != null) {
            visitor.setEmployee(EmployeePojo.fromEntity(entity.getEmployee()));
        }
        return visitor;
    }

    public Visitor toEntity() {
        Visitor entity = new Visitor();
        entity.setId(this.id);
        entity.setHours(this.hours);
        entity.setPayment(this.payment);
        // Преобразование рабочего дня
        if (this.workDay != null) {
            entity.setWorkDay(this.workDay.toEntity());
        }
        // Преобразование сотрудника
        if (this.employee != null) {
         //   entity.setEmployee(this.employee.toEntity());
        }
        return entity;
    }
}
