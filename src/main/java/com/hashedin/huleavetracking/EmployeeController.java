package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;


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
    public Optional<Employee> getEmployee(@PathVariable(value="id") int id){
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
        return "Sucessfully updated";
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.DELETE)
    public String deleteEmployee(@RequestBody Employee employee)
    {
        employeeStore.deleteEmployee(employee);
        return "Sucessfully deleted";
    }

    @RequestMapping(value = "/employee/{id}/leavebalance/")
    public String leaveBalance(@RequestParam(value ="id") int id)
    {
        int leaveBalances=employeeStore.getEmployeeOnId(id).getBalanceLeaves();
        return "Leave Balance for Employee Id : "+id+"  =  "+leaveBalances ;
    }

    @RequestMapping(value = "/employee/{id}/workedhours/")
    public String workedHours(@RequestParam(value ="id") int id)
    {
        int leaveBalances=employeeStore.getEmployeeOnId(id).getBalanceLeaves();
        return "Leave Balance for Employee Id : "+id+"  =  "+leaveBalances ;
    }

    @RequestMapping(value = "/employee/{id}/compoffbalance/")
    public String compOffBalance(@RequestParam(value ="id") int id)
    {
        int compOff=employeeStore.getEmployeeOnId(id).getCompOff();
        return "CompOff Balance for Employee Id : "+id+"  =  "+compOff ;
    }
}
