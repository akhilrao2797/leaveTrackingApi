package com.hashedin.huleavetracking;

import java.time.LocalDate;

//@Entity
public class LeaveRequest {
    //@Id
    private long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType type;

    public LeaveOptions getOption() {return option;}

    public void setOption(LeaveOptions option) {this.option = option; }

    private LeaveOptions option;

    public LeaveType getType() { return type;}

    public void setType(LeaveType type) {this.type = type;}

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartdate() {
        return startDate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startDate = startdate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveRequest(Employee employee, LocalDate startdate, LocalDate endDate,LeaveType type, LeaveOptions option) {
        this.employeeId = employee.getEmployeeId();
        this.startDate = startdate;
        this.endDate = endDate;
        this.type = type;
        this.option = option;
    }
}
