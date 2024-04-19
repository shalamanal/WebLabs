package web.kursach.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.kursach.pojo.EmployeePojo;
import web.kursach.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeePojo> createEmployee(@RequestBody EmployeePojo employeePojo) {
        EmployeePojo saved = employeeService.createEmployee(employeePojo);                // Create
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    


    @GetMapping
    public ResponseEntity<List<EmployeePojo>> getEmployers(){
        List<EmployeePojo> employees = employeeService.readEmployees();             //Get All
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employeeID}")
    public ResponseEntity<EmployeePojo> getEmployer(@RequestParam Long employeeID) {
        EmployeePojo employee = employeeService.readEmployee(employeeID);           // Get by id
        if(employee == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(employee);
    }
    
    @PutMapping("{employeeID}")
    public ResponseEntity<EmployeePojo> putMethodName(@PathVariable Long employeeID, @RequestBody EmployeePojo employeePojo) {
        EmployeePojo updated = employeeService.updateEmployee(employeeID, employeePojo);
        if(updated == null)                                                         // Update
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("{employeeID}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeID){
        boolean isDeleted = employeeService.deleteEmployee(employeeID);             //Delete
        if(!isDeleted)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
