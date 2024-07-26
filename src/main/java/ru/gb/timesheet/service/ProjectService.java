package ru.gb.timesheet.service;

import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final TimesheetRepository timesheetRepository;

  public ProjectService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
    this.projectRepository = projectRepository;
    this.timesheetRepository = timesheetRepository;
  }

  public Optional<Project> findById(Long id) {
    return projectRepository.findById(id);
  }

  public List<Project> findAll() {
    return projectRepository.findAll();
  }
    
  public Project create(Project project) {
        return projectRepository.save(project);
    }

//  public Project create(Project project) {
//    return projectRepository.create(project);
//  }

//  public void delete(Long id) {
//    projectRepository.delete(id);
//  }

   
   public void delete(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("'Project' с id = " + id + " не существует");
        }
    }
    
//  public List<Timesheet> getTimesheets(Long id) {
//    if (projectRepository.findById(id).isEmpty()) {
//      throw new NoSuchElementException("'Project' с id = " + id + " не существует");
//    }
//
//    return timesheetRepository.findByProjectId(id);
//  }
//    
    
    public List<Timesheet> getTimesheets(Long id) {
        if (projectRepository.existsById(id)) {
            return timesheetRepository.findByProjectId(id);
        } else {
            throw new NoSuchElementException("'Project' с id = " + id + " не существует");
        }
    }
    
}
