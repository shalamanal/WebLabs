package web.kursach.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.kursach.model.Kpi;
import web.kursach.pojo.KpiPojo;
import web.kursach.repo.KpiRepo;

@Service
public class KpiService {

        @Autowired
        private KpiRepo kpiRepo;

    public KpiPojo createKpi(KpiPojo kpiPojo){
        try {
            Kpi kpi = kpiPojo.toEntity();
            return KpiPojo.fromEntity(kpiRepo.save(kpi));                                           ///Create
        } catch (Exception e) {
            return null;
        }
    }

    public KpiPojo readKpi(Long id){
        try {
            Kpi kpi = kpiRepo.findById(id).orElse(null);
            if(kpi != null)
                return KpiPojo.fromEntity(kpi);                                    //READ
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<KpiPojo> readKpis(){
        try{
            List<Kpi> kpis = kpiRepo.findAll();
            return kpis.stream().map(KpiPojo::fromEntity)                           /// READ
                                    .collect(Collectors.toList());
        }catch (Exception e) {
            return null;
        }
    }

    public KpiPojo updateKpi(Long id, KpiPojo kpiPojo){
        try {
            Kpi kpi = kpiRepo.getReferenceById(id);
            kpi.setBonus(kpiPojo.getBonus());
            kpi.setSalary(kpiPojo.getSalary());
            kpi.setWorkedHours(kpiPojo.getWorkedHours());                           //Update
            return KpiPojo.fromEntity(kpiRepo.save(kpi));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteKpi(Long id){
        try {
            kpiRepo.deleteById(id);                                                         ///Delete
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
