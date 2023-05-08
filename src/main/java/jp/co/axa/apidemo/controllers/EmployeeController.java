package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.CreateEmployeeDTO;
import jp.co.axa.apidemo.dto.EmployeeCreatedDTO;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.mapper.EmployeeMapper;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeCreatedDTO> saveEmployee(@Valid @RequestBody CreateEmployeeDTO createEmployeeRrequest){
        Employee employee = EmployeeMapper.mapToDomainEntity(createEmployeeRrequest);

        Long createdEmployeeId = employeeService.saveEmployee(employee);
        log.info("Employee {} Saved Successfully", employee.getName());
        return new ResponseEntity<>(new EmployeeCreatedDTO(createdEmployeeId), HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        log.info("Employee {} Deleted Successfully", employeeId);
        return new ResponseEntity<>("Employee deleted successfully!.", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }

    }

}
