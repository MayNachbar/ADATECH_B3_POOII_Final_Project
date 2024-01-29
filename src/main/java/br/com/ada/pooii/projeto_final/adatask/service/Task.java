package br.com.ada.pooii.projeto_final.adatask.service;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;

import java.time.LocalDate;
import java.util.List;

public interface Task<T extends BaseTask> {

    void postTask(T task);

    List<T> getAllTasks();

    T getTaskById(Integer id);

    T getTasksByDeadline(LocalDate date);

    void putTask(T task);

    void deleteTask(Integer id);
}