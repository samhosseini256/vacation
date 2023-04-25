package com.payeshgaran.workflow.service;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ProcessService {

    @Inject
    private RuntimeService runtimeService;


    public void StartProcess(String processName, String businessKey){
        runtimeService.startProcessInstanceByKey(processName,businessKey);
    }

}
