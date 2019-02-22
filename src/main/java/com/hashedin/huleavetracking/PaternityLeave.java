package com.hashedin.huleavetracking;

public class PaternityLeave {

    public LeaveResponse paternityLeaveGrant(Employee employee, LeaveRequest request) {
        if(employee.getNoOfChildren() >2){
            return  new LeaveResponse(LeaveStatus.REJECTED,
                    "Cannot grant leave for more than 2 children birth");
        }
        if (employee.getGender() == "FEMALE") {
            return new LeaveResponse(LeaveStatus.REJECTED, "Gender and Leave Type Mismatch");
        }
        if (employee.getPaternityLeave() > 2){
            return new LeaveResponse(LeaveStatus.REJECTED,"Exceeded max. paternity leaves");}
        if (employee.getPaternityLeave() < 2) {
            employee.setPaternityLeave(employee.getPaternityLeave() + 1);
           // employee.setLeavesAtPresent(request.getStartdate(), request.getStartdate().plusMonths(1));
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");
        }

        return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");

    }
}
