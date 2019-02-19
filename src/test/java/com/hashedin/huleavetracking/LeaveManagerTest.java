package com.hashedin.huleavetracking;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LeaveManagerTest {

    @Test(expected = IllegalArgumentException.class)
    public void TestStartDateLessThanEndDate(){
        Employee employee = new Employee(10,Gender.MALE);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().minusDays(3),LeaveType.OutOfOffice);
        LeaveResponse response = manager.apply(employee, request ,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void MaleApplyingForMaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.MaternityLeave);
        LeaveResponse response = manager.apply(employee, request ,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FeMaleApplyingForMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.PaternityLeave);
        LeaveResponse response = manager.apply(employee, request ,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void MaleExceededPaternityLeave(){
        Employee employee = new Employee(10,Gender.MALE);
        employee.setPaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.PaternityLeave);
        LeaveResponse response = manager.apply(employee, request ,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void FemaleExceededMaternityLeave(){
        Employee employee = new Employee(10,Gender.FEMALE);
        employee.setMaternityLeave(3);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3),LeaveType.MaternityLeave);
        LeaveResponse response = manager.apply(employee, request ,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void balanceNoOfHolidaysAreLess(){
        Employee employee = new Employee(10,Gender.FEMALE);
        employee.month.put("JANUARY",0);
        employee.month.put("FEBRUARY",0);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now().plusDays(2),LocalDate.now().plusDays(10),LeaveType.OutOfOffice);
        LeaveResponse response = manager.apply(employee, request,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void leaveAlreadyExistingOnThatDate(){
        Employee employee = new Employee(10,Gender.FEMALE);
        employee.setLeavesAtPresent(LocalDate.now(),LocalDate.now().plusDays(4));
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.OutOfOffice);
        LeaveResponse response = manager.apply(employee, request,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void compOffLeave(){
        Employee employee = new Employee(10,Gender.FEMALE);
        LeaveManager manager = new LeaveManager();
        LeaveRequest request = new LeaveRequest(employee, LocalDate.now(),LocalDate.now().plusDays(1),LeaveType.CompOff);
        employee.compOff.setLogWorkDays(0);
        LeaveResponse response = manager.apply(employee, request,employee.compOff);
        assertEquals("Error thrown as expected",LeaveStatus.REJECTED,response.getStatus());
    }



}