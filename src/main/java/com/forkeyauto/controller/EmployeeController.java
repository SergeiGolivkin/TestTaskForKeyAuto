package com.forkeyauto.controller;

import com.forkeyauto.dto.ConvertDto;
import com.forkeyauto.dto.EmployeeDto;
import com.forkeyauto.entity.Employee;
import com.forkeyauto.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ConvertDto convertDto;

    @PostMapping()
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
        try {
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok(convertDto.fromEmployeeToEmployeeDto(employee));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @GetMapping()
    public List<EmployeeDto> allGoods(){
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  employeeById(@PathVariable Long id) {
            return ResponseEntity.ok(employeeService.findByIdEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGoods(@PathVariable Long id, @RequestBody Employee employee)  {
            employeeService.updateEmployee(id,employee);
            return ResponseEntity.ok(employeeService.findByIdEmployee(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?>  deleteEmployee(@PathVariable Long id){
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Сотрудник успешно удалён");

    }

}
