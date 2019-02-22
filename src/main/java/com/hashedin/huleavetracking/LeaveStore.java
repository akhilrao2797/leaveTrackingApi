
package com.hashedin.huleavetracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LeaveStore {

    private EmployeeRepository employeeRepository;
    private LeaveRepository leaveRepository;
    private EmployeeStore employeeStore;
    private LeaveManager leaveManager;
    @Autowired
    public LeaveStore(EmployeeRepository employeeRepository , LeaveRepository leaveRepository,
                      EmployeeStore employeeStore,LeaveManager leaveManager)
    {
        this.employeeRepository=employeeRepository;
        this.leaveRepository=leaveRepository;
        this.employeeStore=employeeStore;
        this.leaveManager=leaveManager;
    }

    public ArrayList<LeaveRequest> leavesOfAnEmployeeById(int id){
        ArrayList<LeaveRequest> leaves=new ArrayList<>();
        leaveRepository.findAll().forEach(leaves::add);
        ArrayList<LeaveRequest> leaveHistory=new ArrayList<>();
        for(LeaveRequest request: leaves){
            if(request.getEmployeeId()==id){
                leaveHistory.add(request);
            }
        }
        return leaveHistory;
    }

    public LeaveResponse applyLeave(LeaveRequest request){
        Employee employee=employeeStore.getEmployeeOnBasisOfId(request.getEmployeeId()).get();
        LeaveResponse response = leaveManager.apply(employee,request);
        if(response.getStatus() == LeaveStatus.ACCEPTED)
        {
            System.out.println("save");
            leaveRepository.save(request);
        }
        return response;
    }

    public ArrayList<LeaveRequest> getAllLeaves(){
        ArrayList<LeaveRequest> leaves=new ArrayList<>();
        leaveRepository.findAll().forEach(leaves::add);
        return leaves;
    }
}
