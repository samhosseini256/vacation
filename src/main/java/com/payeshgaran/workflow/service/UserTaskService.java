package com.payeshgaran.workflow.service;

import com.payeshgaran.workflow.model.TaskModel;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.tasklist.Tasklist;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserTaskService {

    @Inject
    private TaskService taskService;

    public List<TaskModel> userTasks(String assignee){

        List<TaskModel> taskListModel= new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(assignee).list();

        for (Task tasks: taskList){

            TaskModel task = new TaskModel();
            task.setId(tasks.getId());
            task.setName(tasks.getName());
            task.setAssignee(tasks.getAssignee());

            taskListModel.add(task);

        }

        return taskListModel;

    }


}
