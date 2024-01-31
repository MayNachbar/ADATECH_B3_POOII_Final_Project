package br.com.ada.pooii.projeto_final.adatask.domain;

import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;

import java.time.LocalDate;

public final class StudyTask extends BaseTask {
    private String subject;

    public StudyTask(String title, String description, User responsible, Priority priority, LocalDate deadline, String subject) {
        super(title, description, responsible, priority,deadline);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getCategory() {
        return "Study";
    }

    @Override
    public String toString() {
        return    "-----------------------------------\n"
                + "    ######## Study Task ########\n"
                + "-----------------------------------\n"
                + "Id: " + this.getId() + "\n"
                + "Title: " + this.getTitle() + "\n"
                + "Description: " + this.getDescription() + "\n"
                + "Responsible: " + this.getResponsible() + "\n"
                + "Subject: " + this.getSubject() + "\n"
                + "Priority: " + this.getPriority() + "\n"
                + "Status: " + this.getStatus() + "\n"
                + "Deadline: " + this.getDeadline() + "\n"
                + "Created at: " + super.getCreatedAt() + "\n"
                + "Updated at: " + super.getUpdatedAt() + "\n"
                + "Deleted at: " + super.getDeletedAt() + "\n"
                + "Category: " + this.getCategory() + "\n";
    }
}
