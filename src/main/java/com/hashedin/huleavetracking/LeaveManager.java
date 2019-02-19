package com.hashedin.huleavetracking;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

public class LeaveManager {

    public int balanceHolidaysLeft(Employee employee, LocalDate date) {
        int balance = 0;
        for (Map.Entry<String, Integer> entry : employee.month.entrySet()) {
            if (date.getMonth().toString() == entry.getKey()) {
                int value = entry.getValue();
                balance += value;
                break;
            }

            balance += entry.getValue();
        }

        return balance;
    }

    // counts the actual no of holidays excluding the weekends
    private int noOfActualHolidays(LocalDate startDate, LocalDate endDate) {
        final DayOfWeek startW = startDate.getDayOfWeek();
        final DayOfWeek endW = endDate.getDayOfWeek();
        final int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        final int daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
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

     /*  LocalDate esdate=employee.getCurrentLeaveStartDate();
       LocalDate eedate=employee.getCurrentLeaveEndDate();
       Date estartdate= Date.from(esdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
       Date eenddate= Date.from(eedate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        LocalDate sdate=request.getStartDate();
        LocalDate edate=request.getEndDate();
        Date startdate= Date.from(sdate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date enddate= Date.from(edate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return ( estartdate.compareTo(startdate) * startdate.compareTo(eenddate) >= 0 ||
                estartdate.compareTo(enddate) * enddate.compareTo(eenddate) >= 0);*/
    }

    public LeaveResponse apply(Employee employee, LeaveRequest request, CompOff compOff) {

        if (request.getStartdate().isAfter(request.getEndDate())) {
            throw new IllegalArgumentException("Date is not properly mentioned");
        }
        if(overlappingDates(employee,request))
            throw new IllegalArgumentException("Already holiday has been taken for that time period mentioned");

        if (request.getType() == LeaveType.PaternityLeave) {
            if (employee.getGender() == Gender.FEMALE)
                throw new IllegalArgumentException("Gender and Leave Type Mismatch");
            if (employee.getPaternityLeave() > 2)
                throw new IllegalArgumentException("Exceeded max. paternity leaves");
            if (employee.getPaternityLeave() < 2) {
                employee.setPaternityLeave(employee.getPaternityLeave() + 1);
                employee.setLeavesAtPresent(request.getStartdate(),request.getEndDate());
                return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");
            }
        }

        if (request.getType() == LeaveType.MaternityLeave) {
            if (employee.getGender() == Gender.MALE)
                throw new IllegalArgumentException("Gender and Leave Type Mismatch");
            if (employee.getMaternityLeave() > 2)
                throw new IllegalArgumentException("Exceeded max. maternity leaves");
            if(employee.getMaternityLeave() < 2){
                employee.setPaternityLeave(employee.getPaternityLeave() + 1);
                employee.setLeavesAtPresent(request.getStartdate(),request.getEndDate());
                return new LeaveResponse(LeaveStatus.ACCEPTED, "Leave granted as no. of children less than 2");
            }
        }
        int noOfDays = noOfActualHolidays(request.getStartdate(), request.getEndDate());

        if(request.getType() == LeaveType.CompOff){
            CompOff compoff = new CompOff();
            compoff.compOffLeaveGrant(employee, noOfDays);
        }

        if (balanceHolidaysLeft(employee, LocalDate.now()) > noOfDays) {
            if (noOfDays <= 2) {
                employee.setLeavesAtPresent(request.getStartdate(),request.getEndDate());
                LeaveResponse leaveResponse = new LeaveResponse(LeaveStatus.ACCEPTED, "Accepted due to a valid reason");
                return leaveResponse;
            } else
                return new LeaveResponse(LeaveStatus.REJECTED, "Rejected due to more no of days i.e more than 2 days of leave");

        } else {
            throw new IllegalArgumentException("No balance leaves left");
        }

    }

}

