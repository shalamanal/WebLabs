package web.kursach.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.kursach.model.Month;
import web.kursach.pojo.MonthPojo;
import web.kursach.repo.MonthRepo;

@Service
public class MonthService {
    @Autowired
    private MonthRepo monthRepo;

    public MonthPojo createDay(MonthPojo monthPojo){
        try {
            Month month = monthPojo.toEntity();
            return MonthPojo.fromEntity(monthRepo.save(month));                                           ///Create
        } catch (Exception e) {
            return null;
        }
    }

    public MonthPojo readMonth(Long id){
        try {
            Month month = monthRepo.findById(id).orElse(null);
            if(month != null)
                return MonthPojo.fromEntity(month);                                    //READ
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<MonthPojo> readMonths(){
        try{
            List<Month> months = monthRepo.findAll();
            return months.stream().map(MonthPojo::fromEntity)                           /// READ
                                    .collect(Collectors.toList());
        }catch (Exception e) {
            return null;
        }
    }

    public MonthPojo updateMonth(Long id, MonthPojo monthPojo){
        try {
            Month month = monthRepo.getReferenceById(id);
            month.setActualRevenue(monthPojo.getActualRevenue());
            month.setPlannedRevenue(monthPojo.getPlannedRevenue());                 ///Update
            month.setMonthNumber(monthPojo.getMonthNumber());
            month.setYear(monthPojo.getYear());
            return MonthPojo.fromEntity(monthRepo.save(month));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteMonth(Long id){
        try {
            monthRepo.deleteById(id);                                             //delete
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
