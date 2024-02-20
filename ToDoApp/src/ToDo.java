/*
Given the terminal opened in the project directory
When the application is run without any arguments
Then it should print the usage instructions
 */
public class ToDo {
  public static void main(String[] args) {
    if(args.length == 0){
      printUsage();
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
}
