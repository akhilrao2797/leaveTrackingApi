package com.hashedin.huleavetracking;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
public class Employee {

    @Id
    private int employeeId;
    private Gender gender;
    private int maternityLeave=0;
    private int paternityLeave=0;
    //private CompOff compOff;
    //public Map<String,Integer> month= new LinkedHashMap<String,Integer>();
    private LocalDate joiningDate;
    private int balanceLeaves;
    private boolean takenOptionalLeave;
    private boolean isOnMaternityOrPaternityLeave;

    public Employee(){}

    public Employee(int employeeId, Gender gender, LocalDate joiningDate) {
        this.employeeId = employeeId;
        this.gender = gender;
        this.joiningDate = joiningDate;
        //this.setMonth();
        this.setBalanceLeaves(this.getBalanceLeaves());
        this.takenOptionalLeave =false;
        this.isOnMaternityOrPaternityLeave = false;
        // this.compOff =new CompOff(this);
        //this.leavesAtPresent = new LinkedHashMap<LocalDate, LocalDate>();
    }

    public LocalDate getStartLeaveDate() {
        return startLeaveDate;
    }

    public void setStartLeaveDate(LocalDate startLeaveDate) {
        this.startLeaveDate = startLeaveDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    private LocalDate startLeaveDate;
    private LocalDate endDate;

    public boolean isTakenOptionalLeave() {
        return takenOptionalLeave;
    }

    public void setTakenOptionalLeave(boolean takenOptionalLeave) {
        this.takenOptionalLeave = takenOptionalLeave;
    }

    public boolean isOnMaternityOrPaternityLeave() {
        return isOnMaternityOrPaternityLeave;
    }

    public void setOnMaternityOrPaternityLeave(boolean onMaternityOrPaternityLeave) {
        isOnMaternityOrPaternityLeave = onMaternityOrPaternityLeave;
    }

    public LocalDate getJoiningDate() { return joiningDate; }

    public int getEmployeeId() { return employeeId;}

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}

    public int getBalanceLeaves() {
        return balanceLeaves;
    }

    public void setBalanceLeaves(int balanceLeaves) {
       /* int balance = balanceLeaves;
        LocalDate date = LocalDate.now();
        for (Map.Entry<String, Integer> entry : this.month.entrySet()) {
            if (date.getMonth().toString() == entry.getKey()) {
                int value = entry.getValue();
                balance += value;
                break;
            }
            balance += entry.getValue();
        }
        */
       int balance;
        int duration = (int) ChronoUnit.MONTHS.between(joiningDate, LocalDate.now());
        if(joiningDate.getDayOfMonth() < 15) {
            balance = duration * 2;
        }
        else {
            balance = duration * 2 - 1;
        }
        this.balanceLeaves = balance;
    }

//    public Map<LocalDate, LocalDate> getLeavesAtPresent() {
//        return leavesAtPresent;
//    }
//
//    public void setLeavesAtPresent(LocalDate startDate, LocalDate endDate) {
//        leavesAtPresent.put(startDate,endDate);
//    }

 //   public Map<LocalDate,LocalDate> leavesAtPresent ;

    public int getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(int maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public int getPaternityLeave() {
        return paternityLeave;
    }

    public void setPaternityLeave(int paternityLeave) {
        this.paternityLeave = paternityLeave;
    }
    public Gender getGender() {
        return gender;
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
