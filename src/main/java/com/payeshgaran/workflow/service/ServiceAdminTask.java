package com.payeshgaran.workflow.service;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class ServiceAdminTask implements TaskListener {

    @Inject
    private TaskService taskService;

    @Inject
    private IdentityService identityService;


    @Override
    public void notify(DelegateTask delegateTask) {

        taskService.setAssignee(delegateTask.getId(),"user1");
    }



}
