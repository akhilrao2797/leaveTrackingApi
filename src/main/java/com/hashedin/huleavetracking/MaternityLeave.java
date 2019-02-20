package com.hashedin.huleavetracking;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class MaternityLeave {

    public LeaveResponse maternityLeaveGrant(Employee employee, LeaveRequest request){
        if (employee.getGender() == Gender.MALE)
            throw new IllegalArgumentException("Gender and Leave Type Mismatch");
        if (employee.getMaternityLeave() > 2)
            throw new IllegalArgumentException("Exceeded max. maternity leaves");
        if (DAYS.between(employee.getJoiningDate(), LocalDate.now()) <= 180)
             throw new IllegalArgumentException("Min. of 180 days required from the date of joining to get the maternity leave");
        employee.setPaternityLeave(employee.getPaternityLeave() + 1);
        employee.setLeavesAtPresent(request.getStartdate(),request.getStartdate().plusMonths(6));
        return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");

      //  return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");
    }
}
