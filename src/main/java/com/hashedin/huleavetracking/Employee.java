package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    private int employeeId;
    private String gender;
    private int maternityLeaveTaken=0;
    private int paternityLeaveTaken=0;
    private LocalDate joiningDate;
    private int balanceLeaves;
    private boolean takenOptionalLeave;
    private boolean isOnMaternityOrPaternityLeave;
    private LocalDate startLeaveDate;
    private LocalDate endLeaveDate;
    private int compOff;

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    private int noOfChildren;


    public Employee(){}

    public Employee(int employeeId, Gender gender, LocalDate joiningDate,int compOff,int noOfChildren,
                    int maternityOrPaternityLeave,int leaveBalance) {
        this.employeeId = employeeId;
        this.gender = String.valueOf(gender);
        this.joiningDate = joiningDate;
        this.setBalanceLeaves(this.getBalanceLeaves());
        this.takenOptionalLeave =false;
        this.isOnMaternityOrPaternityLeave = false;
        this.compOff=compOff;
        this.noOfChildren = noOfChildren;
        this.maternityLeaveTaken = maternityOrPaternityLeave;
        this.paternityLeaveTaken = maternityOrPaternityLeave;
        this.balanceLeaves=leaveBalance;
    }

    public LocalDate getStartLeaveDate() {
        return startLeaveDate;
    }

    public void setStartLeaveDate(LocalDate startLeaveDate) {
        this.startLeaveDate = startLeaveDate;
    }


    public LocalDate getEndLeaveDate() {
        return endLeaveDate;
    }

    public void setEndLeaveDate(LocalDate endLeaveDate) {
        this.endLeaveDate = endLeaveDate;
    }

    public int getCompOff() {
        return compOff;
    }

    public void setCompOff(int compOff) {
        this.compOff = compOff;
    }

    public int getBalanceLeaves() {
        return balanceLeaves;
    }

    public void setBalanceLeaves(int balanceLeaves) {
        this.balanceLeaves = balanceLeaves;
    }

    public LocalDate getJoiningDate() { return joiningDate; }

    public int getEmployeeId() { return employeeId;}

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}

    public int getMaternityLeave() {
        return maternityLeaveTaken;
    }

    public void setMaternityLeave(int maternityLeave) {
        this.maternityLeaveTaken = maternityLeave;
    }

    public int getPaternityLeave() {
        return paternityLeaveTaken;
    }

    public void setPaternityLeave(int paternityLeave) {
        this.paternityLeaveTaken = paternityLeave;
    }
    public String getGender() {
        return gender;
    }

    public boolean isOnMaternityOrPaternityLeave() {
        return isOnMaternityOrPaternityLeave;
    }

    public void setOnMaternityOrPaternityLeave(boolean onMaternityOrPaternityLeave) {
        isOnMaternityOrPaternityLeave = onMaternityOrPaternityLeave;
    }

    public boolean isTakenOptionalLeave() {
        return takenOptionalLeave;
    }

    public void setTakenOptionalLeave(boolean takenOptionalLeave) {
        this.takenOptionalLeave = takenOptionalLeave;
    }



/*
    public void setMonth() {
        month.put("JANUARY", 2);
        month.put("FEBRUARY", 2);
        month.put("MARCH", 2);
        month.put("APRIL", 2);
        month.put("MAY", 2);
        month.put("JUNE", 2);
        month.put("JULY", 2);
        month.put("AUGUST",2);
        month.put("SEPTEMBER",2);
        month.put("OCTOBER",2);
        month.put("NOVEMBER",2);
        month.put("DECEMBER",2);
        setMonthlyLeavesAccordingToJoiningDate();
    }

    private void setMonthlyLeavesAccordingToJoiningDate() {
        LocalDate date = LocalDate.now();
        if (date.getYear() == joiningDate.getYear()) {
            for (Map.Entry<String, Integer> entry : this.month.entrySet()) {
                if (date.getMonth().toString() == entry.getKey()) {
                    if(date.getDayOfMonth() > 16)
                        month.put(entry.getKey(),1);
                    else
                        month.put(entry.getKey(),2);
                }
                month.put(entry.getKey(),0);
            }
        }
    }*/
}
