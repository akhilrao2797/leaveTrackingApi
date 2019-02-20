package com.hashedin.huleavetracking;

import java.util.HashMap;
import java.util.Map;

public class EmployeeStore {
    private int totalNoOfEmployees;
     Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    public void addEmployee(Employee employee){
        employees.put(totalNoOfEmployees,employee);
        totalNoOfEmployees+=1;
    }
    public void removeEmployee(int id){
        employees.remove(id);
    }
    public int getTotalNoOfEmployees() {
        return totalNoOfEmployees;
    }
    public void displayEmployee()
    {
        for (Map.Entry<Integer,Employee> entry : employees.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public void setTotalNoOfEmployees(int totalNoOfEmployees) {
        this.totalNoOfEmployees = totalNoOfEmployees;
    }

    public Employee getEmployeeOnBasisOfId(int id){
        for ( Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            int key = entry.getKey();
            if(key==id)
                return entry.getValue();
        }
        return null;
    }
}
