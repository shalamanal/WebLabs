package web.kursach.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.kursach.model.Visitor;
import web.kursach.pojo.VisitorPojo;
import web.kursach.repo.VisitorRepo;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepo visitorRepo;

    public VisitorPojo createVisitor(VisitorPojo visitorPojo){
        try {
            Visitor visitor = visitorPojo.toEntity();
            return VisitorPojo.fromEntity(visitorRepo.save(visitor));                                           ///Create
        } catch (Exception e) {
            return null;
        }
    }


    public VisitorPojo readVisitor(Long id){
        try {
            Visitor visitor = visitorRepo.findById(id).orElse(null);
            if(visitor != null)
                return VisitorPojo.fromEntity(visitor);                                    //READ
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<VisitorPojo> readVisitors(){
        try{
            List<Visitor> visitors = visitorRepo.findAll();
            return visitors.stream().map(VisitorPojo::fromEntity)                           /// READ
                                    .collect(Collectors.toList());
        }catch (Exception e) {
            return null;
        }
    }

    public VisitorPojo updateVisitor(Long id, VisitorPojo visitorPojo){
        try {
            Visitor visitor = visitorRepo.getReferenceById(id);
            visitor.setHours(visitorPojo.getHours());                                   ///UPDATE
            visitor.setPayment(visitorPojo.getPayment());
            return VisitorPojo.fromEntity(visitorRepo.save(visitor));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteVisitor(Long id){
        try {
            visitorRepo.deleteById(id);                                             //delete
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
