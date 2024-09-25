package com.mapstructexample.Service;

import com.mapstructexample.DTO.EmployeeDTO;
import com.mapstructexample.Entity.EmployeeEntity;
import com.mapstructexample.Mapper.EmployeeMapper;
import com.mapstructexample.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement the service that will use the repository (handle db operations) and mapper(convert btw DTO and Entity) to handle business logic.
 */

@Service
public class EmployeeService {

    //our service classes uses the repository and mapper so we inject them here as static objects

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {

        //The EmployeeRepository interface extends the JPA repository which takes the EmployeeEntity Object and Long Id as parameters JpaRepository<UserEntity, Long>
        //We are using the findAll method available through extended JpaRepository which is now available in employee repository to get all Entity(DB) objects
        List<EmployeeEntity> employees = employeeRepository.findAll();

//        Pass employees fetched from DB (Via Entity class) to a Java Stream in order to apply stream operations map and toList
        return employees.stream()
                //deserialize (convert from DB Objects to JSON via the toDTO method in Mapper class i.e. map entity to DTO---> UserDTO toDTO(UserEntity userEntity);
                .map(employeeMapper::toDTO)
                //get a list of DTO
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {

        //Get specific user from DB (Entity) by user Id using Jpa findById method
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        //deserialize (convert returned user to JSON via the toDTO method in Mapper class i.e. map entity to DTO---> UserDTO toDTO(UserEntity userEntity);
        return employeeMapper.toDTO(employeeEntity);
    }


//    CREATE SINGLE OBJECT

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        //serialize new user so that we can create in DB/when we create a new user, they are in DTO, and we have to convert to Entity to write to DB so we create an object of UserEntity
        // -->UserEntity toEntity(UserDTO userDTO)
        System.out.println("Employee DTO: " + employeeDTO);
        EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeDTO);
        //use JPARepository save method to save to the DB
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
;
        //Return the new created user as a DTO...>In order to display correctly as Java Object (DTO)
        return employeeMapper.toDTO(savedEmployee);
    }

    //    CREATE LIST OF OBJECTS
    public List<EmployeeDTO> createEmployees(List<EmployeeDTO> employeeDTOs) {
        //serialize new user so that we can create in DB/when we create a new user, they are in DTO, and we have to convert to Entity to write to DB so we create an object of UserEntity
        // -->UserEntity toEntity(UserDTO userDTO)
        System.out.println("Employee DTO\n\n: " + employeeDTOs);
        List<EmployeeEntity> employeeEntities = employeeDTOs.stream()
                        .map(employeeMapper::toEntity) //convert each of the entity to DTO
                        .collect(Collectors.toList());
        //use JPARepository saveAll method to save all entities to the DB
        List<EmployeeEntity> savedEmployees = employeeRepository.saveAll(employeeEntities);
        ;
        //Return the new created user as a DTO...>In order to display correctly as Java Object (DTO)
        // Convert saved entities (savedEmployees) back to DTOs
        return savedEmployees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
