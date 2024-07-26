package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    
    
    
    List<Timesheet> findByProjectId(Long projectId);


    List<Timesheet> findByCreatedAtBeforeAndCreatedAtAfter(LocalDate createdAtBefore, LocalDate createdAtAfter);
    
    List<Timesheet> findByEmployeeId(Long employeeId);
}


