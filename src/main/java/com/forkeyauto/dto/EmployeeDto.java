package com.forkeyauto.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    private Long id;
    private String fio;
    private String post;
    private String password;

}
