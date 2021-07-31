package com.forkeyauto.service;

import com.forkeyauto.controller.EmployeeController;
import com.forkeyauto.dto.ConvertDto;
import com.forkeyauto.dto.EmployeeDto;
import com.forkeyauto.entity.Employee;
import com.forkeyauto.exception.EmployeeAlreadyExistException;
import com.forkeyauto.exception.EmployeeNotFoundException;
import com.forkeyauto.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ConvertDto convertDto;

    public  EmployeeDto findByIdEmployee(Long id) throws EmployeeNotFoundException{
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с ID: " + id + " не найден!"));
       return convertDto.fromEmployeeToEmployeeDto(employee);
    }

    public List<EmployeeDto> findAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(convertDto::fromEmployeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    public void saveEmployee(Employee employee) throws EmployeeAlreadyExistException {
        if (employeeRepository.findByFio(employee.getFio()) != null) {
            throw new EmployeeAlreadyExistException("Сотрудник с таким именем уже существует");
        }
        employee.setPassword(new Random().ints(10, 33, 122)
                .mapToObj(i -> String.valueOf((char)i)).collect(Collectors.joining()));
        employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, Employee employee) throws EmployeeNotFoundException {
        Employee newEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с ID: " + id + " не найден!"));
        newEmployee.setId(employee.getId());
        newEmployee.setRole(employee.getRole());
        newEmployee.setFio(employee.getFio());
        newEmployee.setPost(employee.getPost());
        employeeRepository.save(newEmployee);
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с ID: " + id + " не найден!"));
        employeeRepository.deleteById(id);
    }

}
