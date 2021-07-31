package com.forkeyauto.dto;


import com.forkeyauto.entity.Employee;
import org.springframework.stereotype.Component;


@Component
public class ConvertDto {

    public EmployeeDto fromEmployeeToEmployeeDto(Employee employee){
        return EmployeeDto.builder()
                .id(employee.getId())
                .fio(employee.getFio())
                .post(employee.getPost())
                .password(employee.getPassword())
                .build();
    }

}
