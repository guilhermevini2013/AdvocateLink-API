package com.example.AdvocateLink.service.factory;

import com.example.AdvocateLink.dto.ClientDTO;
import com.example.AdvocateLink.dto.EmployeeDTO;
import com.example.AdvocateLink.dto.ManageableDTO;
import com.example.AdvocateLink.dto.RoleDTO;
import com.example.AdvocateLink.models.Client;
import com.example.AdvocateLink.models.Employee;
import com.example.AdvocateLink.models.Manageable;
import com.example.AdvocateLink.models.Role;

import java.util.HashSet;

public class Factory {
    public static EmployeeDTO createEmployeeDTO(){
        return new EmployeeDTO(1l,"guilherme","123",new HashSet<>(),new HashSet<>(),"1",new Role(1l,"ee"),2.0);
    }
    public static Employee createEmployee(){
        return new Employee(createEmployeeDTO());
    }
    public static Role createRole(){
        return new Role(1l,"Engenheiro");
    }
    public static RoleDTO createRoleDto(){
        return new RoleDTO(createRole());
    }
    public static RoleDTO createRoleDtoNullAttributes(){
        return new RoleDTO(2l,null);
    }
    public static ClientDTO createClientDto(){
        return new ClientDTO(1l,"guilherme","123",new HashSet<>(),new HashSet<>(),"1",new Role(1l,"ee"),"123");
    }
    public static Client createClient(){
        return new Client(createClientDto());
    }
}
