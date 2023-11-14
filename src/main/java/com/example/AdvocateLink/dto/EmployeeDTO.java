package com.example.AdvocateLink.dto;

import com.example.AdvocateLink.models.Address;
import com.example.AdvocateLink.models.Contact;
import com.example.AdvocateLink.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends ManageableDTO{
    private Double salary;

    public EmployeeDTO(Employee employee){
        super(employee.getId(), employee.getName(), employee.getCpf(), employee.getUrlPhoto(), employee.getRole_Id());
        this.salary = employee.getSalary();
    }
    public EmployeeDTO(Employee employee, Set<Address> addressSet, Set<Contact> contactSet){
        this(employee);
        addressSet.forEach(x-> super.addressesDTO.add(new AddressDTO(x)));
        contactSet.forEach(x-> super.contactsDTO.add(new ContactDTO(x)));
    }
}
