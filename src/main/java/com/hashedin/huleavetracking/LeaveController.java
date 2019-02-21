//
//package com.hashedin.huleavetracking;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//@RestController
//public class LeaveController {
//    @Autowired
//    private EmployeeStore employeeStore;
//    @Autowired
//    private LeaveStore leaveStore;
//    @Autowired
//    private LeaveManager manager;
//    @Autowired
//    private LeaveRepository leaveRepository;
//    @Autowired
//    public LeaveController(LeaveStore leaveStore,EmployeeStore employeeStore,LeaveManager manager,
//                           LeaveRepository leaveRepository){
//        this.employeeStore=employeeStore;
//        this.leaveStore=leaveStore;
//        this.manager =manager;
//        this.leaveRepository=leaveRepository;
//    }
//
//    @RequestMapping("/leave/apply/")
//    public LeaveResponse applyLeave(@RequestBody LeaveRequest request)
//    {
//        Employee employee=employeeStore.getEmployeeOnId(request.getEmployeeId());
//        LeaveResponse response = manager.apply(employee,request);
//        if(response.getStatus() == LeaveStatus.ACCEPTED)
//        {
//            leaveRepository.save(response.getStatus());
//        }
//        return response;
//    }
//}
