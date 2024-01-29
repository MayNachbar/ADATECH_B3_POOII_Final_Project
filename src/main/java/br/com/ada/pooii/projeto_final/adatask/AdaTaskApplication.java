package br.com.ada.pooii.projeto_final.adatask;

import br.com.ada.pooii.projeto_final.adatask.controller.PersonalTaskController;
import br.com.ada.pooii.projeto_final.adatask.controller.StudyTaskController;
import br.com.ada.pooii.projeto_final.adatask.controller.WorkTaskController;
import br.com.ada.pooii.projeto_final.adatask.repository.InMemoryTaskRepository;
import br.com.ada.pooii.projeto_final.adatask.repository.TaskRepository;
import br.com.ada.pooii.projeto_final.adatask.service.Task;
import br.com.ada.pooii.projeto_final.adatask.service.TaskService;

import java.util.Scanner;

public class AdaTaskApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TaskRepository taskRepository = new InMemoryTaskRepository();
        Task taskService = new TaskService(taskRepository);

        PersonalTaskController personalTaskController = new PersonalTaskController(taskService);
        StudyTaskController studyTaskController = new StudyTaskController(taskService);
        WorkTaskController workTaskController = new WorkTaskController(taskService);

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
