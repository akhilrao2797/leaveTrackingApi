package com.hashedin.huleavetracking;

import java.time.LocalDate;

public class LeaveRequest {
    private long employeeId;
    private LocalDate startdate;
    private LocalDate endDate;

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    private LeaveType type;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }



    public LeaveRequest(Employee employee, LocalDate startdate, LocalDate endDate,LeaveType type) {
        //this.employeeId = employeeId;
        this.startdate = startdate;
        this.endDate = endDate;
        this.type = type;
    }
}
