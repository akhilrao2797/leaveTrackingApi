//
//package com.hashedin.huleavetracking;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class LeaveStore {
//
//    private EmployeeRepository employeeRepository;
//    private LeaveRepository leaveRepository;
//    private EmployeeStore employeeStore;
//    private LeaveManager leaveManager;
//    @Autowired
//    public LeaveStore(EmployeeRepository employeeRepository , LeaveRepository leaveRepository,
//                      EmployeeStore employeeStore,LeaveManager leaveManager)
//    {
//        this.employeeRepository=employeeRepository;
//        this.leaveRepository=leaveRepository;
//        this.employeeStore=employeeStore;
//        this.leaveManager=leaveManager;
//        /*LeaveRequest request =new LeaveRequest(employeeStore.getEmployeeOnId(10), LocalDate.now(),
//        LocalDate.now().plusDays(2),
//                LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
//        LeaveResponse response= leaveManager.apply(employeeStore.getEmployeeOnId(10),request);
//        if(response.getStatus() == LeaveStatus.ACCEPTED)
//        {
//            leaveRepository.save(response.getStatus());
//        }
//        LeaveRequest requestNew =new LeaveRequest(employeeStore.getEmployeeOnId(10), LocalDate.now().plusDays(10)
//        ,LocalDate.now().plusDays(12),
//                LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
//        LeaveResponse responseNew= leaveManager.apply(employeeStore.getEmployeeOnId(10),request);
//        if(responseNew.getStatus() == LeaveStatus.ACCEPTED)
//        {
//            leaveRepository.save(responseNew.getStatus());
//        }
//        */
//    }
//
//    public Optional<LeaveStatus> leavesOfAnEmployee(int id){
//        return leaveRepository.findById(String.valueOf(id));
//    }
//}
