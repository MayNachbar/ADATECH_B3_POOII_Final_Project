package br.com.ada.pooii.projeto_final.adatask.repository;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryTaskRepository<T extends BaseTask> implements TaskRepository<T> {
    private final List<T> tasks = new ArrayList<>();
    private final List<T> personalTasks = new ArrayList<>();
    private final List<T> studyTasks = new ArrayList<>();
    private final List<T> workTasks = new ArrayList<>();

    @Override
    public void saveTask(T taskToAdd) {
        if (taskToAdd.getCategory().equals("Personal")) {
            this.personalTasks.add(taskToAdd);
        } else if (taskToAdd.getCategory().equals("Study")) {
            this.studyTasks.add(taskToAdd);
        } else if (taskToAdd.getCategory().equals("Work")) {
            this.workTasks.add(taskToAdd);
        } else {
            throw new IllegalArgumentException("Invalid category");
        }
    }

    @Override
    public List<? extends BaseTask> readAllTasks(String category) {
        if (category.equals("Personal")) {
            return this.personalTasks;
        } else if (category.equals("Study")) {
            return this.studyTasks;
        } else if (category.equals("Work")) {
            return this.workTasks;
        } else {
            throw new IllegalArgumentException("Invalid category");
        }
    }

    @Override
    public T readTaskById(String category, Integer id) {
        if (category.equals("Personal")) {
            for (T task : personalTasks) {
                if (task.getId().equals(id)) {
                    return task;
                }
            }
        } else if (category.equals("Study")) {
            for (T task : studyTasks) {
                if (task.getId().equals(id)) {
                    return task;
                }
            }
        } else if (category.equals("Work")) {
            for (T task : workTasks) {
                if (task.getId().equals(id)) {
                    return task;
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid ID or category");
        }
        return null;
    }

    public T readTasksByDeadline(LocalDate date, String category) {
        if (category.equals("Personal")) {
            for (T task : personalTasks) {
                if (task.getDeadline().equals(date)) {
                    return task;
                }
            }
        } else if (category.equals("Study")) {
            for (T task : studyTasks) {
                if (task.getDeadline().equals(date)) {
                    return task;
                }
            }
        } else if (category.equals("Work")) {
            for (T task : workTasks) {
                if (task.getDeadline().equals(date)) {
                    return task;
                }
            }
        } else {
            throw new IllegalArgumentException("Invalid category");
        }
        return null;
    }

    @Override
    public void updateTask(T updatedTask, String category) {
        if (category.equals("Personal")) {
            Integer taskId = updatedTask.getId();
            readTaskById(category, taskId);
            updatedTask.setUpdatedAt(LocalDate.now());
            personalTasks.set(taskId, updatedTask);
        } else if (category.equals("Study")) {
            Integer taskId = updatedTask.getId();
            readTaskById(category, taskId);
            updatedTask.setUpdatedAt(LocalDate.now());
            studyTasks.set(taskId, updatedTask);
        } else if (category.equals("Work")) {
            Integer taskId = updatedTask.getId();
            readTaskById(category, taskId);
            updatedTask.setUpdatedAt(LocalDate.now());
            workTasks.set(taskId, updatedTask);
        } else {
            throw new IllegalArgumentException("Invalid category");
        }
    }

    @Override
    public void deleteTask(String category, Integer id) {
        T taskToDelete = readTaskById(category, id);

        if (taskToDelete != null) {
            if (category.equals("Personal")) {
                personalTasks.remove(taskToDelete);
            } else if (category.equals("Study")) {
                studyTasks.remove(taskToDelete);
            } else if (category.equals("Work")) {
                workTasks.remove(taskToDelete);
            } else {
                throw new IllegalArgumentException("Invalid category");
            }
        }
    }
}
