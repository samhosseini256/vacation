package com.payeshgaran.workflow.model;

import lombok.Data;

@Data
public class QuestionnaireSearchParams {
    private Long insuranceFieldId;
    private String questionnaireCode;
    private String policyHolderIdentificationNo;
}
