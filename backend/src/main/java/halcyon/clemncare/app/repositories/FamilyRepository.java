package halcyon.clemncare.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findById(Family family);
}
