import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static final Path FILEPATH = Path.of(
      "todos.txt");
  public static List<Task> toDoList = new ArrayList<>();

  public static void main(String[] args) {

    if (args.length == 0) {
      printUsage();
      return;
    }
    try {
      toDoList = load(FILEPATH);
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
      save(toDoList, FILEPATH);
    } catch (IOException e) {
      throw new RuntimeException("File not found", e);
    }
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


  public static List<Task> load(Path filePath) throws IOException {
    List<String> fileContent;
    fileContent = readFile(filePath);

    for (String line : fileContent) {
      toDoList.add(Task.getTaskFromDeSerialization(line));
    }

    return toDoList;
  }

  public static List<String> readFile(Path filePath) throws IOException {
    List<String> fileContent;
    fileContent = Files.readAllLines(filePath);
    return fileContent;
  }

  public static void save(List<Task> toDoList, Path filePath) throws IOException {
    StringBuilder fileContent = new StringBuilder();
    for (Task task : toDoList) {
      fileContent.append(task.serializeTask()).append(System.lineSeparator());
    }
    Files.writeString(filePath, fileContent, StandardOpenOption.TRUNCATE_EXISTING);
  }
}