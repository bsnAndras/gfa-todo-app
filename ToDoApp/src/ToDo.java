import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.*;
import java.util.List;

/*
Given the terminal opened in the project directory
When the application is run without any arguments
Then it should print the usage instructions
 */
public class ToDo {
  public static void main(String[] args) {
    if(args.length == 0){
      printUsage();
      return;
    }

    switch (args[0]){
      case "-l":
        listTasks();
        break;
      case "-a":
        //not implemented yet
        System.out.println("Function is not implemented yet");
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
        System.out.println("Invalid argument, try again!");
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
   *
   * $ todo -l
   *
   * 1 - Walk the dog
   * 2 - Buy milk
   * 3 - Do homework
   */
  private static void listTasks() {
    List<String> toDoList;
    try {
      toDoList=Files.readAllLines(Paths.get("todos.txt"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    for (int i = 0; i < toDoList.size(); i++) {
      System.out.printf("%d - %s\n",i+1,toDoList.get(i));
    }
  }
}
