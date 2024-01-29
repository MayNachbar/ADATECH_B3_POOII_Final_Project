package br.com.ada.pooii.projeto_final.adatask.controller;

import br.com.ada.pooii.projeto_final.adatask.domain.PersonalTask;
import br.com.ada.pooii.projeto_final.adatask.domain.User;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Status;
import br.com.ada.pooii.projeto_final.adatask.service.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonalTaskController {
    private Task taskService;

    public PersonalTaskController(Task taskService) {
        this.taskService = taskService;
    }

    User user;

    public void start(Scanner scanner) {

        List<User> users = new ArrayList<>();
        System.out.println("_________________________________________");
        System.out.println("         Let's create a user!");
        System.out.println("_________________________________________");
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
                    createPersonalTask(scanner);
                    break;
                case 2:
                    findAllPersonalTasks();
                    break;
                case 3:
                    findPersonalTaskById(scanner);
                    break;
                case 4:
                    findPersonalTaskByDeadline(scanner);
                    break;
                case 5:
                    updatePersonalTask(scanner);
                    break;
                case 6:
                    deletePersonalTask(scanner);
                    break;//
                case 7:
                    System.out.println("_________________________________________");
                    System.out.println("         Returning to the main menu");
                    System.out.println("_________________________________________");
                    break;
                default:
                    System.out.println("_________________________________________");
                    System.out.println("             Invalid input!");
                    System.out.println("_________________________________________");
            }
        } while (choice != 7);
    }

    private void showMenu() {
        System.out.println("_________________________________________");
        System.out.println("       ### MENU PERSONAL TASK ###");
        System.out.println("_________________________________________");
        System.out.println("Choose an option:");
        System.out.println("1. Create a Task");
        System.out.println("2. List Tasks");
        System.out.println("3. List Task By Id");
        System.out.println("4. List Task By deadline");
        System.out.println("5. Update a Task");
        System.out.println("6. Delete a Task");
        System.out.println("7. Return to the main menu");
        System.out.print("Option: ");
    }

    public void createPersonalTask(Scanner scanner){
        System.out.print("Enter the title of the Personal Task: ");
        String title = scanner.nextLine();

        System.out.print("Enter the description of the Personal Task: ");
        String description = scanner.nextLine();

        System.out.print("Enter the priority of the Personal Task (LOW, MEDIUM, HIGH): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter the deadline of the Personal Task:: ");
        String deadlineInformed = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(deadlineInformed, formatter);

        PersonalTask personalTask = new PersonalTask(title, description, user, priority, deadline);

        personalTask.setTitle(title);
        personalTask.setDescription(description);
        personalTask.setResponsible(user);
        personalTask.setPriority(priority);
        personalTask.setDeadline(deadline);

        taskService.postTask(personalTask);

        System.out.println("Personal Task created successfully!");
    }
    public List<PersonalTask> findAllPersonalTasks() {
        List<PersonalTask> personalTasks = taskService.getAllTasks();
        printTasks(personalTasks);
        return personalTasks;
    }

    private void printTasks(List<PersonalTask> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("_________________________________________");
            System.out.println("  The list of Personal Tasks is empty!");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println("     ### List of Personal Tasks ###");
            System.out.println("_________________________________________");
            for (PersonalTask task : tasks) {
                System.out.println("Id: " + task.getId());
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Responsible: " + task.getResponsible());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Status: " + task.getStatus());
                System.out.println("Deadline: " + task.getDeadline());
                System.out.println("Created at: " + task.getCreatedAt());

                System.out.println("_________________________________________");
            }
        }
    }

    public PersonalTask findPersonalTaskById(Scanner scanner) {
        System.out.println("_________________________________________");
        System.out.println("      Enter the Personal Task ID: ");
        System.out.println("ID: ");
        Integer id = scanner.nextInt();
        System.out.println("_________________________________________");

        PersonalTask task = (PersonalTask) taskService.getTaskById(id);
        if (task == null) {
            System.out.println("_________________________________________");
            System.out.println(" There is no Personal Task with this ID.");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println(task);
            System.out.println("_________________________________________");
        }
        return task;
    }

    public PersonalTask findPersonalTaskByDeadline(Scanner scanner) {
        System.out.println("_________________________________________");
        System.out.println("         Enter the deadline: ");
        System.out.println("Deadline (dd/MM/yyyy): ");
        String date = scanner.nextLine();
        System.out.println("_________________________________________");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(date, formatter);

        PersonalTask task = (PersonalTask) taskService.getTasksByDeadline(deadline);
        if (task == null) {
            System.out.println("_________________________________________");
            System.out.println("There is no Personal Task with this deadline.");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println(task);
            System.out.println("_________________________________________");
        }
        return task;
    }

    public void updatePersonalTask(Scanner scanner) {
        System.out.println("_____________________________________________________");
        System.out.println("Enter the id of the Personal Task you want to edit: ");
        System.out.println("_____________________________________________________");
        Integer id = scanner.nextInt();

        PersonalTask existingTask = findTaskById(id);

        if (existingTask != null) {
            System.out.println("_________________________________________");
            System.out.println(" What atribute would you want to update?");
            System.out.println("_________________________________________");
            System.out.println("1 - Title");
            System.out.println("2 - Description");
            System.out.println("3 - Responsible");
            System.out.println("4 - Priority");
            System.out.println("5 - Status");
            System.out.println("6 - Deadline");
            System.out.println("Option:");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:

                    System.out.println("Enter the new title:");
                    String newTitle = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setTitle(newTitle);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("   Personal Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 2:
                    System.out.println("Enter the new description:");
                    String newDescription = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setDescription(newDescription);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("   Personal Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 3:
                    System.out.println("Enter the new responsible:");
                    String name = scanner.nextLine();
                    User newResponsible = new User(name);
                    scanner.nextLine();
                    existingTask.setResponsible(newResponsible);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("   Personal Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 4:
                    System.out.println("Enter the new priority:");
                    Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setPriority(newPriority);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("   Personal Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 5:
                    System.out.println("Enter the new status:");
                    Status newStatus = Status.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setStatus(newStatus);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("   Personal Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 6:
                    System.out.println("Enter the new deadline:");
                    String date = scanner.nextLine();
                    scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate newDeadline = LocalDate.parse(date, formatter);
                    existingTask.setDeadline(newDeadline);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("   Personal Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                default:
                    System.out.println("_________________________________________");
                    System.out.println("            Invalid input!");
                    System.out.println("_________________________________________");
            }
        } else {
            System.out.println("_________________________________________");
            System.out.println("There is no Personal Task with this ID.");
            System.out.println("_________________________________________");
        }
    }

    private PersonalTask findTaskById(Integer id) {
        List<PersonalTask> personalTasks = taskService.getAllTasks();
        for (PersonalTask task : personalTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void deletePersonalTask(Scanner scanner) {
        System.out.println("________________________________________________________");
        System.out.println(" Enter the id of the Personal Task you want to delete:");
        System.out.println("________________________________________________________");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        PersonalTask deletedTask = (PersonalTask) taskService.getTaskById(id);
        if (deletedTask == null) {
            System.out.println("_________________________________________");
            System.out.println(" There is no Personal Task with this ID.");
            System.out.println("_________________________________________");
        } else {
            taskService.deleteTask(id);
            System.out.println("_________________________________________");
            System.out.println("   Personal Task deleted successfully!");
            System.out.println("_________________________________________");
        }
    }
}