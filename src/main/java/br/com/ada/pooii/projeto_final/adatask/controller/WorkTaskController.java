package br.com.ada.pooii.projeto_final.adatask.controller;

import br.com.ada.pooii.projeto_final.adatask.domain.User;
import br.com.ada.pooii.projeto_final.adatask.domain.WorkTask;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Status;
import br.com.ada.pooii.projeto_final.adatask.service.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkTaskController {
    private Task taskService;

    public WorkTaskController(Task taskService) {
        this.taskService = taskService;
    }

    User user;

    public void start(Scanner scanner) {

        List<User> users = new ArrayList<>();
        System.out.println("Let's create a user!");
        System.out.println("Enter the user's name:");
        String userName = scanner.nextLine();
        user = new User(userName);
        users.add(user);

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createWorkTask(scanner);
                    break;
                case 2:
                    findAllWorkTasks();
                    break;
                case 3:
                    findWorkTaskById(scanner);
                    break;
                case 4:
                    findWorkTaskByDeadline(scanner);
                    break;
                case 5:
                    updateWorkTask(scanner);
                    break;
                case 6:
                    deleteWorkTask(scanner);
                    break;
                case 7:
                    System.out.println("Closing application!");
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        } while (choice != 7);
    }

    private void showMenu() {
        System.out.println("--------MENU--------");
        System.out.println("Choose an option:");
        System.out.println("1. Create a Task");
        System.out.println("2. List Tasks");
        System.out.println("3. List Task By Id");
        System.out.println("4. List Task By deadline");
        System.out.println("5. Update a Task");
        System.out.println("6. Delete a Task");
        System.out.println("7. Return to main menu");
        System.out.print("Option: ");
    }

    public void createWorkTask(Scanner scanner){
        System.out.print("Enter the title of the Work Task: ");
        String title = scanner.nextLine();

        System.out.print("Enter the description of the Work Task: ");
        String description = scanner.nextLine();

        System.out.print("Enter the work type of the Work Task: ");
        String workType = scanner.nextLine();

        System.out.print("Enter the priority of the Work Task (LOW, MEDIUM, HIGH): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter the deadline of the Work Task: ");
        String deadlineInformed = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(deadlineInformed, formatter);

        WorkTask workTask = new WorkTask(title, description, user, priority, deadline, workType);

        workTask.setTitle(title);
        workTask.setDescription(description);
        workTask.setResponsible(user);
        workTask.setPriority(priority);
        workTask.setDeadline(deadline);
        workTask.setWorkType(workType);

        taskService.postTask(workTask);

        System.out.println("Work Task created successfully!");
    }

    public List<WorkTask> findAllWorkTasks() {
        List<WorkTask> workTasks = taskService.getAllTasks();
        printTasks(workTasks);
        return workTasks;
    }

    private void printTasks(List<WorkTask> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("The list of Work Tasks is empty!");
        } else {
            System.out.println("### List of Work Tasks ###");
            for (WorkTask task : tasks) {
                System.out.println("Id: " + task.getId());
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Responsible: " + task.getResponsible());
                System.out.println("WorkType: " + task.getWorkType());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Status: " + task.getStatus());
                System.out.println("Deadline: " + task.getDeadline());

                System.out.println("--------------------------------");
            }
        }
    }

    public WorkTask findWorkTaskById(Scanner scanner) {
        System.out.println("Enter the Work Task ID: ");
        Integer id = scanner.nextInt();
        WorkTask task = (WorkTask) taskService.getTaskById(id);
        if (task == null) {
            System.out.println("There is no Work Task with this ID.");
        } else {
            System.out.println(task);
        }
        return task;
    }

    public WorkTask findWorkTaskByDeadline(Scanner scanner) {
        System.out.println("Enter the deadline: ");
        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(date, formatter);

        WorkTask task = (WorkTask) taskService.getTasksByDeadline(deadline);
        if (task == null) {
            System.out.println("There is no Work Task with this deadline.");
        } else {
            System.out.println(task);
        }
        return task;
    }

    public void updateWorkTask(Scanner scanner) {
        System.out.print("Enter the id of the Work Task you want to edit: ");
        Integer id = scanner.nextInt();

        WorkTask existingTask = findTaskById(id);

        if (existingTask != null) {
            System.out.println("What atribute would you want to update?");
            System.out.println("1 - Title");
            System.out.println("2 - Description");
            System.out.println("3 - Responsible");
            System.out.println("4 - Priority");
            System.out.println("5 - Status");
            System.out.println("6 - Deadline");
            System.out.println("7 - WorkType");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the new title:");
                    String newTitle = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setTitle(newTitle);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                case 2:
                    System.out.println("Enter the new description:");
                    String newDescription = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setDescription(newDescription);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                case 3:
                    System.out.println("Enter the new responsible:");
                    String name = scanner.nextLine();
                    User newResponsible = new User(name);
                    scanner.nextLine();
                    existingTask.setResponsible(newResponsible);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                case 4:
                    System.out.println("Enter the new priority:");
                    Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setPriority(newPriority);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                case 5:
                    System.out.println("Enter the new status:");
                    Status newStatus = Status.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setStatus(newStatus);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                case 6:
                    System.out.println("Enter the new deadline:");
                    String date = scanner.nextLine();
                    scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate newDeadline = LocalDate.parse(date, formatter);
                    existingTask.setDeadline(newDeadline);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                case 7:
                    System.out.println("Enter the new work type:");
                    String newWorkType = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setWorkType(newWorkType);
                    taskService.putTask(existingTask);
                    System.out.println("Work Task updated successfully!");
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        } else {
            System.out.println("There is no Work Task with this ID.");
        }
    }

    private WorkTask findTaskById(Integer id) {
        List<WorkTask> workTasks = taskService.getAllTasks();
        for (WorkTask task : workTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void deleteWorkTask(Scanner scanner) {
        System.out.println("Enter the id of the Work Task you want to delete:");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        WorkTask deletedTask = (WorkTask) taskService.getTaskById(id);
        if (deletedTask == null) {
            System.out.println("There is no Work Task with this ID.");
        } else {
            taskService.deleteTask(id);
            System.out.println("Work Task deleted successfully!");
        }
    }
}