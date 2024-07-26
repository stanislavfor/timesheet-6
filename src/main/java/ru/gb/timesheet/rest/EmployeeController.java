package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.EmployeeService;
import ru.gb.timesheet.service.TimesheetService;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private final EmployeeService employeeService;
    private final TimesheetService timesheetService;

    public EmployeeController(EmployeeService employeeService, TimesheetService timesheetService) {
        this.employeeService = employeeService;
        this.timesheetService = timesheetService;
        
    }

    @Operation(summary = "Получить сотрудника по ID", description = "Возвращает информацию о сотруднике по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сотрудник найден", content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить всех сотрудников", description = "Возвращает список всех сотрудников")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Employee.class))))
    })
    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }
    
    @Operation(summary = "Создать нового сотрудника", description = "Создает нового сотрудника в системе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Сотрудник создан", content = @Content(schema = @Schema(implementation = Employee.class)))
    })
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.create(employee));
    }
    
    @Operation(summary = "Удалить сотрудника", description = "Удаляет сотрудника по его идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Сотрудник удален"),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить таймшиты сотрудника", description = "Возвращает список таймшитов для указанного сотрудника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Таймшиты найдены", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Timesheet.class)))),
            @ApiResponse(responseCode = "404", description = "Сотрудник не найден")
    })
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new NoSuchElementException("Запись с id=" + id + " не существует"));
        return ResponseEntity.ok(timesheetService.findByEmployeeId(id));
    }
}
