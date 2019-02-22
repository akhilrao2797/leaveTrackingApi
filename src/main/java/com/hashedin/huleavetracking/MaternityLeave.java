package com.hashedin.huleavetracking;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class MaternityLeave {

    public LeaveResponse maternityLeaveGrant(Employee employee, LeaveRequest request){
        if(employee.getNoOfChildren() >2){
            return  new LeaveResponse(LeaveStatus.REJECTED,
                    "Cannot grant leave for more than 2 children birth");
        }
        if (employee.getGender() == "MALE") {
            return new LeaveResponse(LeaveStatus.REJECTED, "Gender and leave mismatch");
        }
        if (employee.getMaternityLeave() > 2) {
            return new LeaveResponse(LeaveStatus.REJECTED, "Exceeded max. maternity leaves");
        }
        if (DAYS.between(employee.getJoiningDate(), LocalDate.now()) <= 180) {
            return new LeaveResponse(LeaveStatus.REJECTED, "Min. of 180 days required from the date " +
                    "of joining to get the maternity leave");
        }
        employee.setStartLeaveDate(request.getStartDate());
        employee.setEndLeaveDate(request.getStartDate().plusMonths(6));
        employee.setPaternityLeave(employee.getPaternityLeave() + 1);
        return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");
    }
}
