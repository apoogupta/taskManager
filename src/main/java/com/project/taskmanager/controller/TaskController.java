package com.project.taskmanager.controller;

import com.project.taskmanager.entity.Task;
import com.project.taskmanager.entity.User;
import com.project.taskmanager.repository.UserRepository;
import com.project.taskmanager.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@SessionAttributes("user")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addTask")
    public String addTask(){
        return "addTask";
    }

    @PostMapping("/saveTask")
    public String saveTask(@RequestParam(name = "title") String title,
                        Model model){

        User user = (User) model.getAttribute("user");
        log.info("username="+user.getUsername());
        taskService.saveTask(title,user);
        // Redirect to login page or secure area after successful signup
        return "TaskFrontUI";

    }

    @GetMapping("/showTasks")
    public String showTask(Model model){

        User user = (User) model.getAttribute("user");
        List<Task> taskList = taskService.getTask(user);
        for (Task task : taskList) {
            log.info("Task ID: " + task.getId());
            log.info("Task Description: " + task.getTitle());
            // You can log other task properties as needed
        }

        model.addAttribute("tasks", taskList);
        return "showTasks";
        // Redirect to login page or secure area after successful signup

    }

    @PostMapping("/tasks/{id}/done")
    public String markTaskAsDone(@PathVariable Long id,Model model) {
        Task task = taskService.getTaskById(id);
        User user = (User) model.getAttribute("user");
        if (task != null) {
            task.setCompleted(true);
            taskService.updateTask(task);
        }
        return "redirect:/showTasks";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/showTasks";
    }
}
