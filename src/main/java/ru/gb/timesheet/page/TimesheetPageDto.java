package ru.gb.timesheet.page;

import lombok.Data;


@Data
public class TimesheetPageDto {

  private String projectName;
  private String id;
  private Long projectId;
  private String minutes;
  private String createdAt;

}
