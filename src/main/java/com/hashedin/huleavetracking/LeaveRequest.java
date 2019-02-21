package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int leaveRequestId;
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private LeaveType type;
    @Enumerated(EnumType.STRING)
    private LeaveOptions option;

    public LeaveOptions getOption() {return option;}

    public void setOption(LeaveOptions option) {this.option = option; }

    public LeaveType getType() { return type;}

    public void setType(LeaveType type) {this.type = type;}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
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
