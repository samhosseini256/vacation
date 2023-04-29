package com.payeshgaran.workflow.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

}
