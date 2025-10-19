public class Task {
    String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void markAsCompleted() {
        this.isCompleted = true;
    }
    public void markAsIncomplete() {
        this.isCompleted = false;
    }
}
