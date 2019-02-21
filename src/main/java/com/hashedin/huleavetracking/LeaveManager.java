package com.hashedin.huleavetracking;

import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class LeaveManager {

    // counts the actual no of holidays excluding the weekends
    public int noOfActualHolidays(LeaveRequest request,PublicHolidays holidaymanager) {
        if (request.getOption() == LeaveOptions.blanketCoverage) {
            Period period = Period.between(request.getStartdate(), request.getEndDate());
            return period.getDays();
        } else {
            final DayOfWeek startW = request.getStartdate().getDayOfWeek();
            final DayOfWeek endW = request.getEndDate().getDayOfWeek();
            final int days = (int) ChronoUnit.DAYS.between(request.getStartdate(), request.getEndDate());
            final int daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);
            int holidays = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
            return holidaymanager.OverlappingPublicHolidays(holidays);
        }
    }

   private boolean overlappingDates(Employee employee, LeaveRequest request){

            if(request.getStartdate().isAfter(employee.getStartLeaveDate()) && request.getEndDate().isBefore(employee.getEndDate())) {
                return false;
            }
            if(request.getStartdate().isBefore(employee.getStartLeaveDate()) && request.getEndDate().isBefore(employee.getEndDate()) && request.getStartdate().isBefore(employee.getEndDate())){
                return false;
            }
            if(request.getStartdate().isBefore(employee.getStartLeaveDate()) && request.getEndDate().isAfter(employee.getEndDate())){
                return false;
            }
            if(request.getStartdate().isAfter(employee.getStartLeaveDate()) && request.getEndDate().isAfter(employee.getEndDate()) && request.getEndDate().isBefore(employee.getEndDate())){
                return false;
            }
        return true;
    }

    public LeaveResponse apply(Employee employee, LeaveRequest request) {
        PublicHolidays holidaymanager =new PublicHolidays(employee,request);
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

        int noOfDays = noOfActualHolidays(request,holidaymanager);

        if(request.getType() == LeaveType.CompOff){
            CompOff compOff = new CompOff(employee);
            compOff.compOffLeaveGrant(employee, noOfDays);
        }

        if(!overlappingDates(employee,request))
            throw new IllegalArgumentException("Already holiday has been taken for that time period mentioned");
        if (employee.getBalanceLeaves() >= noOfDays) {
            if (noOfDays <= 2) {
                employee.setStartLeaveDate(request.getStartdate());
                employee.setEndDate(request.getEndDate());
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

