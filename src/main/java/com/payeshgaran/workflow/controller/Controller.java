package com.payeshgaran.workflow.controller;

import com.payeshgaran.workflow.model.QuestionnaireInboxItem;
import com.payeshgaran.workflow.model.WorkflowStep;
import com.payeshgaran.workflow.service.WorkflowService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private WorkflowService workflowService;

    @GetMapping("/start-{processInstanceId}")
    public List<WorkflowStep> getWorkflowStepsFor(@PathVariable String processInstanceId){
        return workflowService.getWorkflowStepsFor(processInstanceId);
    }

}
