package br.com.ada.pooii.projeto_final.adatask.repository;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryTaskRepository<T extends BaseTask> implements TaskRepository<T> {
    private final List<T> tasks = new ArrayList<>();

    @Override
    public void saveTask(T taskToAdd) {
        this.tasks.add(taskToAdd);
    }

    @Override
    public List<T> readAllTasks() {
        if (!tasks.isEmpty()) {
            return tasks;
        }
        return null;
    }

    @Override
    public T readTaskById(Integer id) {
        for (T task : tasks){
            if (Objects.equals(task.getId(), id)){
                return task;
            }
        }
        return null;
    }

    @Override
    public void updateTask(T updatedTask) {
        Integer taskId = updatedTask.getId();

        for (int i = 0; i < tasks.size(); i++) {
            BaseTask existingTask = tasks.get(i);
            if (existingTask.getId().equals(taskId)) {
                tasks.set(i, updatedTask);
                return;
            }
        }
    }

    @Override
    public void deleteTask(Integer id) {
        tasks.removeIf(task -> Objects.equals(task.getId(), id));
    }

    public T readTasksByDeadline(LocalDate date) {
        for (T task : tasks) {
            if (task.getDeadline().equals(date)) {
                return task;
            }
        }
        return null;
    }
}
