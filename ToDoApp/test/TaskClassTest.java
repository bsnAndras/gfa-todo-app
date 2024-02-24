import org.junit.jupiter.api.*;

public class TaskClassTest {
  Task task;

  @Test
  public void constructorTest() {
    String d = "task";
    task = new Task(d);
    Assertions.assertEquals(d, task.description);
    Assertions.assertFalse(task.getIsDone());
  }

  @Test
  public void getDescriptionTest() {
    String d = "description";
    task = new Task(d);
    Assertions.assertEquals(d, task.getDescription());
  }

  @Test
  void shouldGetIsDoneFalse() {
    task = new Task("description");
    Assertions.assertFalse(task.getIsDone());
  }

  @Test
  void serializeTask() {
    String description = "description";
    task = new Task(description);
    Assertions.assertEquals("[ ] description", task.serializeTask());
  }

  @Test
  void shouldCreateTaskBasedOnText() {
    String text = "[X] <description>";
    task = Task.getTaskFromDeSerialization(text);
    Assertions.assertEquals("<description>",task.getDescription());
    Assertions.assertTrue(task.getIsDone());
  }
}
