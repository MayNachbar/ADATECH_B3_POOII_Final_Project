package br.com.ada.pooii.projeto_final.adatask;

import br.com.ada.pooii.projeto_final.adatask.controller.PersonalTaskController;
import br.com.ada.pooii.projeto_final.adatask.controller.StudyTaskController;
import br.com.ada.pooii.projeto_final.adatask.controller.WorkTaskController;
import br.com.ada.pooii.projeto_final.adatask.domain.PersonalTask;
import br.com.ada.pooii.projeto_final.adatask.domain.StudyTask;
import br.com.ada.pooii.projeto_final.adatask.domain.User;
import br.com.ada.pooii.projeto_final.adatask.domain.WorkTask;
import br.com.ada.pooii.projeto_final.adatask.domain.enums.Priority;
import br.com.ada.pooii.projeto_final.adatask.repository.InMemoryTaskRepository;
import br.com.ada.pooii.projeto_final.adatask.repository.TaskRepository;
import br.com.ada.pooii.projeto_final.adatask.service.Task;
import br.com.ada.pooii.projeto_final.adatask.service.TaskService;

import java.time.LocalDate;
import java.util.Scanner;

public class AdaTaskApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TaskRepository taskRepository = new InMemoryTaskRepository();
        Task taskService = new TaskService(taskRepository);

        PersonalTaskController personalTaskController = new PersonalTaskController(taskService);
        StudyTaskController studyTaskController = new StudyTaskController(taskService);
        WorkTaskController workTaskController = new WorkTaskController(taskService);

        //Test:
//        var user = new User("May");
//
//        PersonalTask personalTask1 = new PersonalTask("Personal Task", "Task 1", user, Priority.LOW, LocalDate.now().plusDays(10));
//        taskService.postTask(personalTask1);
//        PersonalTask personalTask2 = new PersonalTask("Personal Task", "Task 2", user, Priority.MEDIUM, LocalDate.now().plusDays(5));
//        taskService.postTask(personalTask2);
//        PersonalTask personalTask3 = new PersonalTask("Personal Task", "Task 3", user, Priority.HIGH, LocalDate.now().plusDays(2));
//        taskService.postTask(personalTask3);
//
//        StudyTask studyTask1 = new StudyTask("Study Task", "Task 4", user, Priority.LOW, LocalDate.now().plusDays(10), "Math");
//        taskService.postTask(studyTask1);
//        StudyTask studyTask2 = new StudyTask("Study Task", "Task 5", user, Priority.MEDIUM, LocalDate.now().plusDays(5), "History");
//        taskService.postTask(studyTask2);
//        StudyTask studyTask3 = new StudyTask("Study Task", "Task 6", user, Priority.HIGH, LocalDate.now().plusDays(2), "Geography");
//        taskService.postTask(studyTask3);
//
//        WorkTask workTask1 = new WorkTask("Work Task", "Task 7", user, Priority.LOW, LocalDate.now().plusDays(10), "Development");
//        taskService.postTask(workTask1);
//        WorkTask workTask2 = new WorkTask("Work Task", "Task 8", user, Priority.MEDIUM, LocalDate.now().plusDays(5), "Testing");
//        taskService.postTask(workTask2);
//        WorkTask workTask3 = new WorkTask("Work Task", "Task 9", user, Priority.HIGH, LocalDate.now().plusDays(2), "Analysis");
//        taskService.postTask(workTask3);

        int choice;

        do {
            System.out.println("_________________________________________");
            System.out.println("_________________________________________");
            System.out.println("        ADA TASK - Task Manager");
            System.out.println("_________________________________________");
            System.out.println("_________________________________________");
            System.out.println("         Choose a type of task:");
            System.out.println("_________________________________________");
            System.out.println("1 - Personal Task");
            System.out.println("2 - Study Task");
            System.out.println("3 - Work Task");
            System.out.println("4 - Exit");
            System.out.print("Option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    personalTaskController.start(scanner);
                    break;
                case 2:
                    studyTaskController.start(scanner);
                    break;
                case 3:
                    workTaskController.start(scanner);
                    break;
                case 4:
                    System.out.println("Closing the application");
                default:
                    System.out.println("Invalid input!");
            }
        } while (choice != 4);
    }
}
