package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private String option;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveRequest(){}

    public LeaveRequest(int employeeId, LocalDate startDate, LocalDate endDate,String type, String option) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.option = option;
    }
}
