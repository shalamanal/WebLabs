package web.kursach.pojo;

import lombok.Data;
import web.kursach.model.Kpi;


@Data
public class KpiPojo {

    private Long id;
    private EmployeePojo employee;
    private int workedHours;
    private double bonus;
    private double salary;


    public static KpiPojo fromEntity(Kpi entity) {
        KpiPojo kpi = new KpiPojo();
        kpi.setId(entity.getId());
        kpi.setWorkedHours(entity.getWorkedHours());
        kpi.setBonus(entity.getBonus());
        kpi.setSalary(entity.getSalary());
        // Преобразование сотрудника
        if (entity.getEmployee() != null) {
            kpi.setEmployee(EmployeePojo.fromEntity(entity.getEmployee()));
        }
        return kpi;
    }

    public Kpi toEntity() {
        Kpi entity = new Kpi();
        entity.setId(this.id);
        entity.setWorkedHours(this.workedHours);
        entity.setBonus(this.bonus);
        entity.setSalary(this.salary);
        // Преобразование сотрудника
        if (this.employee != null) {
            //entity.setEmployee(this.employee.toEntity());
        }
        return entity;
    }
}
