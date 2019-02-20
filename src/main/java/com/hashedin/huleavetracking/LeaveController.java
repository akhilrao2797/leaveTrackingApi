/*
package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    private EmployeeStore employeeStore;
    private LeaveStore leaveStore;
    @Autowired
    public LeaveController(EmployeeStore employeeStore,LeaveStore leaveStore){
        this.employeeStore=employeeStore;
        this.leaveStore=leaveStore;
    }

    @RequestMapping("/leaves/{id}")
    public Map<LocalDate, LocalDate> getLeaveOfEmployee(@PathVariable(value="id") int id){
        return leaveStore.leavesOfAnEmployee(id);
    }
}
*/