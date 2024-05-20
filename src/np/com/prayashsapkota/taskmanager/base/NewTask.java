package np.com.prayashsapkota.taskmanager.base;

public class NewTask {
    private final String taskDescription;
    private final String dueDate;
    private final String priority;

    public NewTask(String taskDescription, String dueDate, String priority) {
        this.taskDescription = taskDescription;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public String toString() {
        return "Task Description : "+getTaskDescription()+"\nDate of Creation : "+getDueDate()+"\nPriority : "+getPriority()+" ";
    }


}
