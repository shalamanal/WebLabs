package web.kursach.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.kursach.model.Day;
import web.kursach.pojo.DayPojo;
import web.kursach.repo.DayRepo;

@Service
public class DayService {
    @Autowired
    private DayRepo dayRepo;

    public DayPojo createDay(DayPojo dayPojo){
        try {
            Day day = dayPojo.toEntity();
            return DayPojo.fromEntity(dayRepo.save(day));                                           ///Create
        } catch (Exception e) {
            return null;
        }
    }

    public DayPojo readDay(Long id){
        try {
            Day day = dayRepo.findById(id).orElse(null);
            if(day != null)
                return DayPojo.fromEntity(day);                                    //READ
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<DayPojo> readDays(){
        try{
            List<Day> days = dayRepo.findAll();
            return days.stream().map(DayPojo::fromEntity)                           /// READ
                                    .collect(Collectors.toList());
        }catch (Exception e) {
            return null;
        }
    }

    public DayPojo updateDay(Long id, DayPojo dayPojo){
        try {
            Day day = dayRepo.getReferenceById(id);
            day.setDate(dayPojo.getDate());
            day.setRevenue(dayPojo.getRevenue());                                   ///UPDATE
            return DayPojo.fromEntity(dayRepo.save(day));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteDay(Long id){
        try {
            dayRepo.deleteById(id);                                                     ///Delete
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
