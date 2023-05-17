package com.payeshgaran.workflow.controller;

import com.payeshgaran.workflow.model.QuestionnaireInboxItem;
import com.payeshgaran.workflow.model.WorkflowStep;
import com.payeshgaran.workflow.service.WorkflowService;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/start-{processInstanceId}")
    public List<WorkflowStep> getWorkflowStepsFor(@PathVariable String processInstanceId){
        return workflowService.getWorkflowStepsFor(processInstanceId);
    }

    @GetMapping("/test-{user}")
    public void claimTask(@PathVariable String user){
    }

}
