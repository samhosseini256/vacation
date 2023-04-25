package com.payeshgaran.workflow.controller;


import com.payeshgaran.workflow.model.TaskModel;
import com.payeshgaran.workflow.model.UserModel;
import com.payeshgaran.workflow.service.UserAndGroupService;
import com.payeshgaran.workflow.service.UserTaskService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserAndGroupService userAndGroupService;

    @Autowired
    private UserTaskService userTaskService;

    @PostMapping("/save-user")
    public void saveUser(@RequestBody UserModel userModel){
        userAndGroupService.saveUser(userModel);
    }

    @PostMapping("/delete-user-{username}")
    public void deleteUser(@PathVariable String username){
        userAndGroupService.deleteUser(username);
    }

    @PostMapping("/update-user")
    public void updateUser(@RequestBody UserModel userModel){
        userAndGroupService.updateUser(userModel);
    }

    @GetMapping("/all-users")
    public List<User> allUsers(){
        return userAndGroupService.allUsers();
    }

    @PostMapping("/save-tenant-{tenantId}-{tenantName}")
    public void saveTenant(@PathVariable String tenantId, @PathVariable String tenantName){
        userAndGroupService.saveTenant(tenantId,tenantName);
    }

    @PostMapping("/delete-tenant-{tenantId}-{tenantName}")
    public void deleteTenant(@PathVariable String tenantId, @PathVariable String tenantName){
        userAndGroupService.deleteTenant(tenantId,tenantName);
    }

    @PostMapping("/update-tenant-{tenantId}-{tenantName}")
    public void updateTenant(@PathVariable String tenantId, @PathVariable String tenantName) {
        userAndGroupService.updateTenant(tenantId, tenantName);
    }

    @PostMapping("/save-group-{groupId}-{groupName}")
    public void saveGroup(@PathVariable String groupId, @PathVariable String groupName){
        userAndGroupService.saveGroup(groupId,groupName);
    }

    @PostMapping("/update-group-{groupId}-{groupName}")
    public void updateGroup(@PathVariable String groupId, @PathVariable String groupName){
        userAndGroupService.updateGroup(groupId,groupName);
    }

    @PostMapping("/delete-group-{groupId}-{groupName}")
    public void deleteGroup(@PathVariable String groupId, @PathVariable String groupName){
        userAndGroupService.deleteGroup(groupId,groupName);
    }

    @PostMapping("/add-user-to-group-{userId}-{groupId}")
    public void addUserToGroup(@PathVariable String userId, @PathVariable String groupId){
        userAndGroupService.addUserToGroup(userId,groupId);
    }

    @GetMapping("/tasklist-{username}")
    public List<TaskModel> userTasks(@PathVariable String username){
        return userTaskService.userTasks(username);
    }

}
