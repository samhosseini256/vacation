package com.payeshgaran.workflow.service;

import com.payeshgaran.workflow.model.TaskHistoryModel;
import com.payeshgaran.workflow.model.TaskModel;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserTaskService {

    @Inject
    private TaskService taskService;

    @Inject
    private HistoryService historyService;

    public List<TaskModel> userTaskList(String assignee) {

        List<TaskModel> taskListModel = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();
        for (Task tasks : taskList) {
            TaskModel task = new TaskModel();
            task.setId(tasks.getId());
            task.setName(tasks.getName());
            task.setAssignee(tasks.getAssignee());
            taskListModel.add(task);
        }
        return taskListModel;
    }

    public List<TaskModel> userTaskListHistorical(String assignee, Date startDate, Date endDate ) {

        List<TaskModel> taskListModel = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery().dueAfter(startDate).dueBefore(endDate).orderByDueDate().taskAssignee(assignee).list();
        for (Task tasks : taskList) {
            TaskModel task = new TaskModel();
            task.setId(tasks.getId());
            task.setName(tasks.getName());
            task.setAssignee(tasks.getAssignee());
            taskListModel.add(task);
        }
        return taskListModel;
    }


    public List<TaskModel> taskHistory(String assignee) {
        List<TaskModel> taskListModel = new ArrayList<>();
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assignee).list();

        for (HistoricTaskInstance tasks : taskList) {
            TaskModel task = new TaskModel();
            task.setId(tasks.getId());
            task.setName(tasks.getName());
            task.setAssignee(tasks.getAssignee());
            taskListModel.add(task);
        }
        return taskListModel;
    }


}
