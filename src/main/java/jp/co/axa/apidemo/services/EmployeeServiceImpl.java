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

    /**
     * Get a employee
     * @param employeeId
     * @return a Employee
     * @Throws ObjectNotFoundException if employee with employeeId is not found
     */
    public Employee getEmployee(Long employeeId) {
        Employee optEmp = employeeRepository.findById(employeeId)
                .orElseThrow(ObjectNotFoundException::new);
        return optEmp;
    }

    /**
     * Save a employee
     * @param employee
     * @return employeeId representing the saved employee
     */
    public Long saveEmployee(Employee employee){
        Employee saved = employeeRepository.save(employee);
        return saved.getId();
    }

    /**
     * Delete a employee
     * @param employeeId
     * @throws ObjectNotFoundException if no employee is found with given employeeId
     */
    public void deleteEmployee(Long employeeId){
        int deletedCount =  employeeRepository.deleteByEmployeeId(employeeId);
        if(deletedCount == 0) {
            throw new ObjectNotFoundException();
        }
    }

    /**
     * Update employee
     * @param employee
     * @throws ObjectNotFoundException if no employee with given id is found
     */
    public void updateEmployee(Employee employee) {
        Employee emp = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new ObjectNotFoundException());

        emp.setName(employee.getName());
        emp.setSalary(employee.getSalary());
        emp.setDepartment(employee.getDepartment());

        employeeRepository.save(emp);
    }
}