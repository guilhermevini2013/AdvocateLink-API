package com.example.AdvocateLink.models;

import com.example.AdvocateLink.dto.AddressDTO;
import com.example.AdvocateLink.dto.ContactDTO;
import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.dto.ManageableDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("Employee")
public class Employee extends Manageable{
    private double salary;

    public Employee(Long id, String name, String cpf, String urlPhoto, Role role_Id, double salary) {
        super(id, name, cpf, urlPhoto, role_Id);
        this.salary = salary;
    }

    public Employee(EmployeeDTO employeeDTO) {
        super(employeeDTO.getId(), employeeDTO.getName(), employeeDTO.getCpf(), employeeDTO.getUrlPhoto(), employeeDTO.getRole_id());
        this.salary=employeeDTO.getSalary();
    }
    public Employee(EmployeeDTO employeeDTO, Set<AddressDTO> addressesDTO, Set<ContactDTO> contactsDTO){
        this(employeeDTO);
        addressesDTO.forEach(x-> this.getAddresses().add(new Address(x,this)));
        contactsDTO.forEach(x->this.getContacts().add(new Contact(x,this)));
    }
}
