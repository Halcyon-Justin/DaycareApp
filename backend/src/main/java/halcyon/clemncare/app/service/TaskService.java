package halcyon.clemncare.app.service;

import java.util.List;

import halcyon.clemncare.app.model.Task;

public interface TaskService {

    public Task createTask(Task task);

    public Task updateTask(Long id, Task task);

    public Task getTask(Long id);

    public List<Task> getTasks();
    
}
