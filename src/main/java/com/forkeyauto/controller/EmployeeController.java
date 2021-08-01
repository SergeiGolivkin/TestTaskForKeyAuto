package com.forkeyauto.controller;

import com.forkeyauto.dto.EmployeeRequestDto;
import com.forkeyauto.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<?> relocationEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return  ResponseEntity.ok(employeeService.movingEmployeeContr(employeeRequestDto));
    }

}
