/*
package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class LeaveStore {
    private EmployeeStore employeeStore;
    public LeaveStore(EmployeeStore employeeStore){
        this.employeeStore=employeeStore;
    }
    public Map<LocalDate,LocalDate> leavesOfAnEmployee(int id){
        int employeeId;
        Employee emp = null;
        for (Employee employee: employeeStore.getEmployees())
        {
            if(employee.getEmployeeId() == id)
            {emp=employee;
                break; }
        }
        if( emp == null)
            return null;
        return emp.getLeavesAtPresent();
    }
}
*/