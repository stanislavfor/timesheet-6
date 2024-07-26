package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/projects")
public class ProjectController {

  private final ProjectService service;

  public ProjectController(ProjectService service) {
    this.service = service;
  }

    
  @Operation(summary = "Получить проект по ID", description = "Возвращает проект с указанным ID")
  @GetMapping("/{id}")
  public ResponseEntity<Project> get(
      @Parameter(description = "ID проекта", required = true) @PathVariable Long id) {
    return service.findById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Получить таймшиты проекта", description = "Возвращает список таймшитов для проекта с указанным ID")
  @GetMapping("/{id}/timesheets")
  public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(service.getTimesheets(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Получить все проекты", description = "Возвращает список всех проектов")
  @GetMapping
  public ResponseEntity<List<Project>> getAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @Operation(summary = "Создать новый проект", description = "Создает новый проект и возвращает его")  
  @PostMapping
  public ResponseEntity<Project> create(
      @Parameter(description = "Проект для создания", required = true) @RequestBody Project project) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
  }

  @Operation(summary = "Удалить проект", description = "Удаляет проект с указанным ID")    
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @Parameter(description = "ID проекта", required = true) @PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
    

    

}
