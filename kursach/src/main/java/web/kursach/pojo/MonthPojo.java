package web.kursach.pojo;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;
import web.kursach.model.Day;
import web.kursach.model.Month;


@Data
public class MonthPojo {

    private Long id;
    private double plannedRevenue;
    private double actualRevenue;
    private List<DayPojo> workDays;
    private int monthNumber;
    private int year;

    // Геттеры и сеттеры

    public static MonthPojo fromEntity(Month entity) {
        MonthPojo month = new MonthPojo();
        month.setId(entity.getId());
        month.setPlannedRevenue(entity.getPlannedRevenue());
        month.setActualRevenue(entity.getActualRevenue());
        month.setMonthNumber(entity.getMonthNumber());
        month.setYear(entity.getYear());
        // Преобразование списка рабочих дней
        if (entity.getWorkDays() != null) {
            List<DayPojo> workDays = entity.getWorkDays().stream()
                                                         .map(DayPojo::fromEntity)
                                                         .collect(Collectors.toList());
            month.setWorkDays(workDays);
        }
        return month;
    }

    public Month toEntity() {
        Month entity = new Month();
        entity.setId(this.id);
        entity.setPlannedRevenue(this.plannedRevenue);
        entity.setActualRevenue(this.actualRevenue);
        entity.setMonthNumber(this.monthNumber);
        entity.setYear(this.year);
        // Преобразование списка рабочих дней
        if (this.workDays != null) {
            List<Day> dayEntities = this.workDays.stream()
                                                  .map(DayPojo::toEntity)
                                                  .collect(Collectors.toList());
            entity.setWorkDays(dayEntities);
        }
        return entity;
    }
}
