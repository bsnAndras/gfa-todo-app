import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ToDo {
  public static final Path FILEPATH = Paths.get("C:\\Users\\F. András\\Documents\\Prog\\Workspace\\bsnAndras-todo-app\\ToDoApp\\src\\todos.txt");
  public static List<Task> toDoList = new ArrayList<>();

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
        listTasks(FILEPATH);
        break;
      case "-a":
        //not implemented yet
        //addTask(FILEPATH,args[1]);
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
   * <p>
   * ### Empty list:
   * - **Given** the terminal opened in the project directory
   * - And the file where you store your data exists
   * - And the file has 0 task
   * - **When** the application is ran with `-l` argument
   * - **Then** it should show a message like this: `No todos for today! :)`
   */
  private static void listTasks(Path filePath) {
    toDoList = loadToDos(filePath);

    if (toDoList.isEmpty()) {
      System.out.println("No todos for today! :)");
      return;
    }
    for (int i = 0; i < toDoList.size(); i++) {
      System.out.printf("%d - %s\n", i + 1, toDoList.get(i));
    }
  }

  public static List<Task> loadToDos(Path filePath) {
    List<String> fileContent;
    List<Task> toDoList = new ArrayList<>();
    fileContent = readFile(filePath);

    for (String line : fileContent) {
      toDoList.add(new Task(line));
    }

    return toDoList;
  }

  public static List<String> readFile(Path filePath) {
    List<String> fileContent;
    try {
      fileContent = Files.readAllLines(filePath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return fileContent;
  }
}