package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Employee {
    private int employeeId;
    private Gender gender;
    private int holidaysLeft;
    private int maternityLeave=0;
    private int paternityLeave=0;
    public Map<String,Integer> month= new LinkedHashMap<String,Integer>();

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
        LeaveRequest request = new LeaveRequest(employee , LocalDate.now(),LocalDate.now().plusDays(2),LeaveType.OutOfOffice);
        LeaveManager manager = new LeaveManager();
        employee.setMonth();
        LeaveResponse apply = manager.apply(employee, request);
        System.out.println(apply.getResponse());
    }

}
