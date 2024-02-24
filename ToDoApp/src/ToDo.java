import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class ToDo {
  public static final Path FILEPATH = Paths.get(
      "C:\\Users\\F. András\\Documents\\Prog\\Workspace\\bsnAndras-todo-app\\ToDoApp\\src\\todos.txt");
  public static List<Task> toDoList = new ArrayList<>();

  public static void main(String[] args) {

    if (args.length == 0) {
      printUsage();
      return;
    }
    loadToDos(FILEPATH);
    switch (args[0]) {
      case "-l" -> listTasks(); //for listing out tasks
      case "-a" -> {
        //for adding tasks
        if (args.length >= 2) {
          addTask(args[1]);
        } else {
          System.err.println("Unable to add: no task provided");
        }
      }
      case "-r" -> removeTask(args[1]); //for removing tasks
      case "-c" -> checkTask(args[1]); //for checking tasks
      default -> {
        System.err.println("Unsupported argument, try again!");
        printUsage();
      }
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

  /**
   * <h1>List tasks</h1>
   * <ul>
   *  <ul>
   *   <li><b>Given</b> the terminal opened in the project directory</li>
   * <li>And the file where you store your data exists</li>
   * <li>And a task with the description Walk the dog is stored in the file</li>
   * <li>And a task with the description Buy milk is stored in the file</li>
   * <li>And a task with the description Do homework is stored in the file</li>
   * </ul>
   * <ul><li><b>When</b> the application is run with -l argument</li></ul>
   * <ul><li><b>Then</b> it should print the tasks that are stored in the file</li>
   * <li>And it should add numbers before each task</li></ul>
   * </ul>
   * $ todo -l
   * 1 - Walk the dog
   * 2 - Buy milk
   * 3 - Do homework
   * <p>
   * <h2>Empty list</h2>
   * - <b>Given</b> the terminal opened in the project directory
   * - And the file where you store your data exists
   * - And the file has 0 task
   * - <b>When</b> the application is ran with `-l` argument
   * - <b>Then</b> it should show a message like this: `No todos for today! :)`
   */
  private static void listTasks() {

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
      throw new RuntimeException("File not found", e);
    }
    return fileContent;
  }

//  public static int saveFile(List<Task> toDoList, Path filePath) {
//
//    try {
//      Files.writeString(filePath, newLine + "\n", StandardOpenOption.CREATE,
//          StandardOpenOption.APPEND);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//    return 0;
//  }

  /**
   * <h1>Add new task</h1>
   * <ul>
   *   <li><b>Given</b> the terminal opened in the project directory</li>
   *   <li><b>When</b> the application is ran with the `-a "Feed the monkey"` argument</li>
   *   <li><b>Then</b> it should add a new task with the description *Feed the monkey*</li>
   * </ul>
   * <h2>Add new task error handling</h2>
   *  <ul>
   *    <li><b>Given</b> the terminal opened in the project directory</li>
   *    <li><b>When</b> the application is ran with the <b>-a</b> argument</li>
   *    <li><b>Then</b> it should show an error message like:</li>
   *    <li><em>`Unable to add: no task provided`</em></li>
   *  </ul>
   */
  public static void addTask(String newTaskDescription) {
    Task newTask = new Task(newTaskDescription);
    toDoList.add(newTask);
  }

  /**
   * <h1>Check task</h1>
   * <ul>
   *   <li>Given the terminal opened in the project directory</li>
   *   <li>And the file where you store your data exists</li>
   *   <li>And the file has at least 2 tasks</li>
   *   <li>When the application is ran with the -c 2 argument</li>
   *   <li>Then it should check the second task from the file</li>
   * </ul>
   */
  public static void checkTask(String lineIndexStr) {
    int lineNr = Integer.parseInt(lineIndexStr);
    if (toDoList.size() >= lineNr) {
      System.out.println(toDoList.get(lineNr - 1));
    } else {
      System.err.println("index out of bound");
    }
  }

  /**
   * <h1>Remove task</h1>
   * <ul>
   *  <li>Given the terminal opened in the project directory</li>
   *  <li>And the file where you store your data exists</li>
   *  <li>And the file has at least 2 tasks</li>
   *  <li>When the application is ran with the -r 2 argument</li>
   *  <li>Then it should remove the second task from the file</li>
   * </ul>
   */
  public static void removeTask(String lineIndexStr) {
    int lineNr = Integer.parseInt(lineIndexStr);

    if (toDoList.size() >= lineNr) {
      toDoList.remove(lineNr - 1);
    } else {
      System.err.println("index out of bound");
    }
  }
}