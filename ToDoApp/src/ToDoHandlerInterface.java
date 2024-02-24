import java.util.List;

public interface ToDoHandlerInterface {
  void addTask(Task task);
  void removeTask(Task task);
  void listTasks(List<Task> toDoList);
  void checkTask(Task task);
}
