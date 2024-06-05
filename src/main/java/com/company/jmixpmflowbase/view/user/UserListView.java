package com.company.jmixpmflowbase.view.user;

import com.company.jmixpmflowbase.app.TaskService;
import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.User;
import com.company.jmixpmflowbase.view.main.MainView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "users", layout = MainView.class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {

    @Autowired
    private TaskService taskService;
    @Autowired
    private DataManager dataManager;


    @Subscribe
    public void onInit(final InitEvent event) {
        User leastBusyUser = taskService.findLeastBusyUser();

        List<Project> list = dataManager.load(Project.class)
                .query("select p from Project p where p.manager.id = :current_user_id")
                .list();

    }
}