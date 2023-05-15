package com.payeshgaran.workflow.model;

import java.util.Map;

public class QuestionnaireInboxItem {private final String processInstanceId;
    private final String taskId;
    private final String taskName;
    private final QuestionnaireBpmsModel questionnaire;

    public QuestionnaireInboxItem(String processInstanceId, String taskId, String taskName, Map<String, Object> values) {
        this.processInstanceId = processInstanceId;
        this.taskId = taskId;
        this.taskName = taskName;
        this.questionnaire = QuestionnaireBpmsModel.fromMap(values);
    }

}
