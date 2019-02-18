package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.HashMap;

public class Employee {
    private int employeeId;
    private Gender gender;
    private int holidaysLeft;
    private int maternityLeave=0;
    private int paternityLeave=0;
    public HashMap<String,Integer> month= new HashMap<String,Integer>();

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

    public HashMap<String, Integer> getMonth() {
        return month;
    }

    public void setMonth(HashMap<String, Integer> month) {
        month.put("January", 2);
        month.put("February", 2);
        month.put("March", 2);
        month.put("April", 2);
        month.put("May", 2);
        month.put("June", 2);
        month.put("July", 2);
        month.put("August",2);
        month.put("September",2);
        month.put("October",2);
        month.put("November",2);
        month.put("December",2);
    }

    public Employee(int employeeId, Gender gender, int holidaysLeft, int extraWorkHours, int compOffBalance) {
        this.employeeId = employeeId;
        this.gender = gender;
        this.holidaysLeft = holidaysLeft;
        this.extraWorkHours = extraWorkHours;
        this.compOffBalance = compOffBalance;
    }

    public int getExtraWorkHours() {
        return extraWorkHours;
    }

    public void setExtraWorkHours(int extraWorkHours) {
        this.extraWorkHours = extraWorkHours;
    }

    public int getCompOffBalance() {
        return compOffBalance;
    }

    public void setCompOffBalance(int compOffBalance) {
        this.compOffBalance = compOffBalance;
    }

    private int extraWorkHours;
    private int compOffBalance;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getHolidaysLeft() {
        return holidaysLeft;
    }

    public void setHolidaysLeft(int holidaysLeft) {
        this.holidaysLeft = holidaysLeft;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }


    public static void main(String args[]){
        Employee employee = new Employee(10,Gender.MALE, 20,20,0);
        LeaveRequest request = new LeaveRequest(employee , LocalDate.now(),LocalDate.now().plusDays(4),LeaveType.OutOfOffice);
        LeaveManager manager = new LeaveManager();
        LeaveResponse apply = manager.apply(employee, request);
        System.out.println(apply.getResponse());
    }

}
