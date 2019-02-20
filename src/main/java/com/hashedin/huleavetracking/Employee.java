package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class Employee {
    private int employeeId;
    private Gender gender;
    private int maternityLeave=0;
    private int paternityLeave=0;
    private CompOff compOff = new CompOff();
    private LocalDate joiningDate;
    private int balanceLeaves;

    public LocalDate getJoiningDate() { return joiningDate; }

    public int getEmployeeId() { return employeeId;}

    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}


    public CompOff getCompOff() {
        return compOff;
    }

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
        if(joiningDate.getDayOfMonth() < 15)
            balance = duration * 2;
        else
            balance = duration * 2 - 1;
        this.balanceLeaves = balance;
    }


    public Map<String,Integer> month= new LinkedHashMap<String,Integer>();

    public Map<LocalDate, LocalDate> getLeavesAtPresent() {
        return leavesAtPresent;
    }

    public void setLeavesAtPresent(LocalDate startDate, LocalDate endDate) {
        leavesAtPresent.put(startDate,endDate);
    }

    public Map<LocalDate,LocalDate> leavesAtPresent = new LinkedHashMap<LocalDate, LocalDate>();

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
    }

    public Employee(int employeeId, Gender gender, LocalDate joiningDate) {
        this.employeeId = employeeId;
        this.gender = gender;
        this.joiningDate = joiningDate;
        this.setMonth();
        this.setBalanceLeaves(this.getBalanceLeaves());
    }

    public Gender getGender() {
        return gender;
    }

    public static void main(String args[]){
        EmployeeStore empStore = new EmployeeStore();
        Employee employee1 = new Employee(10,Gender.MALE, LocalDate.now().minusYears(2));
        empStore.addEmployee(employee1);
        Employee employee2 = new Employee(11,Gender.MALE, LocalDate.now().minusYears(2));
        empStore.addEmployee(employee2);
        Employee employee3 = new Employee(12,Gender.FEMALE, LocalDate.now().minusYears(2));
        empStore.addEmployee(employee3);
        Employee employee4 = new Employee(14,Gender.MALE, LocalDate.now().minusYears(2));
        empStore.addEmployee(employee4);
        Employee employee5 = new Employee(15,Gender.MALE, LocalDate.now().minusYears(2));
        empStore.addEmployee(employee5);
        LeaveRequest request = new LeaveRequest(employee1 , LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.OutOfOffice,LeaveOptions.blanketCoverage);
        LeaveManager manager = new LeaveManager();
        System.out.println(employee1.getBalanceLeaves());
        LeaveResponse apply = manager.apply(employee1, request,employee1.compOff);
        System.out.println(apply.getResponse());
    }

}
