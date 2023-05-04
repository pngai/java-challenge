package jp.co.axa.apidemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.dto.CreateEmployeeDTO;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.notNullValue;
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
}