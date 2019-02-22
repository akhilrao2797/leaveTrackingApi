package com.hashedin.huleavetracking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;


public class CompOff {

    private Employee employee;
    private int logWorkDays;
    private boolean used;
    private int month;

    public CompOff(Employee employee) {
        this.logWorkDays=employee.getCompOff();
        this.month=employee.getCompOffValidMonth();
        this.employee = employee;
        this.used=false;
    }

    public int getLogWorkDays() {
        return logWorkDays;
    }

    public void setLogWorkDays(int logWorkDays) {
        this.logWorkDays = logWorkDays;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void logExtraWorkHours(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
//            throw new IllegalArgumentException("Time entered is not proper");
            return;
        }
        if (ChronoUnit.HOURS.between(startTime, endTime) < 8) {
            return;
//         throw new IllegalArgumentException("Cant add a new compensation off as the time is not more than 8 hours");
        }
        if (startTime.getDayOfWeek() != endTime.getDayOfWeek()) {
            return;
//            throw new IllegalArgumentException("Work on same day only can be loaded ");
        }
        if ((endTime.getDayOfWeek().name().toUpperCase().equalsIgnoreCase("SATURDAY")) ||
                (endTime.getDayOfWeek().name().equalsIgnoreCase("SUNDAY"))) {
            logWorkDays += 1;
            this.month = endTime.getMonth().getValue();
            employee.setCompOffValidMonth(month);
            employee.setCompOff(logWorkDays);
        } else {
            return;
//            throw new IllegalArgumentException("Weekend or public holiday work is only counted");
        }
    }
    public LeaveResponse compOffLeaveGrant(Employee employee, int noOFDays,LeaveRequest request) {
        System.out.println(this.logWorkDays);
        if (logWorkDays < 1 || used == true) {
            return new LeaveResponse(LeaveStatus.REJECTED,"No extra work hours logged");
        }
        if (month  != request.getStartDate().getMonthValue()) {
            System.out.println(month + " " + Calendar.getInstance().get(Calendar.MONTH));
            return new LeaveResponse(LeaveStatus.REJECTED,"extra work days cant be carried forward");
        }
        if (logWorkDays < noOFDays) {
            return new LeaveResponse(LeaveStatus.REJECTED,"more number of days asked");
        }

        logWorkDays -= noOFDays;
        if (logWorkDays <= 0) {
            used = true;
        } else {
            used = false;
        }
        employee.setCompOff(logWorkDays);
        return new LeaveResponse(LeaveStatus.ACCEPTED,"CompOff leave Accepted");
    }

}
