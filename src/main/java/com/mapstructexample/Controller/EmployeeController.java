package com.mapstructexample.Controller;

import com.mapstructexample.DTO.EmployeeDTO;
import com.mapstructexample.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
We just use the ServiceClass here. Basically this is our flow:

Controller-->Service-->Repository & Mapper -->DTO-->Entity
 */
@RestController
@RequestMapping("/api/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Deprecated
    @PostMapping(consumes = "application/json", produces = "application/json")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        System.out.println(employeeDTO);
        return employeeService.createEmployee(employeeDTO);
    }

    @PostMapping(value ="/all", consumes = "application/json", produces = "application/json")
    public List<EmployeeDTO> createEmployees(@RequestBody List<EmployeeDTO> employeeDTOs) {
        System.out.println(employeeDTOs);
        return employeeService.createEmployees(employeeDTOs);
    }
}
