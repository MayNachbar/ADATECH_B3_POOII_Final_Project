package br.com.ada.pooii.projeto_final.adatask.controller;

import br.com.ada.pooii.projeto_final.adatask.domain.StudyTask;
import br.com.ada.pooii.projeto_final.adatask.domain.User;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Status;
import br.com.ada.pooii.projeto_final.adatask.service.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudyTaskController {
    private Task taskService;

    public StudyTaskController(Task taskService) {
        this.taskService = taskService;
    }

    User user;
    String category = "Study";

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
                    createStudyTask(scanner);
                    break;
                case 2:
                    findAllStudyTasks();
                    break;
                case 3:
                    findStudyTaskById(scanner);
                    break;
                case 4:
                    findStudyTaskByDeadline(scanner);
                    break;
                case 5:
                    updateStudyTask(scanner);
                    break;
                case 6:
                    deleteStudyTask(scanner);
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
        System.out.println("        ### MENU STUDY TASK ###");
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

    public void createStudyTask(Scanner scanner){
        System.out.print("Enter the title of the Study Task: ");
        String title = scanner.nextLine();

        System.out.print("Enter the description of the Study Task: ");
        String description = scanner.nextLine();

        System.out.print("Enter the subject of the Study Task: ");
        String subject = scanner.nextLine();

        System.out.print("Enter the priority of the Study Task (LOW, MEDIUM, HIGH): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter the deadline of the Study Task (dd/MM/yyyy): ");
        String deadlineInformed = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(deadlineInformed, formatter);

        StudyTask studyTask = new StudyTask(title, description, user, priority, deadline, subject);

        taskService.postTask(studyTask);

        System.out.println("Study Task created successfully!");
    }

    public List<StudyTask> findAllStudyTasks() {
        List<StudyTask> studyTasks = taskService.getAllTasks(category);
        printTasks(studyTasks);
        return studyTasks;
    }

    private void printTasks(List<StudyTask> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("_________________________________________");
            System.out.println("  The list of Study Tasks is empty!");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println("     ### List of Study Tasks ###");
            System.out.println("_________________________________________");
            for (StudyTask task : tasks) {
                System.out.println("Id: " + task.getId());
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Responsible: " + task.getResponsible());
                System.out.println("Subject: " + task.getSubject());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Status: " + task.getStatus());
                System.out.println("Deadline: " + task.getDeadline());
                System.out.println("Created at: " + task.getCreatedAt());
                System.out.println("Updated at: " + task.getUpdatedAt());
                System.out.println("Deleted at: " + task.getDeletedAt());
                System.out.println("Category: " + task.getCategory());

                System.out.println("_________________________________________");
            }
        }
    }

    public StudyTask findStudyTaskById(Scanner scanner) {
        System.out.println("_________________________________________");
        System.out.println("       Enter the Study Task ID: ");
        System.out.println("ID: ");
        Integer id = scanner.nextInt();
        System.out.println("_________________________________________");
        StudyTask task = (StudyTask) taskService.getTaskById(category, id);
        if (task == null) {
            System.out.println("_________________________________________");
            System.out.println("There is no Study Task with this ID: " + id);
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println(task);
            System.out.println("_________________________________________");
        }
        return task;
    }

    public StudyTask findStudyTaskByDeadline(Scanner scanner) {
        System.out.println("_________________________________________");
        System.out.println("         Enter the deadline: ");
        System.out.println("Deadline (dd/MM/yyyy): ");
        String date = scanner.nextLine();
        System.out.println("_________________________________________");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(date, formatter);

        StudyTask task = (StudyTask) taskService.getTasksByDeadline(deadline, category);
        if (task == null) {
            System.out.println("_________________________________________");
            System.out.println("There is no Study Task with this deadline.");
            System.out.println("_________________________________________");
        } else {
            System.out.println("_________________________________________");
            System.out.println(task);
            System.out.println("_________________________________________");
        }
        return task;
    }

    public void updateStudyTask(Scanner scanner) {
        System.out.println("_____________________________________________________");
        System.out.println("  Enter the id of the Study Task you want to edit: ");
        System.out.println("ID: ");
        Integer id = scanner.nextInt();
        System.out.println("_____________________________________________________");

        StudyTask existingTask = (StudyTask) taskService.getTaskById(category, id);

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
            System.out.println("7 - Subject");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new title:");
                    String newTitle = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setTitle(newTitle);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 2:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new description:");
                    String newDescription = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setDescription(newDescription);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 3:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new responsible:");
                    String name = scanner.nextLine();
                    User newResponsible = new User(name);
                    scanner.nextLine();
                    existingTask.setResponsible(newResponsible);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 4:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new priority (LOW, MEDIUM, HIGH):");
                    Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setPriority(newPriority);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 5:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new status (TODO, INPROGRESS, DONE, CANCELLED):");
                    Status newStatus = Status.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setStatus(newStatus);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 6:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new deadline (dd/MM/yyyy):");
                    String date = scanner.nextLine();
                    scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate newDeadline = LocalDate.parse(date, formatter);
                    existingTask.setDeadline(newDeadline);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                case 7:
                    scanner = new Scanner(System.in);
                    System.out.println("Enter the new subject:");
                    String newSubject = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setSubject(newSubject);
                    taskService.putTask(existingTask);
                    System.out.println("_________________________________________");
                    System.out.println("    Study Task updated successfully!");
                    System.out.println("_________________________________________");
                    break;
                default:
                    System.out.println("_________________________________________");
                    System.out.println("            Invalid input!");
                    System.out.println("_________________________________________");
            }
        } else {
            System.out.println("_________________________________________");
            System.out.println("There is no Personal Task with this ID: " + id);
            System.out.println("_________________________________________");
        }
    }

    public void deleteStudyTask(Scanner scanner) {
        System.out.println("________________________________________________________");
        System.out.println(" Enter the id of the Personal Task you want to delete:");
        System.out.println("ID: ");
        System.out.println("________________________________________________________");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        StudyTask deletedTask = (StudyTask) taskService.getTaskById(category, id);
        if (deletedTask == null) {
            System.out.println("_________________________________________");
            System.out.println(" There is no Personal Task with this ID: " + id);
            System.out.println("_________________________________________");
        } else {
            scanner = new Scanner(System.in);
            System.out.println("Are you sure you want to delete this task? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("n")) {
                return;
            }
            taskService.deleteTask(category, id);
            System.out.println("_________________________________________");
            System.out.println("   Personal Task deleted successfully!");
            System.out.println("_________________________________________");
        }
    }
}