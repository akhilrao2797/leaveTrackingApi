
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

    @RequestMapping("/leaves/{id}")
    public String getLeaveOfEmployee(@PathVariable(value="id") int id){
        return leaveStore.leavesOfAnEmployee(id);
    }

    @RequestMapping("/apply")
    public LeaveResponse applyLeave(@RequestBody LeaveRequest request)
    {
        Employee employee=employeeStore.getEmployeeOnBasisOfId(request.getEmployeeId());
        LeaveResponse response = manager.apply(employee,request);
        return response;

    }
//    public LeaveResponse applyLeave(@RequestParam("id") int id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,@RequestParam("type") String type,@RequestParam("option") String option){
//        LocalDate startdate = LocalDate.parse(startDate);
//        LocalDate enddate = LocalDate.parse(endDate);
//        LeaveType types= LeaveType.valueOf(type);
//        LeaveOptions options = LeaveOptions.valueOf(option);
//        return leaveStore.applyLeave(id,startdate,enddate,types,options);
//
//    }
}
