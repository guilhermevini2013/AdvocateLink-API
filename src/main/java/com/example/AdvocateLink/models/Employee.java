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

    public Employee(EmployeeDTO manageableDTO) {
        super(manageableDTO.getId(), manageableDTO.getName(), manageableDTO.getCpf(), manageableDTO.getUrlPhoto(), manageableDTO.getRole_id());
        this.salary=manageableDTO.getSalary();
    }
    public Employee(EmployeeDTO manageableDTO, Set<AddressDTO> addressesDTO, Set<ContactDTO> contactsDTO){
        this(manageableDTO);
        addressesDTO.forEach(x-> super.addresses.add(new Address(x)));
        contactsDTO.forEach(x-> super.contacts.add(new Contact(x)));
    }
}
