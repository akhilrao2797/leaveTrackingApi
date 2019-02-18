package com.hashedin.huleavetracking;

import jdk.vm.ci.meta.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LeaveManager {
    public LeaveResponse apply(Employee employee, LeaveRequest request){

        if(request.getStartdate().isAfter(request.getEndDate())){
            throw new IllegalArgumentException("Date is not properly mentioned");
        }

        if(employee.getGender() == Gender.MALE && request.getType() == LeaveType.MaternityLeave ){
            throw new IllegalArgumentException("Gender and Leave Type Mismatch");
        }

        if(employee.getGender() == Gender.FEMALE && request.getType() == LeaveType.PaternityLeave){
            throw new IllegalArgumentException("Gender and Leave Type Mismatch");
        }

        if(employee.getPaternityLeave()>2)
            throw new IllegalArgumentException("Exceeded max. paternity leaves");
        if(employee.getMaternityLeave() > 2)
            throw new IllegalArgumentException("Exceeded max. maternity leaves");

        int noOfDays = noOfActualHolidays(request.getStartdate(),request.getEndDate());
        System.out.println(noOfDays);
      //  if (balance(employee, LocalDate.now()) > noOfDays) {
            if (noOfDays <= 2) {
                LeaveResponse leaveResponse = new LeaveResponse(LeaveStatus.ACCEPTED, "Accepted due to a valid reason");
                return leaveResponse;
            } else
                return new LeaveResponse(LeaveStatus.REJECTED, "Rejected due to a valid reason");

    }

    private int noOfActualHolidays(LocalDate startDate, LocalDate endDate) {
        final DayOfWeek startW = startDate.getDayOfWeek();
        final DayOfWeek endW = endDate.getDayOfWeek();
        final int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        final int daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }

   
    public int compOff(Employee employee, LocalDate date){
        int compOffBalance = employee.getCompOffBalance();
        return 0;
    }
    public int logExtraWorkHours(Employee employee, LocalDateTime startTime, LocalDateTime endTime){
        if(startTime.isAfter(endTime)){
            throw new IllegalArgumentException("Time entered isn't in the perfect manner");
        }
        return 0;
    }

}
