
package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    private EmployeeStore employeeStore;
    private LeaveStore leaveStore;
    private LeaveManager manager;
    @Autowired
    public LeaveController(LeaveStore leaveStore,EmployeeStore employeeStore,LeaveManager manager){
        this.employeeStore=employeeStore;
        this.leaveStore=leaveStore;
        this.manager =manager;
    }

    @RequestMapping("/leave/apply/")
    public LeaveResponse applyLeave(@RequestBody LeaveRequest request)
    {
        Employee employee=employeeStore.getEmployeeOnBasisOfId(request.getEmployeeId());
        LeaveResponse response = manager.apply(employee,request);
        return response;

    }
}
