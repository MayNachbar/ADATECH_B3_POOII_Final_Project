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

        System.out.print("Enter the deadline of the Study Task: ");
        String deadlineInformed = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(deadlineInformed, formatter);

        StudyTask studyTask = new StudyTask(title, description, user, priority, deadline, subject);

        studyTask.setTitle(title);
        studyTask.setDescription(description);
        studyTask.setResponsible(user);
        studyTask.setPriority(priority);
        studyTask.setDeadline(deadline);
        studyTask.setSubject(subject);

        taskService.postTask(studyTask);

        System.out.println("Study Task created successfully!");
    }

    public List<StudyTask> findAllStudyTasks() {
        List<StudyTask> studyTasks = taskService.getAllTasks();
        printTasks(studyTasks);
        return studyTasks;
    }

    private void printTasks(List<StudyTask> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("The list of Study Tasks is empty!");
        } else {
            System.out.println("### List of Study Tasks ###");
            for (StudyTask task : tasks) {
                System.out.println("Id: " + task.getId());
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Responsible: " + task.getResponsible());
                System.out.println("Subject: " + task.getSubject());
                System.out.println("Priority: " + task.getPriority());
                System.out.println("Status: " + task.getStatus());
                System.out.println("Deadline: " + task.getDeadline());

                System.out.println("--------------------------------");
            }
        }
    }

    public StudyTask findStudyTaskById(Scanner scanner) {
        System.out.println("Enter the Study Task ID: ");
        Integer id = scanner.nextInt();
        StudyTask task = (StudyTask) taskService.getTaskById(id);
        if (task == null) {
            System.out.println("There is no Study Task with this ID.");
        } else {
            System.out.println(task);
        }
        return task;
    }

    public StudyTask findStudyTaskByDeadline(Scanner scanner) {
        System.out.println("Enter the deadline: ");
        String date = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate deadline = LocalDate.parse(date, formatter);

        StudyTask task = (StudyTask) taskService.getTasksByDeadline(deadline);
        if (task == null) {
            System.out.println("There is no Study Task with this deadline.");
        } else {
            System.out.println(task);
        }
        return task;
    }

    public void updateStudyTask(Scanner scanner) {
        System.out.print("Enter the id of the Study Task you want to edit: ");
        Integer id = scanner.nextInt();

        StudyTask existingTask = findTaskById(id);

        if (existingTask != null) {
            System.out.println("What atribute would you want to update?");
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
                    System.out.println("Enter the new title:");
                    String newTitle = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setTitle(newTitle);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                case 2:
                    System.out.println("Enter the new description:");
                    String newDescription = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setDescription(newDescription);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                case 3:
                    System.out.println("Enter the new responsible:");
                    String name = scanner.nextLine();
                    User newResponsible = new User(name);
                    scanner.nextLine();
                    existingTask.setResponsible(newResponsible);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                case 4:
                    System.out.println("Enter the new priority:");
                    Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setPriority(newPriority);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                case 5:
                    System.out.println("Enter the new status:");
                    Status newStatus = Status.valueOf(scanner.nextLine().toUpperCase());
                    scanner.nextLine();
                    existingTask.setStatus(newStatus);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                case 6:
                    System.out.println("Enter the new deadline:");
                    String date = scanner.nextLine();
                    scanner.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate newDeadline = LocalDate.parse(date, formatter);
                    existingTask.setDeadline(newDeadline);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                case 7:
                    System.out.println("Enter the new subject:");
                    String newSubject = scanner.nextLine();
                    scanner.nextLine();
                    existingTask.setSubject(newSubject);
                    taskService.putTask(existingTask);
                    System.out.println("Study Task updated successfully!");
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        } else {
            System.out.println("There is no Study Task with this ID.");
        }
    }

    private StudyTask findTaskById(Integer id) {
        List<StudyTask> studyTasks = taskService.getAllTasks();
        for (StudyTask task : studyTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void deleteStudyTask(Scanner scanner) {
        System.out.println("Enter the id of the Study Task you want to delete:");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        StudyTask deletedTask = (StudyTask) taskService.getTaskById(id);
        if (deletedTask == null) {
            System.out.println("There is no Study Task with this ID.");
        } else {
            taskService.deleteTask(id);
            System.out.println("Study Task deleted successfully!");
        }
    }
}