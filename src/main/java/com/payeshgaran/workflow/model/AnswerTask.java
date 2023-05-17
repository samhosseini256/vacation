package com.payeshgaran.workflow.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerTask {

    private String taskId;
    private String processInstanceID;
    private String answer;
    private String comment;

}
