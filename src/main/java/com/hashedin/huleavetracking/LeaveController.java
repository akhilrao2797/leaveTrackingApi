
package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;



import java.util.ArrayList;

@RestController
public class LeaveController {
    @Autowired
    private EmployeeStore employeeStore;
    @Autowired
    private LeaveStore leaveStore;
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    public LeaveController(LeaveStore leaveStore,EmployeeStore employeeStore,
                           LeaveRepository leaveRepository){
        this.employeeStore=employeeStore;
        this.leaveStore=leaveStore;
        this.leaveRepository=leaveRepository;
    }

    @RequestMapping(value="/leave/apply/",method = RequestMethod.POST)
    public String applyLeave(@RequestBody LeaveRequest request)
    {
        return leaveStore.applyLeave(request).toString();
    }

    @RequestMapping(value="/leaves/")
    public ArrayList<LeaveRequest> applyLeave()
    {
        return leaveStore.getAllLeaves();
    }

    @RequestMapping("/employee/{id}/leavehistory/")
    public ArrayList<LeaveRequest> leaveHistoryOfAnEmployee(@PathVariable("id") int id){
        return leaveStore.leavesOfAnEmployeeById(id);
    }
}
