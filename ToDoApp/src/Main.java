import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static final Path FILEPATH = Paths.get(
      "C:\\Users\\F. András\\Documents\\Prog\\Workspace\\bsnAndras-todo-app\\ToDoApp\\src\\todos.txt");
  public static List<Task> toDoList = new ArrayList<>();

  public static void main(String[] args) {

    if (args.length == 0) {
      printUsage();
      return;
    }
    toDoList=load(FILEPATH);
    ToDoHandler toDoHandler = new ToDoHandler(toDoList);
    switch (args[0]) {
      case "-l" -> toDoHandler.listTasks(); //for listing out tasks
      case "-a" -> {
        //for adding tasks
        if (args.length >= 2) {
          toDoHandler.addTask(args[1]);
        } else {
          System.err.println("Unable to add: no task provided");
        }
      }
      case "-r" -> toDoHandler.removeTask(args[1]); //for removing tasks
      case "-c" -> toDoHandler.checkTask(args[1]); //for checking tasks
      default -> {
        System.err.println("Unsupported argument, try again!");
        printUsage();
      }
    }
//    save(toDoList,FILEPATH);
  }

  /**
   * <h1>Print usage</h1>
   * <ul>
   * <li><b>Given</b> the terminal opened in the project directory</li>
   * <li><b>When</b> the application is run without any arguments</li>
   * <li><b>Then</b> it should print the usage instructions</li>
   * </ul>
   */
  private static void printUsage() {
    System.out.print("\nCommand Line Todo application\n");
    System.out.print("=============================\n\n");

    System.out.println("Command line arguments:");
    System.out.println("    -l   Lists all the tasks");
    System.out.println("    -a   Adds a new task");
    System.out.println("    -r   Removes a task");
    System.out.println("    -c   Completes a task");
  }


  public static List<Task> load(Path filePath) {
    List<String> fileContent;
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
      throw new RuntimeException("File not found", e);
    }
    return fileContent;
  }

//  public static int save(List<Task> toDoList, Path filePath) {
//
//    try {
//      Files.writeString(filePath, ,StandardOpenOption.CREATE,
//          StandardOpenOption.APPEND);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//    return 0;
//  }
}