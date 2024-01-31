package br.com.ada.pooii.projeto_final.adatask.domain;

import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;

import java.time.LocalDate;

public final class PersonalTask extends BaseTask {

    public PersonalTask(String title, String description, User responsible, Priority priority, LocalDate deadline) {
        super(title, description, responsible, priority, deadline);
    }

    @Override
    public String getCategory() {
        return "Personal";
    }

    @Override
    public String toString() {
        return    "-----------------------------------\n"
                + "  ######## Personal Task ########\n"
                + "-----------------------------------\n"
                + "Id: " + this.getId() + "\n"
                + "Title: " + this.getTitle() + "\n"
                + "Description: " + this.getDescription() + "\n"
                + "Responsible: " + this.getResponsible() + "\n"
                + "Priority: " + this.getPriority() + "\n"
                + "Status: " + this.getStatus() + "\n"
                + "Deadline: " + this.getDeadline() + "\n"
                + "Created at: " + super.getCreatedAt() + "\n"
                + "Updated at: " + super.getUpdatedAt() + "\n"
                + "Deleted at: " + super.getDeletedAt() + "\n"
                + "Category: " + this.getCategory() + "\n";
    }
}
