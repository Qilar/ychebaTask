package taskTracker.exception;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){
       super(message);
    }
}
