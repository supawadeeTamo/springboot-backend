package springbootbackend.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springbootbackend.app.model.Response.EmployeeEmailResponse;
import springbootbackend.app.entites.Employee;
import springbootbackend.app.exception.ResourceNotFoundException;
import springbootbackend.app.service.EmployeeService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class EmployeeController{

    private final EmployeeService employeeService;

//    @GetMapping("/employees")
//    public List<Employee> findAll(){
//        List<Employee> employees = new ArrayList<>();
//        employeeRepository.findAll().forEach(employees::add);
//        return employees;
//    }

//    //create employee rest api
//    @PostMapping("/create/employees")
//    public ResponseEntity  CreateEmployee(@RequestBody Employee employee){
//        Employee employee1;
//        try{
//            //employee รับมาจากหน้าจอ
//            //employee1 return ค่ากลับไปที่หน้าจอ
//            employee1 = employeeRepository.save(employee);
//        }
//        catch (IllegalArgumentException illegalArgumentException) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(employee);
//        //เวลา save ต้องส่ง id กลับไปให้หน้าบ้านด้วย
//    }
//
//    //get employee by id
//    @GetMapping("employees/{id}")
//    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceAccessException("Employee not exists with id: "+id));
//        return ResponseEntity.ok(employee);
//    }
//
//    //update employee rest api
//    @PutMapping("/employees/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceAccessException("Employee not exists with id: "+id));
//
//        employee.setFirstName(employeeDetails.getFirstName());
//        employee.setLastName(employeeDetails.getLastName());
//        employee.setEmailId(employeeDetails.getEmailId());
//
//        Employee updateEmployee = employeeRepository.save(employee);
//        return ResponseEntity.ok(updateEmployee);
//    }
//
//    @DeleteMapping("/employees/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
//        Employee employee = employeeRepository.findById(id)
//         .orElseThrow(() -> new ResourceAccessException("Employee not exists with id: "+id));
//        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted",Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }



    @GetMapping("employees") // method get ส่วนใหญ่จะใช้ path กับ param ในการส่ง
    public ResponseEntity getEmployeeByEmail(@RequestParam Long id) {

        try {
            EmployeeEmailResponse employeeEmailResponse = employeeService.findEmailById(id);
            return ResponseEntity.ok(employeeEmailResponse) ;
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return ResponseEntity.noContent().build();
        }
        catch (Exception exception){
            return ResponseEntity.internalServerError().body(exception);
        }
    }

    @GetMapping("/search/employees")
    public ResponseEntity  getEmployeeByNameLike(@RequestParam String firstName){
        return ResponseEntity.ok(employeeService.findByNameLike(firstName));
    }

    @GetMapping("/count/employees")
    public long countEmployees(){
        long count = employeeService.getCountEmployees();
        return count;
    }

        //create employee rest api
    @PostMapping("/create/employees")
    public ResponseEntity  CreateEmployee(@RequestBody Employee employee){
            return ResponseEntity.ok(employeeService.createEmployee(employee));
    }
}
