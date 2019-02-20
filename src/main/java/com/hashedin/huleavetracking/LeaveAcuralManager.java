package com.hashedin.huleavetracking;

import java.time.LocalDate;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MONTHS;

public class LeaveAcuralManager {
    private EmployeeStore empStore;

    public LeaveAcuralManager(EmployeeStore empStore) {
        this.empStore = empStore;
    }

    public void creditLeaves(EmployeeStore empStore) {
        for (Employee employee : empStore.getEmployees()) {
            int dayOfJoining = employee.getJoiningDate().getDayOfMonth();
            int noOfMonths = (int) MONTHS.between(employee.getJoiningDate(), LocalDate.now());
            int leaveBalance = 2 * (noOfMonths);
            if (dayOfJoining > 15 && employee.getJoiningDate().getMonth() == LocalDate.now().getMonth() && employee.getJoiningDate().getYear() == LocalDate.now().getYear())
                leaveBalance -= 1;
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
