package com.payeshgaran.workflow.service;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Service2 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String nationalNumber = (String) delegateExecution.getVariable("NationalNumber");

        System.out.println("your national number is: "+nationalNumber);

    }
}
