package com.payeshgaran.workflow.model;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestHistoryModel {

    private String username;
    private Date startTime;
    private Date endTime;

}
