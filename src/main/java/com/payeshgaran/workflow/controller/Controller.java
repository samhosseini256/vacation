package com.payeshgaran.workflow.controller;


import com.payeshgaran.workflow.model.UserModel;
import com.payeshgaran.workflow.service.UserAndGroupService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserAndGroupService userAndGroupService;

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
        userAndGroupService.createTenant(tenantId,tenantName);
    }


}
