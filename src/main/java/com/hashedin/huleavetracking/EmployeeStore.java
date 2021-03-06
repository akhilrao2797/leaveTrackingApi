package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeStore {

    private List< Employee> employees;

   // @Autowired
   // private EmployeeRepository employeeRepository;

    public EmployeeStore(){
     this.employees= new ArrayList<>();
     Employee employee = new Employee(10,Gender.MALE, LocalDate.now().minusYears(2));
     this.employees.add(employee);
     Employee employee2 = new Employee(11,Gender.MALE, LocalDate.now().minusYears(2));
     this.employees.add(employee2);
     Employee employee3 = new Employee(12,Gender.FEMALE, LocalDate.now().minusYears(2));
     this.employees.add(employee3);
     Employee employee4 = new Employee(14,Gender.MALE, LocalDate.now().minusYears(2));
     this.employees.add(employee4);
     Employee employee5 = new Employee(15,Gender.MALE, LocalDate.now().minusYears(2));
     this.employees.add(employee5);
    }

    public List<Employee> getEmployees() {
        return employees;
        /*
        List<Employee> emp = new ArrayList<>();
        employeeRepository.findAll().iterator().forEachRemaining(emp::add);
        return emp;
        */
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
         employees.add(employee);
        //employeeRepository.save(employee);
    }
    public void removeEmployee(int id){
       // employeeRepository.deleteById(id);
    }

    public Employee getEmployeeOnBasisOfId(int id){
        Employee employee = null;
        for(Employee emp:employees){
            if(emp.getEmployeeId()==id) {
                employee = emp;
                break;
            }
        }
        return employee;
       // return employeeRepository.findById(id).toString();
    }
}
