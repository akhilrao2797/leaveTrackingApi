package com.hashedin.huleavetracking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class CompOff {
    int logWorkDays;
    boolean used;
    int month;
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

    public void logExtraWorkHours(LocalDateTime startTime, LocalDateTime endTime)
    {
        if(startTime.isBefore(endTime)){
            throw new IllegalArgumentException("Time entered is not proper");
        }
        if(ChronoUnit.HOURS.between(startTime,endTime) <= 8){
            throw new IllegalArgumentException("Cant add a new compensation off as the time is not more than 8 hours");
        }
        if(startTime.getDayOfWeek() != endTime.getDayOfWeek()){
            throw new IllegalArgumentException("Work on same day only can be loaded ");
        }
        if(startTime.getDayOfWeek().name() != "SATURDAY" || startTime.getDayOfWeek().name() != "SUNDAY" ){
            throw new IllegalArgumentException("Weekend or public holiday compoff can be loaded");
        }
        logWorkDays += 1;
        Calendar.getInstance().get(Calendar.MONTH);
    }
    public void compOffLeaveGrant(Employee employee, int noOFDays)
    {
        if(logWorkDays < 1 || used == true) {
            throw new IllegalArgumentException("No extra work hours logged");
        }
        if(month != Calendar.getInstance().get(Calendar.MONTH)){
            throw new IllegalArgumentException("extra work days cant be carried forward");
        }
        if(logWorkDays < noOFDays)
            throw new IllegalArgumentException("more number of days asked");

        logWorkDays -= noOFDays;
        if(logWorkDays <= 0){
            used = true;
        }
        else
            used =false;
    }

}
