package com.payeshgaran.workflow.service;

import com.payeshgaran.workflow.model.UserModel;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserAndGroupService {

    @Inject
    private IdentityService identityService;

    @Inject
    ManagementService managementService;

    public void saveUser(UserModel userModel){

        User user = identityService.newUser(userModel.getUsername());
        user.setId(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());

        if (!userExist(userModel.getUsername())) identityService.saveUser(user);
        else System.out.println("Mojood");

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


    public void saveTenant(String tenantId, String tenantName){

        Tenant tenant = identityService.newTenant(tenantId);
        tenant.setName(tenantName);
        if (!tenantExist(tenantId, tenantName)) identityService.saveTenant(tenant);
        else System.out.println("Mojood");

    }

    public void updateTenant(String tenantId, String tenantName){
        Tenant tenant = identityService.createTenantQuery().tenantId(tenantId).singleResult();
        tenant.setName(tenantName);
        if (!tenantExist(tenantId, tenantName)) identityService.saveTenant(tenant);
        else System.out.println("Mojood");
    }

    public void deleteTenant(String tenantId, String tenantName){

        if (tenantExist(tenantId, tenantName) )identityService.deleteTenant(tenantId);
        else System.out.println("namojood");
    }


    public void saveGroup(String groupId, String groupName){
        Group group = identityService.newGroup(groupId);
        group.setName(groupName);
        if (!groupExist(groupId,groupName)) identityService.saveGroup(group);
        else System.out.println("Mojood");
    }


    public void updateGroup(String groupId, String groupName){
        Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
        group.setName(groupName);
        if (!groupExist(groupId,groupName)) identityService.saveGroup(group);
        else System.out.println("Mojood");
    }

    public void deleteGroup(String groupId, String groupName){
        if (groupExist(groupId,groupName)) identityService.deleteGroup(groupId);
        else System.out.println("namojood");
    }

    public void addUserToGroup(String userId, String groupName){
        identityService.createMembership(userId,groupName); //todo check
    }





















    private boolean userExist (String username){
        User user = identityService.createUserQuery().userId(username).singleResult();
        return user != null;
    }

    private boolean tenantExist (String tenantId, String tenantName){

        Tenant withTenantId = identityService.createTenantQuery().tenantId(tenantId).singleResult();
        Tenant withTenantName = identityService.createTenantQuery().tenantName(tenantName).singleResult();

        return withTenantId != null || withTenantName != null;
    }

    private boolean groupExist(String groupId, String groupName){
        Group withGroupId = identityService.createGroupQuery().groupId(groupId).singleResult();
        Group withGroupName = identityService.createGroupQuery().groupName(groupName).singleResult();
        return withGroupId != null || withGroupName != null;
    }




}
