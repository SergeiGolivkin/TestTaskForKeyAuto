package com.forkeyauto.service;

import com.forkeyauto.dto.EmployeeRequestDto;
import com.forkeyauto.dto.EmployeeResponseDto;
import com.forkeyauto.entity.Employee;
import com.forkeyauto.exception.EmployeeAlreadyExistException;
import com.forkeyauto.exception.EmployeeNotFoundException;
import com.forkeyauto.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeResponseDto movingEmployeeContr(EmployeeRequestDto employeeRequestDto){
        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);
        if (employeeRequestDto.getType() == 1 && employeeRequestDto.getId() == null)
        { createEmployee(employee);}
        else if (employeeRequestDto.getType() == 2 && employeeRequestDto.getId() != null)
        {updateEmployee(employee);}
        else if (employeeRequestDto.getType() == 3 && employeeRequestDto.getId() != null)
        {dismissalEmployee(employee);}
        return modelMapper.map(employee, EmployeeResponseDto.class);
    }

    public void createEmployee(Employee employee) throws EmployeeAlreadyExistException {
        employee.setPassword(new Random().ints(10, 33, 122)
                .mapToObj(i -> String.valueOf((char)i)).collect(Collectors.joining()));
                employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        Employee newEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с ID: " + employee.getId() + " не найден!"));
        newEmployee.setRole(employee.getRole());
        newEmployee.setFio(employee.getFio());
        newEmployee.setPost(employee.getPost());
        employee.setPassword(newEmployee.getPassword());
        employeeRepository.save(newEmployee);
    }

    public void dismissalEmployee(Employee employee) throws EmployeeNotFoundException {
         Employee delEmployee = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с ID: " + employee.getId() + " не найден!"));
         employee.setPassword(delEmployee.getPassword());
        employeeRepository.delete(delEmployee);
    }
}
