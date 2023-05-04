package jp.co.axa.apidemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class EmployeeCreatedDTO {
    private Long id;
}
