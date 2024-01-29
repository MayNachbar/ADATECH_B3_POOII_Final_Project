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
        System.out.println("Saving task...");
        taskRepository.saveTask(task);
    }

    @Override
    public List<T> getAllTasks() {
        System.out.println("Reading all tasks...");
        return taskRepository.readAllTasks();
    }

    @Override
    public T getTaskById(Integer id) {
        System.out.println("Reading task by id: " + id);
        return taskRepository.readTaskById(id);
    }

    @Override
    public T getTasksByDeadline(LocalDate date) {
        System.out.println("Reading tasks by deadline: " + date);
        return taskRepository.readTasksByDeadline(date);
    }

    @Override
    public void putTask(T task) {
        System.out.println("Updating task...");
        taskRepository.updateTask(task);
    }

    @Override
    public void deleteTask(Integer id) {
        System.out.println("Deleting task...");
        taskRepository.deleteTask(id);
    }
}