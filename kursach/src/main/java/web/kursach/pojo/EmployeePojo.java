package web.kursach.pojo;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import web.kursach.model.Day;
import web.kursach.model.Employee;


@Data
public class EmployeePojo {

    private Long id;
    private String fullName;
    private Date birthDate;
    private KpiPojo kpi;
    private List<DayPojo> days;

    // Геттеры и сеттеры

    public static EmployeePojo fromEntity(Employee entity) {
        EmployeePojo employee = new EmployeePojo();
        employee.setId(entity.getId());
        employee.setFullName(entity.getFullName());
        employee.setBirthDate(entity.getBirthDate());
        if (entity.getKpi() != null) {
            employee.setKpi(KpiPojo.fromEntity(entity.getKpi()));
        }
        // Преобразование списка дней
        if (entity.getDays() != null){
            List<DayPojo> days = entity.getDays().stream()
                                             .map(DayPojo::fromEntity)
                                             .collect(Collectors.toList());
            employee.setDays(days);
        }
        return employee;
    }

    public static Employee toEntity(EmployeePojo employeePojo) {
        Employee entity = new Employee();
        entity.setId(employeePojo.getId());
        entity.setFullName(employeePojo.getFullName());
        entity.setBirthDate(employeePojo.getBirthDate());
        if (employeePojo.getKpi() != null) {
            entity.setKpi(employeePojo.getKpi().toEntity());
        }
        if(employeePojo.getDays() != null)
        {// Преобразование списка дней
            List<Day> dayEntities = employeePojo.getDays().stream()
                                                    .map(DayPojo::toEntity)
                                                    .collect(Collectors.toList());
            entity.setDays(dayEntities);
        }
        return entity;
    }
}
