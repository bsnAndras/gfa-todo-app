import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
  public void shouldReadWithoutError() {
    Assertions.assertDoesNotThrow(() -> ToDo.readFile(ToDo.FILEPATH));
  }

  //List Tasks Tests
  @Test
  public void shouldReturnToDoListWhenLoadToDos() {
    List<String> fileContent;
    Path filePath = ToDo.FILEPATH;
    List<Task> toDoList = new ArrayList<>();

    fileContent = ToDo.readFile(filePath);

    for (String line : fileContent) {
      toDoList.add(new Task(line));
    }

    Assertions.assertEquals(toDoList.toString(), ToDo.loadToDos(filePath).toString());

  }
}