package com.example.ems.services;

import com.example.ems.dtos.EmployeeRequestDto;
import com.example.ems.dtos.EmployeeResponseDto;
import com.example.ems.entities.Employee;
import com.example.ems.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,  ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeResponseDto findById(int id) {
        Employee employee = employeeRepository.findById(id).orElse(null);;
        return modelMapper.map(employee, EmployeeResponseDto.class);

    }

    public List<EmployeeResponseDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return  employees
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeResponseDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = modelMapper.map(employeeRequestDto, Employee.class);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponseDto.class);
    }

    public EmployeeResponseDto update(int id, EmployeeRequestDto employeeRequestDto) throws Exception {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new Exception("Employee with given ID not found");
        }
        employee = modelMapper.map(employeeRequestDto, Employee.class);
        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponseDto.class);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
