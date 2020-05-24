package sg.edu.np.mad.mad_recyclerview;

public class Task {
    private String taskDescription;
    private Boolean taskStatus = false;
    public Task(String description) {
        this.taskDescription = description;
    }

    public void TaskStatusUpdate(Boolean completed) {
        this.taskStatus = completed;
    }
    public String GetTaskDescription() {
        return this.taskDescription;
    }
    public Boolean GetTaskStatus() {
        return (this.taskStatus);
    }
}
