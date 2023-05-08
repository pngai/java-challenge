package jp.co.axa.apidemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.dto.CreateEmployeeDTO;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.ObjectNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testCreateEmployeeSuccess() throws Exception {
        CreateEmployeeDTO employee = new CreateEmployeeDTO("John", 10000, "Finance");

        mockMvc.perform(post("/api/v1/employee")
                        .header("Content-Type", "application/json")
                        .content(mapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andDo(print());
    }

    @Test
    public void testCreateEmployeeFailure() throws Exception {
        CreateEmployeeDTO employee = new CreateEmployeeDTO(null, null, null);

        mockMvc.perform(post("/api/v1/employee")
                        .header("Content-Type", "application/json")
                        .content(mapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void testGetSingleEmployeeSuccess() throws Exception {
        Employee employee = new Employee (12L, "John", 12345, "Finance");
        given(employeeService.getEmployee(any()))
                .willReturn(employee);

        mockMvc.perform(get("/api/v1/employees/{employeeId}",  employee.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andDo(print());
    }

    @Test
    public void testGetNonExistentEmployee() throws Exception {
        given(employeeService.getEmployee(any()))
                .willThrow(ObjectNotFoundException.class);

        mockMvc.perform(get("/api/v1/employees/{employeeId}",  "12345"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testDeleteEmployeeSuccess() throws Exception {
        willDoNothing().given(employeeService).deleteEmployee(any());

        mockMvc.perform(delete("/api/v1/employees/{employeeId}",  12345))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void testDeleteNonExistentEmployee() throws Exception {
        doThrow(ObjectNotFoundException.class)
                .when(employeeService)
                        .deleteEmployee(any());

        mockMvc.perform(delete("/api/v1/employees/{employeeId}",  2234))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}