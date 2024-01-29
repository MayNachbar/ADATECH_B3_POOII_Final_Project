package br.com.ada.pooii.projeto_final.adatask.repository;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;

import java.time.LocalDate;
import java.util.List;
public interface TaskRepository<T extends BaseTask> {

    void saveTask(T task);

    List<T> readAllTasks();

    T readTaskById(Integer Id);

    T readTasksByDeadline(LocalDate date);

    void updateTask(T task);

    void deleteTask(Integer id);
}
