import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoTest {
  ToDo todo;
  List<Task> toDoList;
  String[] args;
  static Path TESTFILE_PATH;
  @BeforeAll
  public static void setFile(){
    TESTFILE_PATH = Paths.get(
        "C:\\Users\\F. András\\Documents\\Prog\\Workspace\\" +
            "bsnAndras-todo-app\\ToDoApp\\test\\test.txt");
    try {
      Files.deleteIfExists(TESTFILE_PATH);
      Files.writeString(TESTFILE_PATH,
          "Testing file for To Do Application:\n" +
              "------------------------------------\n",StandardOpenOption.CREATE);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @BeforeEach
  public void setTodo() {
    todo = new ToDo();
    toDoList = new ArrayList<>();
  }

  @AfterEach
  public void testEnd(){
    try {
      Files.writeString(TESTFILE_PATH, "----------End of Test-----------\n", StandardOpenOption.APPEND);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  //readFile test
  @Test
  public void shouldReadWithoutError() {
    Assertions.assertDoesNotThrow(() -> ToDo.readFile(TESTFILE_PATH));
  }

  //writeFile test
  @Test
  public void shouldWriteWithoutError() {
    Assertions.assertDoesNotThrow(() -> ToDo.writeFile("test", TESTFILE_PATH));
  }

  //List Tasks Tests
  @Test
  public void shouldReturnToDoListWhenLoadToDos() {
    List<String> fileContent;
    toDoList = new ArrayList<>();

    fileContent = ToDo.readFile(TESTFILE_PATH);

    for (String line : fileContent) {
      toDoList.add(new Task(line));
    }

    Assertions.assertEquals(toDoList.toString(), ToDo.loadToDos(TESTFILE_PATH).toString());
  }

  //addTask test
  @Test
  public void shouldAddTask() {
    ToDo.addTask("Feed the monkey", TESTFILE_PATH);
    Assertions.assertEquals("Feed the monkey", ToDo.readFile(TESTFILE_PATH).getLast());
  }

  //checkTask test
  @Test
  void shouldCheckTask() {
    ToDo.checkTask(TESTFILE_PATH, "2");
  }
}