package jp.co.axa.apidemo.dto;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class CreateEmployeeDTO {

    @NotBlank(message = "Employee name cannot be empty")
    private String name;

    private Integer salary;

    @NotBlank(message = "Department cannot be empty")
    private String department;
}
