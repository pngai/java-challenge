package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.ObjectNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

public class EmployeeServiceImplTest {

    private final EmployeeRepository employeeRepository =
            Mockito.mock(EmployeeRepository.class);

    private final EmployeeService unerTest =
            new EmployeeServiceImpl(employeeRepository);

    @Test
    public void saveEmployeeSuccess() {
        Employee employee = new Employee(null, "John", 1000, "Finance");
        given(employeeRepository.save(any(Employee.class)))
                .willReturn(new Employee(1L, "John", 1000, "Finance"));

        Long expectedId = unerTest.saveEmployee(employee);

        assertThat(expectedId)
                .isNotNull();
    }

    @Test
    public void getNonExistEmployee() {
        assertThatThrownBy(() -> {
            Employee retrievedEmployee = unerTest.getEmployee(123L);
        }).isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void deleteEmployee() {
        given(employeeRepository.deleteByEmployeeId(any()))
                .willReturn(1);

        Assertions.assertThatCode(
                () ->  unerTest.deleteEmployee(1234L)
                )
                .doesNotThrowAnyException();
    }
    @Test
    public void deleteNonExistEmployee() {
        assertThatThrownBy(() -> {
            unerTest.deleteEmployee(1234L);
        }).isInstanceOf(ObjectNotFoundException.class);
    }


}