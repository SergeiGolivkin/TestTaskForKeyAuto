package com.forkeyauto.dto;

import lombok.Data;

@Data
public class EmployeeRequestDto {

    private Long id;
    private int type;
    private String role;
    private String fio;
    private String post;
}
