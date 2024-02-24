public interface ToDoHandlerInterface {
  void addTask(String taskDescription);
  void removeTask(String lineIndex);
  void listTasks();
  void checkTask(String lineIndex);
}
