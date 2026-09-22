package com.company.projectmanagement.view.project;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "projects/:id", layout = MainView.class)
@ViewController(id = "Project.detail")
@ViewDescriptor(path = "project-detail-view.xml")
@EditedEntityContainer("projectDc")
@DialogMode(width = "AUTO", height = "AUTO")
public class ProjectDetailView extends StandardDetailView<Project> {
}