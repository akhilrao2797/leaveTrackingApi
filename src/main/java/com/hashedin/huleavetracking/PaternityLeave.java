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

        employee.setStartLeaveDate(request.getStartDate());
        employee.setEndLeaveDate(request.getStartDate().plusMonths(1));
        employee.setPaternityLeave(employee.getPaternityLeave() + 1);
           // employee.setLeavesAtPresent(request.getStartDate(), request.getStartDate().plusMonths(1));

        return new LeaveResponse(LeaveStatus.ACCEPTED, "Eligible for leave and is granted");

    }
}
