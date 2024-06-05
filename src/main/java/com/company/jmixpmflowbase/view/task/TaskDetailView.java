package com.company.jmixpmflowbase.view.task;

import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.Task;

import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.view.*;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("Task_.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {

    @Subscribe("projectField")
    public void onProjectFieldBlur(final BlurNotifier.BlurEvent<EntityPicker<Project>> event) {

    }
}