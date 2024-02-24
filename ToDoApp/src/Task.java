public class Task {
  //[state] <description>
  final String description;
  private boolean isDone;

  public Task(String description) {
    this.description = description;
    isDone = false;
  }

  public String getDescription() {
    return description;
  }

  public boolean getIsDone() {
    return isDone;
  }

  public String serializeTask() {
    return String.format("[%c] %s",
        isDone ? 'X' : ' ',
        getDescription());
  }

  public static Task getTaskFromDeSerialization(String text) {
    String description;
    boolean isDone;

    description = text.substring(4);

    Task task = new Task(description);
    if (text.charAt(1) == 'X') {
      task.isDone = true;
    } else {
      task.isDone = false;
    }
    return task;
  }
}
