package br.com.ada.pooii.projeto_final.adatask.service;

import br.com.ada.pooii.projeto_final.adatask.domain.BaseTask;

import java.time.LocalDate;
import java.util.List;

public interface Task<T extends BaseTask> {

    void postTask(T task);

    List<? extends BaseTask> getAllTasks(String category);

    T getTaskById(String category, Integer id);

    T getTasksByDeadline(LocalDate date, String category);

    void putTask(T task);

    void deleteTask(String category, Integer id);
}