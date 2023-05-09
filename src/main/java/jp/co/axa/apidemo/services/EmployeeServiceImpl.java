package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.ObjectNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Employee getEmployee(Long employeeId) {
        Employee optEmp = employeeRepository.findById(employeeId)
                .orElseThrow(ObjectNotFoundException::new);
        return optEmp;
    }

    public Long saveEmployee(Employee employee){
        Employee saved = employeeRepository.save(employee);
        return saved.getId();
    }

    public void deleteEmployee(Long employeeId){
        int deletedCount =  employeeRepository.deleteByEmployeeId(employeeId);
        if(deletedCount == 0) {
            throw new ObjectNotFoundException();
        }
    }

    public void updateEmployee(Employee employee) {
        Employee emp = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new ObjectNotFoundException());

        emp.setName(employee.getName());
        emp.setSalary(employee.getSalary());
        emp.setDepartment(employee.getDepartment());

        employeeRepository.save(emp);
    }
}