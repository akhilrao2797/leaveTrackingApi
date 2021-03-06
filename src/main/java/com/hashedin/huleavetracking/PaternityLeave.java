package com.hashedin.huleavetracking;

public class PaternityLeave {

    public LeaveResponse paternityLeaveGrant(Employee employee, LeaveRequest request) {
        if (employee.getGender() == Gender.FEMALE)
            throw new IllegalArgumentException("Gender and Leave Type Mismatch");
        if (employee.getPaternityLeave() > 2)
            throw new IllegalArgumentException("Exceeded max. paternity leaves");
        if (employee.getPaternityLeave() < 2) {
            employee.setPaternityLeave(employee.getPaternityLeave() + 1);
            employee.setLeavesAtPresent(request.getStartdate(), request.getStartdate().plusMonths(1));
            return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");
        }

        return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");

    }
}
