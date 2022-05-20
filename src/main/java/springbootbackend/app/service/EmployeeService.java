package springbootbackend.app.service;

import springbootbackend.app.model.Response.EmployeeEmailResponse;
import springbootbackend.app.model.Response.EmployeeResponse;
import springbootbackend.app.entites.Employee;
import springbootbackend.app.exception.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {


    EmployeeEmailResponse findEmailById(Long id) throws ResourceNotFoundException;

    List<EmployeeResponse> findByNameLike(String firstName);

    long getCountEmployees();

    Employee createEmployee(Employee employee);

}
