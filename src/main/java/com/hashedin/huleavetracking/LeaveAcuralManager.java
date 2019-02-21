package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MONTHS;

public class LeaveAcuralManager {
    private EmployeeStore empStore;
    private int remainingLeaves;

    public LeaveAcuralManager(EmployeeStore empStore) {
        this.empStore = empStore;
        this.remainingLeaves=0;
    }

    public void creditLeaves(EmployeeStore empStore) {

        for (Employee employee : empStore.getEmployees()) {
            int dayOfJoining = employee.getJoiningDate().getDayOfMonth();
            int noOfMonths = (int) MONTHS.between(employee.getJoiningDate(), LocalDate.now());
            int leaveBalance = 2 * (noOfMonths);
            if (dayOfJoining > 15 && employee.getJoiningDate().getMonth() == LocalDate.now().getMonth() && employee.getJoiningDate().getYear() == LocalDate.now().getYear())
                leaveBalance -= 1;
            if(LocalDate.now() == LocalDate.of(LocalDate.now().getYear(),1,1)) {
                remainingLeaves=leaveBalance-12;
                leaveBalance = 10;
            }
            if (employee.isOnMaternityOrPaternityLeave()) {
                for(Map.Entry<LocalDate,LocalDate> entryLeave : employee.getLeavesAtPresent().entrySet())
                    if(entryLeave.getValue() == LocalDate.now() && LocalDate.now().getDayOfMonth() >15)
                        leaveBalance-=1;
                    else if(entryLeave.getValue() == LocalDate.now() && LocalDate.now().getDayOfMonth() <15)
                        leaveBalance-=0;
                    else
                        continue;
            }
            employee.setBalanceLeaves(employee.getBalanceLeaves() + 2);
        }
    }

}
