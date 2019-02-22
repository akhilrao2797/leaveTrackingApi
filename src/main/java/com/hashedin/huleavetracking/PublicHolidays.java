package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.ArrayList;


public class PublicHolidays {

    private ArrayList<LocalDate> publicHolidayList = new ArrayList<>();
    private ArrayList<LocalDate> optionalLeaves = new ArrayList<>();
    //private Map<Integer, OptionalLeaves> optionalLeaves = new HashMap<>();
    private Employee employee;
    private LeaveRequest request;

    public PublicHolidays(Employee employee, LeaveRequest request) {
        this.employee = employee;
        this.request = request;
        int year = request.getStartDate().getYear();
        publicHolidayList.add(LocalDate.of(year, 1, 15));
        publicHolidayList.add(LocalDate.of(year, 1, 26));
        publicHolidayList.add(LocalDate.of(year, 5, 1));
        publicHolidayList.add(LocalDate.of(year, 7, 10));
        publicHolidayList.add(LocalDate.of(year, 8, 26));
        publicHolidayList.add(LocalDate.of(year, 8, 29));
        publicHolidayList.add(LocalDate.of(year, 9, 16));
        publicHolidayList.add(LocalDate.of(year, 10, 2));
        publicHolidayList.add(LocalDate.of(year, 10, 20));
        publicHolidayList.add(LocalDate.of(year, 12, 25));
        optionalLeaves.add(LocalDate.of(year, 8, 10));
        optionalLeaves.add(LocalDate.of(year, 4, 12));
    }

    public int overlappingPublicHolidays(int holidays) {
        for (LocalDate date : publicHolidayList) {
            if (!(date.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY") ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY"))) {
                if (request.getStartDate().isBefore(date.minusDays(1))
                        && request.getEndDate().isAfter(date.plusDays(1))) {
                    holidays -= 1;
                }
            }
        }
        for(LocalDate date:optionalLeaves){
            if(!employee.isTakenOptionalLeave()){
                if(!(date.getDayOfWeek().name().equalsIgnoreCase("Saturday")
                        || date.getDayOfWeek().name().equalsIgnoreCase("Sunday"))){
                    System.out.println("Optional leave considered");
                    employee.setTakenOptionalLeave(true);
                    holidays-=1;
                }
            }
            else{
                break;
            }
        }
        return holidays;
    }
}
