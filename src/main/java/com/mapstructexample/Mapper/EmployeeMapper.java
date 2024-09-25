package com.mapstructexample.Mapper;

import com.mapstructexample.DTO.EmployeeDTO;
import com.mapstructexample.Entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Define a MapStruct Mapper to handle the conversion between the UserEntity (what is stored in DB) and UserDTO`.
 * MapStruct will generate the implementation of this interface automatically
 */


@Mapper(componentModel = "spring") //The componentModel = "spring" tells MapStruct to integrate with the Spring framework, so that the mapper can be injected as a Spring bean.
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    // Map DTO to Entity
    @Mapping(source = "employeeName", target = "name")
    @Mapping(source = "employeeDepartment", target = "department")
    EmployeeEntity toEntity(EmployeeDTO employeeDTO);

    // Map Entity to DTO

//    @Mapping makes sure the fields in entity and dto match
    @Mapping(source = "name", target = "employeeName")
    @Mapping(source = "department", target = "employeeDepartment")
    EmployeeDTO toDTO(EmployeeEntity employeeEntity);
}
