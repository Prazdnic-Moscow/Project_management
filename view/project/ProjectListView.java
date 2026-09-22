package com.company.projectmanagement.view.project;

import com.company.projectmanagement.entity.Project;

import com.company.projectmanagement.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "projects", layout = MainView.class)
@ViewController(id = "Project.list")
@ViewDescriptor(path = "project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "AUTO", height = "AUTO")
public class ProjectListView extends StandardListView<Project> {

}