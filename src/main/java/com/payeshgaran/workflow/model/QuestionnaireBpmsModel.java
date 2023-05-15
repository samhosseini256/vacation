package com.payeshgaran.workflow.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class QuestionnaireBpmsModel {

    private String registerUser;
    private String questionnaireCode;
    private int questionnaireId;
    private String registerAgent;

    public static QuestionnaireBpmsModel fromMap(Map<String, Object> map) {
        QuestionnaireBpmsModel result = new QuestionnaireBpmsModel();
        ReflectionUtils.doWithFields(QuestionnaireBpmsModel.class, field -> field.set(result, map.get(field.getName())));
        return result;
    }
    public Map<String, Object> createMap() {
        Map<String, Object> result = new HashMap<>();
        ReflectionUtils.doWithFields(QuestionnaireBpmsModel.class, field -> result.put(field.getName(), field.get(this)));
        return result;
    }
}