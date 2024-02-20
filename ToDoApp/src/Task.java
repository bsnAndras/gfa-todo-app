public class Task {
  String description;
  State state;

  enum State{TODO,IN_PROGRESS,DONE,WONT_DO}

  public Task(String description) {
    this.description = description;
    state=State.TODO;
  }
}
