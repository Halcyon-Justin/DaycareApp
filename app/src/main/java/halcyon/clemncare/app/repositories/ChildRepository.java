package halcyon.clemncare.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
    Optional<Child> findById(Long id);
}