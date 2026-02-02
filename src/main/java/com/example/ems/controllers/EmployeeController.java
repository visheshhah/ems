package com.example.ems.controllers;

import com.example.ems.dtos.EmployeeRequestDto;
import com.example.ems.dtos.EmployeeResponseDto;
import com.example.ems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public List<EmployeeResponseDto> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("{Id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable int id) {
        return  new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return new ResponseEntity<>(employeeService.create(employeeRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{Id}")
    public void deleteEmployeeById(@PathVariable int id) {
        employeeService.deleteById(id);
    }

    @PutMapping("/Id")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable int id ,@RequestBody EmployeeRequestDto employeeRequestDto) {
        try{
            return ResponseEntity.ok(employeeService.update(id,  employeeRequestDto));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
