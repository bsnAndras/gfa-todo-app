public class Task {
  //[state] <description>
  String description;
  State state;

  enum State {
    TODO(' '),IN_PROGRESS('.'),DONE('X'),WONT_DO('-');
    private final char state;

    State(char state) {
      this.state = state;
    }

    public char getChar(){
      return state;
    }
  }

  public Task(String description) {
    this.description = description;
    state = State.TODO;
  }

  @Override
  public String toString() {
    return description;
  }
}
