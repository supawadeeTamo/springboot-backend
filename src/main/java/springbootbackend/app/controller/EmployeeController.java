package springbootbackend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbootbackend.app.model.Employee;
import springbootbackend.app.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class EmployeeController{

    @Autowired
    private EmployeeRepository employeeRepository;

    //get all employee

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}


