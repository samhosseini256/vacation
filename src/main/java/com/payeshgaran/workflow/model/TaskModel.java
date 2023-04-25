package com.payeshgaran.workflow.model;

import lombok.Getter;
import lombok.Setter;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;

@Getter
@Setter
public class TaskModel {

    private String id;
    private String name;
    private String assignee;

}