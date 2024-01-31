package br.com.ada.pooii.projeto_final.adatask.domain;

import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;

import java.time.LocalDate;

public final class WorkTask extends BaseTask {
    private String workType;

    public WorkTask(String title, String description, User responsible, Priority priority, LocalDate deadline, String workType) {
        super(title, description, responsible, priority, deadline);
        this.workType = workType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    @Override
    public String getCategory() {
        return "Work";
    }

    @Override
    public String toString() {
        return    "-----------------------------------\n"
                + "    ######## Work Task ########\n"
                + "-----------------------------------\n"
                + "Id: " + this.getId() + "\n"
                + "Title: " + this.getTitle() + "\n"
                + "Description: " + this.getDescription() + "\n"
                + "Responsible: " + this.getResponsible() + "\n"
                + "WorkType: " + this.getWorkType() + "\n"
                + "Priority: " + this.getPriority() + "\n"
                + "Status: " + this.getStatus() + "\n"
                + "Deadline: " + this.getDeadline() + "\n"
                + "Created at: " + super.getCreatedAt() + "\n"
                + "Updated at: " + super.getUpdatedAt() + "\n"
                + "Deleted at: " + super.getDeletedAt() + "\n"
                + "Category: " + this.getCategory() + "\n";
    }
}
