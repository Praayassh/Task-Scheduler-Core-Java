package np.com.prayashsapkota.taskmanager.base;

public class NewTask {
    private String taskDescription;
    private String dueDate;
    private String priority;

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

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String toString() {
        return "Task Description : "+getTaskDescription()+"\nDate of Creation : "+getDueDate()+"\nPriority : "+getPriority()+" ";
    }


}
