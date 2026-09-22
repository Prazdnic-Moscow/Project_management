package com.company.projectmanagement.security;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.entity.TimeEntry;
import com.company.projectmanagement.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "RestrictedProjectRole", code = RestrictedProjectRole.CODE)
public interface RestrictedProjectRole {
    String CODE = "restricted-project-role";

    @PredicateRowLevelPolicy(entityClass = Project.class, actions = {RowLevelPolicyAction.UPDATE, RowLevelPolicyAction.DELETE})
    default RowLevelBiPredicate<Project, ApplicationContext> projectPredicate() {
        return (project, applicationContext) -> {
            CurrentAuthentication currentauthentication = applicationContext.getBean(CurrentAuthentication.class);
            User currentUser = (User) currentauthentication.getUser();
            return currentUser.equals(project.getManager());
        };
    }

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.username = :current_user_username or {E}.task.project.manager.username = :current_user_username")
    void timeEntry();
}