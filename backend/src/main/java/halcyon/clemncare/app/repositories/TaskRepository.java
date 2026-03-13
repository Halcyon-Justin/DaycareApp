package halcyon.clemncare.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
