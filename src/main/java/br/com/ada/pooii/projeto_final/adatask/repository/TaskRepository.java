package br.com.ada.pooii.projeto_final.adatask.repository;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;

import java.time.LocalDate;
import java.util.List;
public interface TaskRepository<T extends BaseTask> {

    void saveTask(T task);

    List<? extends BaseTask> readAllTasks(String category);

    T readTaskById(String category, Integer Id);

    T readTasksByDeadline(LocalDate date, String category);

    void updateTask(T task, String category);

    void deleteTask(String category, Integer id);
}
