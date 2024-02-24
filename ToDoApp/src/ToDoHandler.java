import java.util.List;

public class ToDoHandler implements ToDoHandlerInterface {
  List<Task> todoList;

  public ToDoHandler(List<Task> todoList) {
    this.todoList = todoList;
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
  @Override
  public void listTasks() {

    if (todoList.isEmpty()) {
      System.out.println("No todos for today! :)");
      return;
    }
    for (Task task : todoList) {
      System.out.println(task.serializeTask());
    }
  }

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
  public void addTask(String newTaskDescription) {
    Task newTask = new Task(newTaskDescription);
    todoList.add(newTask);
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
  public void checkTask(String lineIndexStr) {
    int lineNr = Integer.parseInt(lineIndexStr);
    Task task;
    if (todoList.size() >= lineNr) {
      task = todoList.get(lineNr);
      task.toggle();
      todoList.set(lineNr,task);
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
  public void removeTask(String lineIndexStr) {
    int lineNr = Integer.parseInt(lineIndexStr);

    if (todoList.size() >= lineNr) {
      todoList.remove(lineNr);
    } else {
      System.err.println("index out of bound");
    }
  }
}
