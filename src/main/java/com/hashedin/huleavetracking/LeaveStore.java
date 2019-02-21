
package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class LeaveStore {
    private EmployeeStore employeeStore;
    @Autowired
    public LeaveStore(EmployeeStore employeeStore){
        this.employeeStore=employeeStore;
    }
    public String leavesOfAnEmployee(int id){
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
        return emp.getStartLeaveDate() + " " +emp.getEndDate();
    }
    public LeaveResponse applyLeave(int id, LocalDate startDate, LocalDate endDate,LeaveType type,LeaveOptions option){
        LeaveManager manager= new LeaveManager();
        Employee employee = employeeStore.getEmployeeOnBasisOfId(id);
        LeaveRequest request = new LeaveRequest(employee,startDate,endDate,type,option);
        LeaveResponse response = manager.apply(employee,request);
        return response;
    }
}