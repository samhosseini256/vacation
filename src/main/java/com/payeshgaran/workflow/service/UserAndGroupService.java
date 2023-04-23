package com.payeshgaran.workflow.service;

import com.payeshgaran.workflow.model.UserModel;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

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

    public void updateUser(UserModel userModel){

        User user = identityService.createUserQuery().userId(userModel.getUsername()).singleResult();
        if (user!=null){

            user.setId(userModel.getUsername());
            user.setPassword(userModel.getPassword());
            user.setFirstName(userModel.getFirstName());
            user.setLastName(userModel.getLastName());
            identityService.saveUser(user);
        }
        else System.out.println("namojood");
    }

    public void deleteUser(String username){

        if (!userExist(username)) identityService.deleteUser(username);
        else System.out.println("namojood");
    }

    public List<User> allUsers(){ // todo make dto and membership
        return identityService.createUserQuery().orderByUserId().asc().list();
    }


    public void createTenant(String tenantId, String tenantName){

        Tenant tenant = identityService.newTenant(tenantId);
        tenant.setName(tenantName);
        if (!tenantExist(tenantId, tenantName)) identityService.saveTenant(tenant);
        else System.out.println("Mojood");

    }






















    private boolean userExist (String username){
        User user = identityService.createUserQuery().userId(username).singleResult();
        return user != null;
    }

    private boolean tenantExist (String tenantId, String tenantName){
        Tenant withTenantId = identityService.createTenantQuery().tenantId(tenantId).singleResult();
        Tenant WithTenantName = identityService.createTenantQuery().tenantName(tenantName).singleResult();
        return  !(withTenantId==null && WithTenantName==null);
    }




}
