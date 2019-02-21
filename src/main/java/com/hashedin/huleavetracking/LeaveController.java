
package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {
    private EmployeeStore employeeStore;
    private LeaveStore leaveStore;
    @Autowired
    public LeaveController(LeaveStore leaveStore){
        this.leaveStore=leaveStore;
    }

    @RequestMapping("/leaves/{id}")
    public String getLeaveOfEmployee(@PathVariable(value="id") int id){
        return leaveStore.leavesOfAnEmployee(id);
    }

    @RequestMapping("/leaves/id/apply")
    public LeaveResponse applyLeave(@RequestParam("id") int id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,@RequestParam("type") String type,@RequestParam("option") String option){
        LocalDate startdate = LocalDate.parse(startDate);
        LocalDate enddate = LocalDate.parse(endDate);
        LeaveType types= LeaveType.valueOf(type);
        LeaveOptions options = LeaveOptions.valueOf(option);
        return leaveStore.applyLeave(id,startdate,enddate,types,options);

    }
}
