package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MONTHS;

public class LeaveAcuralManager {
    private EmployeeStore empStore;
    public LeaveAcuralManager(EmployeeStore empStore){
        this.empStore = empStore;
    }
    public void creditLeaves(EmployeeStore empStore){
        for ( Map.Entry<Integer, Employee> entry : empStore.employees.entrySet()) {
            Employee employee = entry.getValue();
            int dayJoining=employee.getJoiningDate().getDayOfMonth();
            int dayTday=LocalDate.now().getDayOfMonth();
            int noOfMonths= (int) MONTHS.between(employee.getJoiningDate(), LocalDate.now());
            int leaveBalance=2*(noOfMonths);
            if(dayJoining>15)
                leaveBalance-=1;
         //   if((employee.getGender()== Gender.FEMALE)?checkForMaternityLeave(employee):checkForPaternityLeave(employee)){
         //       continue;
            }
         //   employee.setBalanceLeaves(employee.getBalanceLeaves()+2);

        }
    }
    /*private boolean checkForMaternityLeave(Employee employee){
        if(MONTHS.between((employee.getLeavesAtPresent()),employee.getCurrentLeaveEndDate())==6){
            return true;
        }
        return false;
    }
    private boolean checkForPaternityLeave(Employee employee){
        if(MONTHS.between((employee.getCurrentLeaveStartDate()),employee.getCurrentLeaveEndDate())==1){
            return true;
        }
        return false;
    }
    */
