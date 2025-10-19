import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Main todoList = new Main();
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    public void clearCheckedTasks() {
        tasks.removeIf(Task::isCompleted);
    }

    public void displayList() {
        int i = 1;
        for (Task task : tasks) {
            System.out.print(i);
            System.out.println((task.isCompleted() ? "[X]: " : "[ ]: ") + task.getDescription());
            i++;
        }
    }

    public void displayIncompleteList() {
        int i = 1;
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.print(i);
                System.out.println("[ ]: " + task.getDescription());
            }
            i++;
        }
    }

    @SuppressWarnings({"CallToPrintStackTrace", "ConvertToTryWithResources"})
    public static void main(String[] args) {

        java.io.File file = new java.io.File("database.txt");
        try {
            java.util.Scanner fileScanner = new java.util.Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                char statusChar = line.charAt(0);
                String description = line.substring(1);
                Task task = new Task(description);
                if (statusChar == '1') {
                    task.markAsCompleted();
                }
                todoList.tasks.add(task);
            }
            fileScanner.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("An error occurred while loading tasks.");
            e.printStackTrace();
        }

        while(true) {
        System.out.println("What would you like to do?\n 1. Add Task\n 2. Clear Completed Tasks\n 3. Display Task List\n 4. Check Off Task\n 5. Display Current Tasks\n 6. Close App\n 7. Clear all Tasks");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> { 
                System.out.println("Enter task description:");
                String description = scanner.nextLine();
                todoList.addTask(description);
                System.out.println("Task added.");
            }
            case 2 -> {
                todoList.clearCheckedTasks();
                System.out.println("Completed tasks cleared.");
            }
            case 3 -> {
                System.out.println("Task List:");
                todoList.displayList();
            }
            case 4 -> {
                System.out.println("Task List:");
                todoList.displayList();
                System.out.println("Enter task number to check off:");
                int taskNumber = scanner.nextInt();
                if (taskNumber > 0 && taskNumber <= todoList.tasks.size()) {
                    Task task = todoList.tasks.get(taskNumber - 1);
                    task.markAsCompleted();
                    System.out.println("Task marked as completed.");
                } else {
                    System.out.println("Invalid task number.");
                }
            }
            case 5 -> {
                System.out.println("Current Tasks:");
                todoList.displayIncompleteList();
            }
            case 6 -> {
                System.out.println("Closing app.");
                scanner.close();
                storeData();
                System.exit(0);
            }
            case 7 -> {
                todoList.tasks.clear();
                System.out.println("All tasks cleared.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }
    }

    @SuppressWarnings({"ConvertToTryWithResources", "CallToPrintStackTrace"})
    public static void storeData() {
        System.out.println("Storing data to database.txt");
        try {
            java.io.FileWriter writer = new java.io.FileWriter("database.txt");
            for (Task task : todoList.tasks) {
                writer.write(task.isCompleted() ? "1" : "0");
                writer.write(task.getDescription() + "\n");
                
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }
}


