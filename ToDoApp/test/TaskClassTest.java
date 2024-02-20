import org.junit.jupiter.api.*;
public class TaskClassTest {
  Task task;
  @Test
  public void constructorTest(){
    String d = "task";
    task = new Task(d);
    Assertions.assertEquals(d,task.description);
    Assertions.assertEquals(Task.State.TODO,task.state);
  }
  @Test
  public void toStringTest(){
    String d = "description";
    task=new Task(d);
    Assertions.assertEquals(d,task.toString());
  }
}
