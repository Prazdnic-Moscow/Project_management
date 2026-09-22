package com.company.projectmanagement.view.taskdetail;


import com.company.projectmanagement.entity.Task;
import com.company.projectmanagement.entity.TaskPriority;
import com.company.projectmanagement.view.main.MainView;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.textarea.JmixTextArea;
import io.jmix.flowui.view.*;

@Route(value = "task/:id", layout = MainView.class)
@ViewController(id = "Task_.detail")
@ViewDescriptor(path = "task-detail-view.xml")
@EditedEntityContainer("taskDc")
@DialogMode(width = "AUTO", height = "AUTO")
public class TaskDetailView extends StandardDetailView <Task> {
    @Subscribe
    public void onInitEntity(final InitEntityEvent<Task> event) {
        event.getEntity().setPriority(TaskPriority.MEDIUM);
    }

    @Subscribe("textAreaChange")
    public void onTextAreaChangeComponentValueChange(final AbstractField.ComponentValueChangeEvent<JmixTextArea, ?> event) {
        event.getSource().setHelperText(event.getValue().toString().length()+"/"+500);
    }
}