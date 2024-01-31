package br.com.ada.pooii.projeto_final.adatask.domain;

import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Status;

import java.time.LocalDate;

import static br.com.ada.pooii.projeto_final.adatask.domain.enums.Status.TODO;

public abstract class BaseTask {
    private static int nextId = 1;
    private Integer id;
    private String title;
    private String description;
    private User responsible;
    private Priority priority;
    private Status status;
    private LocalDate deadline;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    private String category;

    public BaseTask(String title, String description, User responsible, Priority priority, LocalDate deadline) {
        this.id = nextId++;
        this.title = title;
        this.description = description;
        this.responsible = responsible;
        this.priority = priority;
        this.status = TODO;
        this.deadline = deadline;
        this.createdAt = LocalDate.now();
        this.category = getCategory();
    }

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCategory() {
        return null;
    }
}
