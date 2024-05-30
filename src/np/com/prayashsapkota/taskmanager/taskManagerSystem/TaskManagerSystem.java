package np.com.prayashsapkota.taskmanager.taskManagerSystem;

import np.com.prayashsapkota.taskmanager.base.NewTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskManagerSystem {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final Map<String, NewTask> taskList = new HashMap<>();

    private static int intUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private static String stringUserInput() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    private static void mainMenu() {
        System.out.println(" ");
        System.out.println("Task Scheduler");
        System.out.println(" ");
        System.out.println("1. View pending tasks");
        System.out.println("2. Create New Task");
        System.out.println("3. Task Remainder");
        System.out.println("4. Exit");
        System.out.println(" ");
        System.out.print("Please choose your option : ");
        int option = intUserInput();
        switch (option) {
            case 1 -> pendingTasks();
            case 2 -> newTask();
            case 3 -> taskRemainder();
            case 4 -> exit();
            default -> {
                System.out.println("Please enter a valid choice!!!!");
                mainMenu();
            }
        }
    }

    private static void pendingTasks() {
        executorService.submit(() -> {
            System.out.println(" ");
            System.out.println("Pending Tasks");
            System.out.println(" ");
            try (FileReader reader = new FileReader("taskmanage.txt")) {
                if (reader.read() == -1) {
                    System.out.println("There are no pending tasks!!!");
                    System.out.println(" ");
                    System.out.print("Press 9 to create a new task or 0 to go back to the main menu : ");
                    int opt = intUserInput();
                    if (opt == 9) {
                        newTask();
                    } else if (opt == 0) {
                        mainMenu();
                    } else {
                        System.out.println("Please enter a valid option!!!!");
                    }
                } else {
                    int read;
                    while ((read = reader.read()) != -1) {
                        System.out.print((char) read);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found : " + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.print("\nPress 0 to go to the main menu : ");
            int choice = intUserInput();
            if (choice == 0) {
                mainMenu();
            } else {
                System.out.println("Please enter a valid option!!!!");
            }
        });
    }

    private static void newTask() {
            System.out.println("\nCreate new tasks ");
            System.out.println(" ");
            System.out.print("Enter task name/description : ");
            String taskName = stringUserInput();
            System.out.print("Please create a task id : ");
            String taskId = stringUserInput();
            System.out.print("Enter Date ((YYYY-MM-DD) : ");
            String date = stringUserInput();
            System.out.print("Enter priority level (Low/Medium/High) : ");
            String priority = stringUserInput();
            NewTask task = new NewTask(taskName, date, priority);
            if (taskList.containsKey(taskId)) {
                System.out.println("The following id is already specified for a task." +
                        " Please provide a new id!!!!");
                newTask();
            } else {
                taskList.put(taskId, task);
                try (FileWriter writer = new FileWriter("taskmanage.txt", true)) {
                    String taskValueEntry = task.toString();
                    writer.write(" ID : " + taskId + "\n" + taskValueEntry + "\n");
                } catch (IOException e) {
                    System.out.println("Exception occurred : " + e.getMessage());
                }
                executorService.submit(() -> {
                    System.out.println("Task is being created, Please wait.....");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException exception) {
                        System.out.println(exception.getMessage());
                    }

                    System.out.println(" ");
                    System.out.println("Your task has been successfully created!!");
                    System.out.println(" ");
                    System.out.print("Press 9 to view your pending tasks or Press 0 to go to the main menu : ");
                    int choice = intUserInput();
                    if (choice == 9) {
                        pendingTasks();
                    } else if (choice == 0) {
                        mainMenu();
                    } else {
                        System.out.println("Please enter a valid option!!!!");
                        newTask();
                    }
                });
            }
        }

    private static void taskRemainder() {
        executorService.submit(() -> {
            System.out.println(" ");
            System.out.println("Remainder");
            System.out.println(" ");
            try (FileReader reader = new FileReader("taskmanage.txt")) {
                if (reader.read() == -1) {
                    System.out.println("There is no tasks!!!");
                    System.out.println(" ");
                    System.out.print("Press 9 to create a new task or 0 to go back to main menu : ");
                    int opt = intUserInput();
                    if (opt == 9) {
                        newTask();
                    } else if (opt == 0) {
                        mainMenu();
                    } else {
                        System.out.println("Please enter a valid option!!!!");
                    }
                } else {
                    int read;
                    while ((read = reader.read()) != -1) {
                        System.out.print((char) read);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found : " + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.print("\nPress 0 to go to main menu : ");
            int choice = intUserInput();
            if (choice == 0) {
                mainMenu();
            } else {
                System.out.println("Please enter a valid option!!!!");
            }
        });
    }

    private static void exit() {
        System.out.println("Thank you for using Task Scheduler!!!! ");
        executorService.shutdownNow();
    }

    public static void run() {
        mainMenu();
    }
}
//>>>>>