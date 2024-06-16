package com.project.taskmanager.service;

import com.project.taskmanager.entity.Task;
import com.project.taskmanager.entity.User;
import com.project.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public void saveTask(String title,User user){

        Task task = new Task();
        task.setTitle(title);
        task.setUser(user);
        taskRepository.save(task);
    }

    public void updateTask(Task updatedTask) {
        Task existingTask = taskRepository.findById(updatedTask.getId()).orElse(null);
        if (existingTask != null) {
//            existingTask.setTitle(updatedTask.getTitle());
//            existingTask.setCompleted(updatedTask.isCompleted());
            // Update other fields as necessary
            taskRepository.save(existingTask);
        }
    }

    public List<Task> getTask(User user){
        return taskRepository.findByUserId(user.getId());
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}