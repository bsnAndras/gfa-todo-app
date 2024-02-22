import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ToDo {
  public static final Path FILEPATH = Paths.get("src/todos.txt");
  private static List<Task> toDoList;

  public static void main(String[] args) {
    /*
    Given the terminal opened in the project directory
    When the application is run without any arguments
    Then it should print the usage instructions
    */
    if (args.length == 0) {
      printUsage();
      return;
    }

    switch (args[0]) {
      case "-l":
        //for listing out tasks from the todos.txt
        listTasks();
        break;
      case "-a":
        //not implemented yet
        //addTask(args);
        break;
      case "-r":
        //not implemented yet
        System.out.println("Function is not implemented yet");
        break;
      case "-c":
        //not implemented yet
        System.out.println("Function is not implemented yet");
        break;
      default:
        System.err.println("Unsupported argument, try again!");
        printUsage();
        break;
    }
  }


  private static void printUsage() {
    System.out.print("\nCommand Line Todo application\n");
    System.out.print("=============================\n\n");

    System.out.println("Command line arguments:");
    System.out.println("    -l   Lists all the tasks");
    System.out.println("    -a   Adds a new task");
    System.out.println("    -r   Removes a task");
    System.out.println("    -c   Completes a task");
  }

  /**
   * Given the terminal opened in the project directory
   * And the file where you store your data exists
   * And a task with the description Walk the dog is stored in the file
   * And a task with the description Buy milk is stored in the file
   * And a task with the description Do homework is stored in the file
   * When the application is run with -l argument
   * Then it should print the tasks that are stored in the file
   * And it should add numbers before each task
   * $ todo -l
   * 1 - Walk the dog
   * 2 - Buy milk
   * 3 - Do homework
   */
  private static void listTasks() {
    toDoList = loadToDos();

    if (toDoList.isEmpty()) {
      System.out.println("No todos for today! :)");
      return;
    }
    for (int i = 0; i < toDoList.size(); i++) {
      System.out.printf("%d - %s\n", i + 1, toDoList.get(i));
    }
  }

  private static List<Task> loadToDos() {
    List<String> fileContent;
    List<Task> toDoList = new ArrayList<>();
    try {
      fileContent = Files.readAllLines(ToDo.FILEPATH);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (String line : fileContent) {
        toDoList.add(new Task(line));
    }

    return toDoList;
  }
}
