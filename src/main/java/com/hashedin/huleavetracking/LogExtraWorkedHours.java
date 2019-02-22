package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LogExtraWorkedHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int logWorkId;
    private int employeeId;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public LogExtraWorkedHours(){}
    public LogExtraWorkedHours(int employeeId,LocalDateTime startDateTime,LocalDateTime endDateTime){
        this.employeeId=employeeId;
        this.endDateTime=endDateTime;
        this.startDateTime=startDateTime;
    }

}
