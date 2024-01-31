package br.com.ada.pooii.projeto_final.adatask.service;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;
import br.com.ada.pooii.projeto_final.adatask.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskService<T extends BaseTask> implements Task<T> {
    private final TaskRepository<T> taskRepository;

    public TaskService(TaskRepository<T> taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void postTask(T task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (task.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be before today");
        }
        if (task.getResponsible() == null) {
            throw new IllegalArgumentException("Responsible cannot be null");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        System.out.println("Saving task...");
        taskRepository.saveTask(task);
    }

    @Override
    public List<? extends BaseTask> getAllTasks(String category) {
        System.out.println("Reading all tasks...");
        return taskRepository.readAllTasks(category);
    }

    @Override
    public T getTaskById(String category, Integer id) {
        System.out.println("Reading task by id: " + id);
        return taskRepository.readTaskById(category, id);
    }

    @Override
    public T getTasksByDeadline(LocalDate date, String category) {
        System.out.println("Reading tasks by deadline: " + date);
        return taskRepository.readTasksByDeadline(date, category);
    }

    @Override
    public void putTask(T task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (task.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Deadline cannot be before today");
        }
        if (task.getResponsible() == null) {
            throw new IllegalArgumentException("Responsible cannot be null");
        }
        if (task.getPriority() == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        System.out.println("Updating task...");
        taskRepository.updateTask(task, task.getCategory());
    }

    @Override
    public void deleteTask(String category, Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        System.out.println("Deleting task...");
        taskRepository.deleteTask(category,id);
    }
}