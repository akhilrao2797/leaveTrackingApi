package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PublicHolidays {
    private ArrayList<LocalDate> publicHolidayList = new ArrayList<LocalDate>();

    public PublicHolidays() {
        publicHolidayList.add(LocalDate.of(2019,1,15));
        publicHolidayList.add(LocalDate.of(2019,1,26));
        publicHolidayList.add(LocalDate.of(2019,5,1));
        publicHolidayList.add(LocalDate.of(2019,7,10));
        publicHolidayList.add(LocalDate.of(2019,8,26));
        publicHolidayList.add(LocalDate.of(2019,8,29));
        publicHolidayList.add(LocalDate.of(2019,9,16));
        publicHolidayList.add(LocalDate.of(2019,10,2));
        publicHolidayList.add(LocalDate.of(2019,10,20));
        publicHolidayList.add(LocalDate.of(2019,12,25));
    }
    public int OverlappingPublicHolidays(int holidays, LeaveRequest request){
        for(LocalDate date: publicHolidayList){
            if(request.getStartdate().getMonthValue() >= date.getMonthValue() && request.getEndDate().getMonthValue() <= date.getMonthValue())
            if(request.getStartdate().getDayOfMonth() >= date.getDayOfMonth() && request.getEndDate().getDayOfMonth() <= date.getDayOfMonth())
            {
                holidays-=1;
            }
        }
        return holidays;
    }
}
