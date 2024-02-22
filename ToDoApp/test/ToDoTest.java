import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoTest {
  ToDo todo;
  String[] args;
  @BeforeEach
  public void setTodo() {
    todo = new ToDo();

  }

  //readFile test
  @Test
  public void shouldReadWithoutError(){
    Assertions.assertDoesNotThrow(()-> ToDo.readFile(ToDo.FILEPATH));
  }

  //List Tasks Tests
  @Test
  public void shouldReturnToDoListWhenLoadToDos(){
    Assertions.assertDoesNotThrow(()-> Files.readAllLines(ToDo.FILEPATH));
  }
}