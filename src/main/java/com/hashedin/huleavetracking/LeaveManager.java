package com.hashedin.huleavetracking;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class LeaveManager {

    // counts the actual no of holidays excluding the weekends
    public int noOfActualHolidays(LeaveRequest request,Employee employee) {
        PublicHolidays holidaymanager =new PublicHolidays(employee,request);
        if (request.getOption() == "blanketCoverage") {
            Period period = Period.between(request.getStartDate(), request.getEndDate());
            return period.getDays();
        } else {
            final DayOfWeek startW = request.getStartDate().getDayOfWeek();
            final DayOfWeek endW = request.getEndDate().getDayOfWeek();
            final int days = (int) DAYS.between(request.getStartDate(),
                    request.getEndDate());
            final int daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);
            int holidays = daysWithoutWeekends;
            if(startW == DayOfWeek.SUNDAY ) {
                holidays += 1;
            }
            if(endW == DayOfWeek.SUNDAY ) {
                holidays += 1;
            }

            return holidaymanager.overlappingPublicHolidays(holidays);
        }
    }

    private boolean overlappingDates(Employee employee, LeaveRequest request){
        if(employee.getStartLeaveDate() != null || employee.getEndLeaveDate() != null) {
            LocalDate startLeaveDate = employee.getStartLeaveDate();
            LocalDate endLeaveDate = employee.getEndLeaveDate();
            Date startDate = Date.from(startLeaveDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            Date enddate = Date.from(endLeaveDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

            LocalDate requestStartDate = request.getStartDate();
            LocalDate requestEndDate = request.getEndDate();
            Date requestedStartDate=Date.from(requestStartDate.atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant());
            Date requestedEndDate = Date.from(requestEndDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            return (startDate.compareTo(requestedStartDate) * requestedStartDate.compareTo(enddate) >= 0 ||
                    startDate.compareTo(requestedEndDate) * requestedEndDate.compareTo(enddate) >= 0);
        }
        else
        {
            return false;
        }

//            if(request.getStartdate().isAfter(employee.getStartLeaveDate())
//                    && request.getEndDate().isBefore(employee.getEndLeaveDate())) {
//                return false;
//            }
//            if(request.getStartdate().isBefore(employee.getStartLeaveDate())
//                    && request.getEndDate().isBefore(employee.getEndLeaveDate())
//                    && request.getStartdate().isBefore(employee.getEndLeaveDate())){
//                return false;
//            }
//            if(request.getStartdate().isBefore(employee.getStartLeaveDate())
//                    && request.getEndDate().isAfter(employee.getEndLeaveDate())){
//                return false;
//            }
//            if(request.getStartdate().isAfter(employee.getStartLeaveDate())
//                    && request.getEndDate().isAfter(employee.getEndLeaveDate())
//                    && request.getEndDate().isBefore(employee.getEndLeaveDate())){
//                return false;
//            }
//        return true;
    }

    public LeaveResponse apply(Employee employee, LeaveRequest request) {
//        System.out.println(employee.getEmployeeId());
//        System.out.println(request.getStartDate()+" "+request.getEndDate()+" "+request.getType());

        if (request.getStartDate().isBefore(LocalDate.now())) {
            return new LeaveResponse(LeaveStatus.REJECTED,"Date is not properly mentioned");
        }

        if (request.getStartDate().isAfter(request.getEndDate())) {
            return new LeaveResponse(LeaveStatus.REJECTED,"Date is not properly mentioned");
        }

        if (request.getType().equalsIgnoreCase("PaternityLeave")) {
            PaternityLeave maternityLeave =  new PaternityLeave();
            LeaveResponse response = maternityLeave.paternityLeaveGrant(employee,request);
            return response;
        }

        if (request.getType().equalsIgnoreCase("MaternityLeave")) {
            MaternityLeave maternityLeave =  new MaternityLeave();
            LeaveResponse response = maternityLeave.maternityLeaveGrant(employee,request);
            System.out.println(response);
            return response;
        }

        int noOfDays = noOfActualHolidays(request,employee);
        if (DAYS.between(employee.getJoiningDate(),request.getStartDate()) < 15) {
            return new LeaveResponse(LeaveStatus.REJECTED,"No leave balance because of your new existance as " +
                    "employee");
        }
        if (DAYS.between(employee.getJoiningDate(),request.getStartDate()) > 15
                && DAYS.between(employee.getJoiningDate(),request.getStartDate()) < 30
                && noOfDays>2) {
            return new LeaveResponse(LeaveStatus.REJECTED,"No leave balance because of your new existance as " +
                    "employee");
        }

        if(request.getType().equalsIgnoreCase("CompOff")){
            CompOff compOff = new CompOff(employee);
            return compOff.compOffLeaveGrant(employee, noOfDays,request);
        }

        if(overlappingDates(employee,request)) {
            return new LeaveResponse(LeaveStatus.REJECTED, "Leave already granted");
        }
        if (employee.getBalanceLeaves() >= noOfDays) {
            if (noOfDays <= 2) {
                employee.setStartLeaveDate(request.getStartDate());
                employee.setEndLeaveDate(request.getEndDate());
                employee.setBalanceLeaves(employee.getBalanceLeaves()- noOfDays);
                LeaveResponse leaveResponse = new LeaveResponse(LeaveStatus.ACCEPTED, "Accepted due to " +
                        "a valid reason");
                return leaveResponse;
            } else
            { return new LeaveResponse(LeaveStatus.REJECTED, "Rejected due to more no of days i.e more" +
                    " than 2 days of leave");}

        } else {
            return new LeaveResponse(LeaveStatus.REJECTED,"No balance leaves");
        }

    }

}

