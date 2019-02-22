package com.hashedin.huleavetracking;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeaveManagerTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private LogExtraWorkedHoursRepository logExtraWorkedHoursRepository;
    private EmployeeStore employeeStore;
    private LeaveManager leaveManager;
    private LeaveStore leaveStore;
    // Start Date less than end date

    @Before
    public void startBeforeTesting(){
        this.employeeStore= new EmployeeStore(employeeRepository,logExtraWorkedHoursRepository);
        this.leaveManager= new LeaveManager();
        this.leaveStore= new LeaveStore(employeeRepository,leaveRepository,employeeStore,leaveManager);
    }

    @Test
    public void testStartDateLessThanEndDate(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().minusDays(13),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void testStartDateMoreThanEndDate(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusDays(13),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    // start date less than current date
    @Test
    public void testStartDateLessThanCurrentDate() {
        employeeRepository.save(new Employee(10, Gender.MALE, LocalDate.now().minusYears(2), 1,
                0, 0, 3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().minusDays(12),
                LocalDate.now().minusDays(13), "OutOfOffice", "nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals("start date and end date not perfect", LeaveStatus.REJECTED, response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void testStartDateMoreThanCurrentDate(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusDays(13),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void maleApplyingForMaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"MaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals("start date and end date not perfect",LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
    }

    @Test
    public void maleApplyingForPaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"PaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void femaleApplyingForPaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.FEMALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"PaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void femaleApplyingForMaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.FEMALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(6),"MaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
    }

    @Test
    public void maleExceededNoOfPaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,3,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"PaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void maleWithLessThanTwoPaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"PaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void femaleExceededNoOfMaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.FEMALE, LocalDate.now().minusYears(2),1,
                0,3,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(6),"PaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void FemaleWorkingLessThan180DaysDeniedMaternityLeave(){
        employeeRepository.save(new Employee(10,Gender.FEMALE, LocalDate.now().minusMonths(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"PaternityLeave","BlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void balanceNoOfHolidaysAreLess(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusMonths(1),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();

    }

    @Test
    public void leaveAlreadyExistingOnThatDate(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),1,
                0,0,40));
        leaveStore.applyLeave(new LeaveRequest(10, LocalDate.now().plusDays(14),
                LocalDate.now().plusDays(16),"OutOfOffice","nonBlanketCoverage"));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(15),
                LocalDate.now().plusMonths(17),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void zeroCompOffLeaveBalance(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),0,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(12),
                LocalDate.now().plusDays(13),"CompOff","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void compOffLeaveOnDifferentMonth(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),2,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(1).plusDays(1),"CompOff","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void compOffLeaveOnSameMonth() {
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusYears(2),2,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now(),
                LocalDate.now().plusDays(1),"CompOff","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void compOffLeaveDaysMoreThanLoggedWorkDays(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now(),2,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now(),
                LocalDate.now().plusDays(10),"CompOff","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void joiningBeforeHalfAMonthWith2daysLeaveRequest(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusDays(1),0,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now(),
                LocalDate.now().plusDays(2),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void joiningBeforeHalfAMonthWith1dayLeaveRequest(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusDays(10),2,
                0,0,3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now(),
                LocalDate.now().plusDays(1),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void joiningBeforeAMonthWith1dayLeaveRequest(){
        employeeRepository.save(new Employee(10,Gender.MALE, LocalDate.now().minusMonths(1),2,
                0,0,1));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now(),
                LocalDate.now().plusDays(1),"OutOfOffice","nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED,response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();
    }

    @Test
    public void optionalLeaveTaken() {
        employeeRepository.save(new Employee(10, Gender.MALE, LocalDate.now().minusYears(2), 2,
                0, 0, 3));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusMonths(1).plusDays(20),
                LocalDate.now().plusMonths(1).plusDays(24), "OutOfOffice", "nonBlanketCoverage");
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED, response.getStatus());
        employeeRepository.deleteAll();
        leaveRepository.deleteAll();

    }

    @Test
    public void logWorkTimeInSufficient() {
        employeeRepository.save(new Employee(10, Gender.MALE, LocalDate.now().minusYears(2), 0,
                0, 0, 3));
        employeeStore.postExtraWorkedHours(10,new LogExtraWorkedHours(10,
                LocalDateTime.now().minusDays(10).minusHours(4),LocalDateTime.now().minusDays(10).plusHours(1)));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4), "CompOff", "nonBlanketCoverage");
        System.out.println(employeeRepository.findById(10).get().getCompOff());
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.REJECTED, response.getStatus());
    }

    // Checks if it is a saturday or sunday and also the time of service given.(>=<  8)
    @Test
    public void logWorkTimeSufficient() {
        employeeRepository.save(new Employee(10, Gender.MALE, LocalDate.now().minusYears(2), 0,
                0, 0, 3));
        employeeStore.postExtraWorkedHours(10,new LogExtraWorkedHours(10,
                LocalDateTime.now().minusDays(5).minusHours(8),LocalDateTime.now().minusDays(5).plusHours(1)));
        LeaveRequest request = new LeaveRequest(10, LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(4), "CompOff", "nonBlanketCoverage");
        System.out.println(employeeRepository.findById(10).get().getCompOff());
        LeaveResponse response = leaveStore.applyLeave(request);
        assertEquals(LeaveStatus.ACCEPTED, response.getStatus());
    }

}