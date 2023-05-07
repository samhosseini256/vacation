package com.payeshgaran.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Service1 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        delegateExecution.setVariable("NationalNumber","1890099740");

//        int a = 10/0;

    }
}
