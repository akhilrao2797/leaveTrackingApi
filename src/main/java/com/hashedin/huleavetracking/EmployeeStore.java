package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeStore {

    private List< Employee> employees;

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeStore( EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
        Employee employee = new Employee(10,Gender.MALE, LocalDate.now().minusYears(2));
        Employee employee2 = new Employee(11,Gender.MALE, LocalDate.now().minusYears(1));
        Employee employee3 = new Employee(12,Gender.FEMALE, LocalDate.now().minusYears(2));
        Employee employee4 = new Employee(14,Gender.MALE, LocalDate.now().minusYears(6));
        Employee employee5 = new Employee(15,Gender.MALE, LocalDate.now().minusYears(3));
        employeeRepository.save(employee);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);
    }

    public List<Employee> getEmployees() {
        //return employees;
        ArrayList<Employee> emp = new ArrayList<>();
        employeeRepository.findAll().forEach(emp::add);
        return emp;
    }

    public void addEmployee(Employee employee){
        // employees.add(employee);
        employeeRepository.save(employee);
    }
    public void deleteEmployee(Employee employee){
        employeeRepository.delete(employee);
    }

    public void updateEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeOnBasisOfId(int id){
        return employeeRepository.findById(id);
    }

    public Employee getEmployeeOnId(int id){
        Employee employee = null;
        for(Employee emp:employees){
            if(emp.getEmployeeId()==id) {
                employee = emp;
                break;
            }
        }
        return employee;
    }
}
