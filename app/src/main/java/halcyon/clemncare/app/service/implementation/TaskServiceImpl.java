package halcyon.clemncare.app.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.exception.GuardianNotFoundException;
import halcyon.clemncare.app.model.Task;
import halcyon.clemncare.app.repositories.TaskRepository;
import halcyon.clemncare.app.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()){
            Task existingTask = optionalTask.get();
            BeanUtils.copyProperties(task, existingTask);
            return taskRepository.save(existingTask);
        } else {
            throw new GuardianNotFoundException("Task Not Found");
        }
    }

    @Override
    public Task getTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()){
            return optionalTask.get();
        } else {
            throw new GuardianNotFoundException("Task Not Found");
        }
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    } 
}
