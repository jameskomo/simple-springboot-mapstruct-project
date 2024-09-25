package com.mapstructexample.DTO;

import java.util.Objects;

public class EmployeeDTO {

    private Long id;
    private String employeeName;
    private String employeeDepartment;

    // Constructors
    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String employeeName, String employeeDepartment) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(employeeName, that.employeeName) && Objects.equals(employeeDepartment, that.employeeDepartment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeName, employeeDepartment);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeDepartment='" + employeeDepartment + '\'' +
                '}';
    }
}
