package com.payeshgaran.workflow.service;

import com.payeshgaran.workflow.model.UserModel;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.identity.UserQuery;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserAndGroupService {

    @Inject
    private IdentityService identityService;


    public void saveUser(UserModel userModel){

        User user = identityService.newUser(userModel.getUsername());
        user.setId(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());

        if (!userExist(userModel.getUsername())) identityService.saveUser(user);
        else System.out.println("TEKRARI");

    }

    public void deleteUser(String username){

        if (!userExist(username)) identityService.deleteUser(username);
        else System.out.println("namojood");
    }

    public void updateUser(UserModel userModel){ //todo not working

        User user = identityService.createUserQuery().userId(userModel.getUsername()).singleResult();
        if (user!=null){
            user.setId(userModel.getUsername());
            user.setPassword(userModel.getPassword());
            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
        }
        else System.out.println("namojood");


    }










    public void addUserToGroup(String username, String groupName){

    }

    private boolean userExist (String username){
        User user = identityService.createUserQuery().userId(username).singleResult();
        return user != null;
    }
}







    /*
    processEngine.getIdentityService().createMembership("userId","groupId");
     */


/*

//    void createGroupUsers(){
//
//        Group HEAD_OFFICE = identityService.newGroup("ستاد");
//        Group PROVINCE = identityService.newGroup("استان");
//        Group BRANCH = identityService.newGroup("شعبه");
//        Group REAL_AGENT = identityService.newGroup("نمايندگي");
//        Group CORP_AGENT = identityService.newGroup("شركت نمايندگي");
//        Group REAL_BROKER = identityService.newGroup("كارگزار حقيقي");
//        Group CORP_BROKER = identityService.newGroup("كارگزار حقوقي");
//        Group LIFE_AGENT = identityService.newGroup("نمايندگي عمر");
//
//        identityService.saveGroup(HEAD_OFFICE);
//        identityService.saveGroup(PROVINCE);
//        identityService.saveGroup(BRANCH);
//        identityService.saveGroup(REAL_AGENT);
//        identityService.saveGroup(CORP_AGENT);
//        identityService.saveGroup(REAL_BROKER);
//        identityService.saveGroup(CORP_BROKER);
//        identityService.saveGroup(LIFE_AGENT);
//
//    }
 */
