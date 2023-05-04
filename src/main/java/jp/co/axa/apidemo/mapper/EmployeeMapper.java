package jp.co.axa.apidemo.mapper;

import jp.co.axa.apidemo.dto.CreateEmployeeDTO;
import jp.co.axa.apidemo.entities.Employee;

public class EmployeeMapper {
    public static Employee mapToDomainEntity(
            CreateEmployeeDTO dto) {

        return Employee.withoutId(
                dto.getName(),
                dto.getSalary(),
                dto.getDepartment());
    }
}
