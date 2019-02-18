package com.hashedin.huleavetracking;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LeaveManagerTest {

    @Test(expected = IllegalArgumentException.class)
    public void TestStartDateLessThanEndDate(){
        Employee employee = new Employee(10,Gender.MALE, 20,20,0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().minusDays(3),LeaveType.OutOfOffice);
        LeaveResponse response = manager.apply(employee, request);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void MaleApplyingForMaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE, 20,20,0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.MaternityLeave);
        LeaveResponse response = manager.apply(employee, request);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FeMaleApplyingForMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE, 20,20,0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.PaternityLeave);
        LeaveResponse response = manager.apply(employee, request);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void MaleExceededPaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE, 20,20,0);
        employee.setPaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.PaternityLeave);
        LeaveResponse response = manager.apply(employee, request);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FemaleExceededMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE, 20,20,0);
        employee.setMaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.MaternityLeave);
        LeaveResponse response = manager.apply(employee, request);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test()
    public void balanceNoOfHolidaysAreLess(){
        Employee employee = new Employee(10,Gender.FEMALE, 20,20,0);
        employee.month.put("JANUARY",0);
        employee.month.put("FEBRUARY",0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(10),LeaveType.OutOfOffice);
        LeaveResponse response = manager.apply(employee, request);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }



}