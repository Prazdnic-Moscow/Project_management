package com.company.projectmanagement.security;

import com.company.projectmanagement.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "ProjcetManager", code = ProjcetManagerRole.CODE)
public interface ProjcetManagerRole {
    String CODE = "projcet-manager";

    @EntityAttributePolicy(entityClass = Project.class,
            attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.ALL)
    void project();

    @EntityAttributePolicy(entityClass = Task.class,
            attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = TimeEntry.class,
            attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = TimeEntry.class, actions = EntityPolicyAction.ALL)
    void timeEntry();

    @EntityAttributePolicy(entityClass = User.class,
            attributes = {
                    "lastName",
                    "firstName"
            }, action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = User.class,
            attributes = {
                    "version",
                    "avatar",
                    "username",
                    "password",
                    "email",
                    "active",
                    "timeZoneId",
                    "passwordChangeRequired",
                    "id"
            }, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void user();

    @MenuPolicy(menuIds = {
            "User.list",
            "Project.list",
            "TimeEntry.list",
            "Task_.list",
            "ProjectStats.list"
    })
    @ViewPolicy(viewIds = {
            "User.list",
            "Project.list",
            "TimeEntry.list",
            "Task_.list",
            "ProjectStats.list",
            "Project.detail",
            "Task_.detail",
            "TimeEntry.detail",
            "User.detail"
    })
    void screens();

    @EntityAttributePolicy(entityClass = ProjectStats.class,
            attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = ProjectStats.class, actions = EntityPolicyAction.READ)
    void projectStats();
}