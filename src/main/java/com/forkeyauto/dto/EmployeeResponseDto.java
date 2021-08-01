package com.forkeyauto.dto;

import lombok.Data;

@Data
public class EmployeeResponseDto {

    private Long id;
    private String fio;
    private String post;
    private String password;
}
