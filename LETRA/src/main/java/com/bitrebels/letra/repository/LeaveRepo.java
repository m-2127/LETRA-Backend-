package com.bitrebels.letra.repository;

import com.bitrebels.letra.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {
//    int countByStartDateAfterAndFinishDateBefore(LocalDate startDate, LocalDate currentDate);
//    List<Leave> findByEmployeeAndDatesBetween(Employee employee, LocalDate startDate, LocalDate endDate);
//    int countLeavesByDatesBetween(LeaveDates startDate, LeaveDates endDate);
//    List<Leave> findByLeaveDates_Date(LocalDate localDate);
    Set<Leave> findByLeaveDates_DateBetweenAndEmployeeAndReportingManager(
            LocalDate startDate, LocalDate endDate, Employee employee , ReportingManager rm);

    Set<Leave> findByLeaveDates_DateBetween(LocalDate startDate, LocalDate endDate);

    Set<Leave> findByLeaveDates_DateBetweenAndReportingManager(
            LocalDate startDate, LocalDate endDate, ReportingManager rm);

    Set<Leave> findByLeaveDates_DateBetweenAndEmployee(
            LocalDate startDate, LocalDate endDate, Employee employee);

    Leave findLeaveByLeaveRequest(LeaveRequest leaveRequest);

    List<Leave> findByReportingManagerAndStatus(ReportingManager reportingManager, LeaveStatus status);
}
