package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class EmployeeController {
    private EmployeeStore employeeStore;
    @Autowired
    public EmployeeController(EmployeeStore employeeStore) {
        this.employeeStore = employeeStore;
    }

    @RequestMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeStore.getEmployees();

    }
    @RequestMapping("/")
    public String index() {
        return "Leave Manager";
    }

    @RequestMapping("/employees/{id}")
    public @ResponseBody Employee getEmployee(@PathVariable(value="id") int id){
        return  employeeStore.getEmployeeOnBasisOfId(id);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String addEmployee(@RequestParam("id") int id,@RequestParam("gender") Gender gender, @RequestParam("date") String date) {
        LocalDate dateInLocalDateFormat = LocalDate.parse(date);
        Employee emp =new Employee(id,gender,dateInLocalDateFormat);
        employeeStore.addEmployee(emp);
        return "Successfully added";
    }


}
