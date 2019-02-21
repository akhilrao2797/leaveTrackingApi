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

    @RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeStore.getEmployees();

    }

    @RequestMapping("/employee/{id}")
    public @ResponseBody Employee getEmployee(@PathVariable(value="id") int id){
        return  employeeStore.getEmployeeOnBasisOfId(id);
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee employee)
    {
        employeeStore.addEmployee(employee);
        return "Sucessfully added";
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.PUT)
    public String updateEmployee(@RequestBody Employee employee)
    {
        employeeStore.updateEmployee(employee);
        return "Sucessfully added";
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestBody Employee employee)
    {
        employeeStore.deleteEmployee(employee);
        return "Sucessfully added";
    }
}
