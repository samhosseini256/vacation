package com.payeshgaran.workflow.service;

import com.payeshgaran.workflow.model.AnswerTask;
import com.payeshgaran.workflow.model.QuestionnaireInboxItem;
import com.payeshgaran.workflow.model.QuestionnaireSearchParams;
import com.payeshgaran.workflow.model.WorkflowStep;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WorkflowService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    public void answerTask(AnswerTask answer){

        taskService.createComment(answer.getTaskId(), answer.getProcessInstanceID(), answer.getComment());

        Map<String, Object> taskParams = new HashMap<>(1);
        taskParams.put("outcome", answer.getAnswer());

        taskService.complete(answer.getTaskId(),taskParams);

    }


    //todo Sahih
    public List<WorkflowStep> getWorkflowStepsFor(String processInstanceId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
//                .tenantIdIn(InsuranceSystemEnum.getCurrentSystemCode())
                .processInstanceId(processInstanceId)
                .taskAssigned()
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();

        return list.stream().map(this::workflowStepFromTask).collect(Collectors.toList());
    }

    private WorkflowStep workflowStepFromTask(HistoricTaskInstance taskInstance) {
        WorkflowStep result = new WorkflowStep();
        result.setTaskName(taskInstance.getName());
        result.setUsername(taskInstance.getAssignee());
        result.setInboxEntryDate(taskInstance.getStartTime());
        result.setInboxExitDate(taskInstance.getEndTime());
        result.setProcessDefinitionKey(taskInstance.getProcessDefinitionKey()); //todo add getProcessDefinitionKey()

        List<Comment> taskComments = taskService.getTaskComments(taskInstance.getId());
        List<String> comments = taskComments.stream().map(Comment::getFullMessage).collect(Collectors.toList());
        result.setComments(comments);
        return result;
    }

}
