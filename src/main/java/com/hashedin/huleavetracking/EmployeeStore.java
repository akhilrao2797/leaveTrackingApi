package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeStore {
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    private List< Employee> employees;
     public EmployeeStore(){
         this.employees= new ArrayList<>();
         /*
         Employee employee = new Employee(10,Gender.MALE, LocalDate.now().minusYears(2));
         this.employees.add(employee);
         Employee employee2 = new Employee(11,Gender.MALE, LocalDate.now().minusYears(2));
         this.employees.add(employee2);
         Employee employee3 = new Employee(12,Gender.FEMALE, LocalDate.now().minusYears(2));
         this.employees.add(employee3);
         Employee employee4 = new Employee(14,Gender.MALE, LocalDate.now().minusYears(2));
         this.employees.add(employee4);
         Employee employee5 = new Employee(15,Gender.MALE, LocalDate.now().minusYears(2));
         this.employees.add(employee5);*/
     }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public void removeEmployee(int id){
        employees.remove(id);
    }

    public List<Employee> displayEmployee()
    {
        return employees;
    }

    public Employee getEmployeeOnBasisOfId(int id){
        for ( Employee employee: employees) {
            if(employee.getEmployeeId()==id)
                return employee;
        }
        return null;
    }
}
