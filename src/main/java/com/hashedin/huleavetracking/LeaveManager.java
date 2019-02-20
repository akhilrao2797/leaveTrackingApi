package com.hashedin.huleavetracking;

import java.sql.SQLOutput;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

public class LeaveManager {
    PublicHolidays holidaymanager =new PublicHolidays();
    // counts the actual no of holidays excluding the weekends
    public int noOfActualHolidays(LeaveRequest request) {
        if (request.getOption() == LeaveOptions.nonBlanketCoverage) {
            Period period = Period.between(request.getStartdate(), request.getEndDate());
            return period.getDays();
        } else {
            final DayOfWeek startW = request.getStartdate().getDayOfWeek();
            final DayOfWeek endW = request.getEndDate().getDayOfWeek();
            final int days = (int) ChronoUnit.DAYS.between(request.getEndDate(), request.getEndDate());
            final int daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);
            int holidays = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
            return holidaymanager.OverlappingPublicHolidays(holidays, request);
        }
    }

   private boolean overlappingDates(Employee employee, LeaveRequest request){
        for(Map.Entry<LocalDate,LocalDate> entry : employee.getLeavesAtPresent().entrySet()){
            if(request.getStartdate().isAfter(entry.getKey()) && request.getEndDate().isBefore(entry.getValue())) {
                return false;
            }
            if(request.getStartdate().isBefore(entry.getKey()) && request.getEndDate().isBefore(entry.getValue()) && request.getStartdate().isBefore(entry.getValue())){
                return false;
            }
            if(request.getStartdate().isBefore(entry.getKey()) && request.getEndDate().isAfter(entry.getValue())){
                return false;
            }
            if(request.getStartdate().isAfter(entry.getKey()) && request.getEndDate().isAfter(entry.getValue()) && request.getEndDate().isBefore(entry.getValue())){
                return false;
            }
        }
        return true;
    }

    public LeaveResponse apply(Employee employee, LeaveRequest request, CompOff compOff) {
        if (request.getStartdate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date is not properly mentioned");
        }

        if (request.getStartdate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Date is not properly mentioned");
        }

        if (request.getType() == LeaveType.PaternityLeave) {
            PaternityLeave maternityLeave =  new PaternityLeave();
            LeaveResponse response = maternityLeave.paternityLeaveGrant(employee,request);
            return response;
        }

        if (request.getType() == LeaveType.MaternityLeave) {
            MaternityLeave maternityLeave =  new MaternityLeave();
            LeaveResponse response = maternityLeave.maternityLeaveGrant(employee,request);
            return response;
        }

        int noOfDays = noOfActualHolidays(request);

        if(request.getType() == LeaveType.CompOff){
            CompOff compoff = new CompOff();
            compoff.compOffLeaveGrant(employee, noOfDays);
        }

        if(!overlappingDates(employee,request))
            throw new IllegalArgumentException("Already holiday has been taken for that time period mentioned");
        if (employee.getBalanceLeaves() > noOfDays) {
            if (noOfDays <= 2) {
                employee.setLeavesAtPresent(request.getStartdate(),request.getEndDate());
                employee.setBalanceLeaves(employee.getBalanceLeaves()- noOfDays);
                LeaveResponse leaveResponse = new LeaveResponse(LeaveStatus.ACCEPTED, "Accepted due to a valid reason");
                return leaveResponse;
            } else
                return new LeaveResponse(LeaveStatus.REJECTED, "Rejected due to more no of days i.e more than 2 days of leave");

        } else {
            throw new IllegalArgumentException("No balance leaves left");
        }

    }

}

