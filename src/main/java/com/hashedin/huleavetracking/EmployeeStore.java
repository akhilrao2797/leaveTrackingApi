package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeStore {

    private List< Employee> employees;
    private LogExtraWorkedHoursRepository logExtraWorkedHoursRepository;
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeStore( EmployeeRepository employeeRepository,
                          LogExtraWorkedHoursRepository logExtraWorkedHoursRepository){
        this.employeeRepository=employeeRepository;
        this.logExtraWorkedHoursRepository=logExtraWorkedHoursRepository;
        Employee employee = new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3);
        Employee employee2 = new Employee(11,Gender.MALE, LocalDate.now().minusYears(1),1,
                0,0,10);
        Employee employee3 = new Employee(12,Gender.FEMALE, LocalDate.now().minusYears(2),1,
                0,0,13);
        Employee employee4 = new Employee(14,Gender.MALE, LocalDate.now().minusYears(6),1,
                0,0,10);
        Employee employee5 = new Employee(15,Gender.MALE, LocalDate.now().minusYears(3),1,
                0,0,4);
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
        if(employeeRepository.existsById(employee.getEmployeeId())) {
            employeeRepository.delete(employee);
        }
    }

    public void updateEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getEmployeeOnBasisOfId(int id){
        return employeeRepository.findById(id).get();
    }

    public ArrayList<LogExtraWorkedHours> getExtraWorkedHours(int id){
        ArrayList<LogExtraWorkedHours> loggedEmployeesWork = new ArrayList<>();
        for (LogExtraWorkedHours logExtraWorkedHours:logExtraWorkedHoursRepository.findAll())
              {
                  if(logExtraWorkedHours.getEmployeeId() == id){
                      loggedEmployeesWork.add(logExtraWorkedHours);
                  }
        }
        return loggedEmployeesWork;
    }
    public String postExtraWorkedHours(int id, LogExtraWorkedHours logExtraWorkedHours){
       if(employeeRepository.existsById(id) && id == logExtraWorkedHours.getEmployeeId())
       {
           CompOff compOff =new CompOff(employeeRepository.findById(id).get());
           compOff.logExtraWorkHours(logExtraWorkedHours.getStartDateTime(),logExtraWorkedHours.getEndDateTime());
           logExtraWorkedHoursRepository.save(logExtraWorkedHours);
           return "Successfully logged";
       }
        return "Sorry!  Employee not found or wrong employee details specified";
    }
}
