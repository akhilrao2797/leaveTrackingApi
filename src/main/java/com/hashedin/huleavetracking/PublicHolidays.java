package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicHolidays {
    private ArrayList<LocalDate> publicHolidayList = new ArrayList<>();
    //private ArrayList<LocalDate> optionalLeaves = new ArrayList<>();
    private Map<Integer, OptionalLeaves> optionalLeaves = new HashMap<>();
    private Employee employee;
    private LeaveRequest request;

    public PublicHolidays(Employee employee, LeaveRequest request) {
        this.employee = employee;
        this.request = request;
        int year = request.getStartdate().getYear();
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
        OptionalLeaves optionalLeavesDate1 = new OptionalLeaves(LocalDate.of(year, 8, 10), LocalDate.of(year, 4, 13));
        optionalLeaves.put(1, optionalLeavesDate1);
        OptionalLeaves optionalLeavesDate2 = new OptionalLeaves(LocalDate.of(year, 7, 11), LocalDate.of(year, 1, 23));
        optionalLeaves.put(2, optionalLeavesDate2);
    }

    public int OverlappingPublicHolidays(int holidays) {
        for (LocalDate date : publicHolidayList) {
            if (!(date.getDayOfWeek().toString().equalsIgnoreCase("SATURDAY") || date.getDayOfWeek().toString().equalsIgnoreCase("SUNDAY")))
                if (request.getStartdate().isBefore(date.minusDays(1)) && request.getEndDate().isAfter(date.plusDays(1))) {
                    holidays -= 1;
                }
        }
        for (Map.Entry<Integer, OptionalLeaves> entry : optionalLeaves.entrySet()) {
            if (!entry.getValue().isUsed()) {
                if (!(entry.getValue().getLocalDate1().getDayOfWeek().toString().equalsIgnoreCase("SATURDAY") || entry.getValue().getLocalDate1().toString().equalsIgnoreCase("SUNDAY")))
                    if (request.getStartdate().isBefore(entry.getValue().getLocalDate1().minusDays(1)) && request.getStartdate().isBefore(entry.getValue().getLocalDate1().minusDays(1))) {
                        employee.setTakenOptionalLeave(!employee.isTakenOptionalLeave());
                        holidays -= 1;
                        entry.getValue().setUsed(true);
                    }
                if (!(entry.getValue().getLocalDate2().getDayOfWeek().toString().equalsIgnoreCase("SATURDAY") || entry.getValue().getLocalDate2().toString().equalsIgnoreCase("SUNDAY")))
                    if (request.getStartdate().isBefore(entry.getValue().getLocalDate2().minusDays(1)) && request.getStartdate().isBefore(entry.getValue().getLocalDate2().minusDays(1))) {
                        employee.setTakenOptionalLeave(!employee.isTakenOptionalLeave());
                        holidays -= 1;
                        entry.getValue().setUsed(true);
                    }
            }
        }
        return holidays;
    }
}