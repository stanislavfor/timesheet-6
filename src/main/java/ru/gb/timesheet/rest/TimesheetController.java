package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

  // GET - получить - не содержит тела
  // POST - create
  // PUT - изменение
  // PATCH - изменение
  // DELETE - удаление

  // @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
  // @DeleteMapping("/timesheets/{id}") // удалить конкретную запись по идентификатору
  // @PutMapping("/timesheets/{id}") // обновить конкретную запись по идентификатору

  private final TimesheetService service;

  public TimesheetController(TimesheetService service) {
    this.service = service;
  }

  @Operation(summary = "Получить таймшит по ID", description = "Возвращает таймшит с указанным ID")
  @GetMapping("/{id}") // получить все
  public ResponseEntity<Timesheet> get(
      @Parameter(description = "ID таймшита", required = true) @PathVariable Long id) {
    return service.findById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Получить все таймшиты", description = "Возвращает список всех таймшитов")
  @GetMapping
  public ResponseEntity<List<Timesheet>> getAll(
    @Parameter(description = "Дата создания до", required = false) @RequestParam(required = false) LocalDate createdAtBefore,
    @Parameter(description = "Дата создания после", required = false) @RequestParam(required = false) LocalDate createdAtAfter
  ) {
    return ResponseEntity.ok(service.findAll(createdAtBefore, createdAtAfter));
  }

  // client -> [spring-server -> ... -> TimesheetController
  //                          -> exceptionHandler(e)
  // client <- [spring-server <- ...

  @Operation(summary = "Создать новый таймшит", description = "Создает новый таймшит и возвращает его")
  @PostMapping // создание нового ресурса
  public ResponseEntity<Timesheet> create(
      @Parameter(description = "Таймшит для создания", required = true) @RequestBody Timesheet timesheet) {
    final Timesheet created = service.create(timesheet);

    // 201 Created
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @Operation(summary = "Удалить таймшит", description = "Удаляет таймшит с указанным ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @Parameter(description = "ID таймшита", required = true) @PathVariable Long id) {
    service.delete(id);

    // 204 No Content
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
    return ResponseEntity.notFound().build();
  }

}
