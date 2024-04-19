package web.kursach.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.kursach.model.Employee;
import web.kursach.pojo.EmployeePojo;
import web.kursach.repo.EmployeeRepo;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeePojo createEmployee(EmployeePojo employeePojo){
        try {
            Employee employee = EmployeePojo.toEntity(employeePojo);
            return EmployeePojo.fromEntity(employeeRepo.save(employee));                   //CREATE
        } catch (Exception e) {
            return null;
        }
    }

    public  EmployeePojo readEmployee(Long id){
        Employee employee = employeeRepo.findById(id).orElse(null);                        // READ
        if(employee != null)
            return EmployeePojo.fromEntity(employee);
        return null;
    }

    public List<EmployeePojo> readEmployees(){
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map(EmployeePojo::fromEntity)                          // Read all
                                        .collect(Collectors.toList());
    }

    public EmployeePojo updateEmployee(Long id,EmployeePojo employeePojo){
        try {
            Employee employee = employeeRepo.getReferenceById(id);
            employee.setBirthDate(employeePojo.getBirthDate());                             /// UPDATE
            employee.setFullName(employeePojo.getFullName());
            return EmployeePojo.fromEntity(employeeRepo.save(employee));
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteEmployee(Long id){
        try {
            employeeRepo.deleteById(id);                                                //Delete
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
