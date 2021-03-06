package com.hashedin.huleavetracking;

import org.junit.Test;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class LeaveManagerTest {

    // Start Date less than end date
    @Test(expected = IllegalArgumentException.class)
    public void TestStartDateLessThanEndDate(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(12),LocalDate.now().minusDays(13),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("start date and end date not perfect",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void TestStartDateMoreThanEndDate(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(12),LocalDate.now().plusDays(13),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("start date and end date not perfect",LeaveStatus.ACCEPTED,response.getStatus());
    }

    // start date less than current date
    @Test(expected = IllegalArgumentException.class)
    public void TestStartDateLessThanCurrentDate(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().minusDays(2),LocalDate.now().plusDays(3),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("start date is less than today's date",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void TestStartDateMoreThanCurrentDate(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("start date is less than today's date",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void MaleApplyingForMaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.MaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("male applying for maternity leave",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void MaleApplyingForPaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusMonths(1),LeaveType.PaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("male applying for maternity leave",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FemaleApplyingForPaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusYears(2),LocalDate.now().plusYears(2).plusDays(5),LeaveType.PaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("Female cant apply for paternity leave",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void FemaleApplyingForMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusMonths(6),LeaveType.MaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("Female cant apply for paternity leave",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void MaleExceededNoOfPaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        employee.setPaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusMonths(1),LeaveType.PaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("Being a father for the third time or more",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void MaleExceededPaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        employee.setPaternityLeave(1);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusMonths(1),LeaveType.PaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("Being a father for the second time or less",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FemaleExceededNoOfMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        employee.setMaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusMonths(6),LeaveType.MaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("Female conceiving for the third time or more",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FemaleWorkingLessThan180DaysDeniedMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusDays(150));
        employee.setMaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusMonths(6),LeaveType.MaternityLeave,LeaveOptions.blanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("Female conceiving for the third time or more",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void balanceNoOfHolidaysAreLess(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusDays(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(4),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request,employee.getCompOff());
        assertEquals("No required balance holidays ",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void leaveAlreadyExistingOnThatDate(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusDays(2));
        employee.setLeavesAtPresent(LocalDate.now(),LocalDate.now().plusDays(4));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request,employee.getCompOff());
        assertEquals("Leave already taken on the given date",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void compOffLeave(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.CompOff,LeaveOptions.nonBlanketCoverage);
        employee.getCompOff().setLogWorkDays(0);
        LeaveResponse response = manager.apply(employee, request,employee.getCompOff());
        assertEquals("Compoff leave for no logged worked",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void compOffLeaveOnDifferentMonth(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.CompOff,LeaveOptions.nonBlanketCoverage);
        employee.getCompOff().setLogWorkDays(1);
        employee.getCompOff().month = 5;
        LeaveResponse response = manager.apply(employee, request,employee.getCompOff());
        assertEquals("Asking for a compOff leave on a different month",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void compOffLeaveOnSameMonth(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.CompOff,LeaveOptions.nonBlanketCoverage);
        employee.getCompOff().logExtraWorkHours(LocalDateTime.of(2019,2,16,8,0),LocalDateTime.of(2019,2,16,18,0));
        LeaveResponse response = manager.apply(employee, request,employee.getCompOff());
        assertEquals("Asking for a compOff leave on a different month",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void compOffLeaveDaysMoreThanLoggedWorkDays(){
        Employee employee = new Employee(10,Gender.FEMALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(4),LeaveType.CompOff,LeaveOptions.blanketCoverage);
        employee.getCompOff().logExtraWorkHours(LocalDateTime.now(), LocalDateTime.now().plusHours(10));
        LeaveResponse response = manager.apply(employee, request,employee.getCompOff());
        assertEquals("leaves are more than ",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void JoiningAfterHalfAMonthWith2daysLeaveRequest(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now());
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(1),LocalDate.now().plusDays(3),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("start date and end date not perfect",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void JoiningAfterHalfAMonthWith1dayLeaveRequest(){
        Employee employee = new Employee(11,Gender.MALE , LocalDate.now());
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("leaves are given after that month's work",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test
    public void JoiningAfterAMonthWith1dayLeaveRequest(){
        Employee employee = new Employee(11,Gender.MALE , LocalDate.now().minusMonths(1));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(1),LocalDate.now().plusDays(2),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("leaves are given after that month's work",LeaveStatus.ACCEPTED,response.getStatus());
    }

    @Test
    public void OptionalLeaveTaken(){
        Employee employee = new Employee(10,Gender.MALE , LocalDate.now().minusYears(2));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusMonths(1).plusDays(23),LocalDate.now().plusMonths(1).plusDays(26),LeaveType.OutOfOffice,LeaveOptions.nonBlanketCoverage);
        LeaveResponse response = manager.apply(employee, request ,employee.getCompOff());
        assertEquals("start date and end date perfect and optional leave granted",LeaveStatus.ACCEPTED,response.getStatus());
    }

}