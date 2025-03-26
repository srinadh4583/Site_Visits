package com.anantarealty.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anantarealty.model.Task;
import com.anantarealty.repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired 
	private TaskRepository taskRepository;
	 
	    
    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get a specific task by ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            System.out.println("📥 Received Task Data: " + task);
        
            // ✅ Save Task to DB
            Task savedTask = taskRepository.save(task);
            System.out.println("✅ Task Saved Successfully: " + savedTask);
            return ResponseEntity.ok(savedTask);

        } catch (Exception e) {
            System.err.println("❌ Error Creating Task: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating task.");
        }
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        System.out.println("🔹 Received PUT request for Task ID: " + id);
        System.out.println("🔹 Received Data: " + updatedTask);

        if (updatedTask.getDueDate() == null) {
            System.out.println("❌ ERROR: Received null dueDate!");
        } else {
            System.out.println("✅ Parsed Due Date: " + updatedTask.getDueDate());
        }

        return taskRepository.findById(id).map(existingTask -> {
            System.out.println("🔹 Existing Task Found: " + existingTask);

            if (updatedTask.getLead() == null || updatedTask.getLead().getLeadId() == null) {
                updatedTask.setLead(existingTask.getLead());
            }

            existingTask.setTaskName(updatedTask.getTaskName() != null ? updatedTask.getTaskName() : existingTask.getTaskName());
            existingTask.setDescription(updatedTask.getDescription() != null ? updatedTask.getDescription() : existingTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus() != null ? updatedTask.getStatus() : existingTask.getStatus());
            existingTask.setDueDate(updatedTask.getDueDate() != null ? updatedTask.getDueDate() : existingTask.getDueDate());
            existingTask.setAssignedTo(updatedTask.getAssignedTo() != null ? updatedTask.getAssignedTo() : existingTask.getAssignedTo());
            existingTask.setComments(updatedTask.getComments() != null ? updatedTask.getComments() : existingTask.getComments());

            Task savedTask = taskRepository.save(existingTask);
            System.out.println("✅ Task Updated Successfully: " + savedTask);

            return ResponseEntity.ok(savedTask);
        }).orElseGet(() -> {
            System.out.println("❌ Task ID " + id + " not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        });
    }



    // Delete a task
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
