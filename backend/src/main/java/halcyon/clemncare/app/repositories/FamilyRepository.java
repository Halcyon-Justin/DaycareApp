package halcyon.clemncare.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    Optional<Family> findById(Family family);

    @Query("SELECT DISTINCT f FROM Family f LEFT JOIN FETCH f.children c WHERE c.isActive = true")
    List<Family> getAllFamiliesWithActiveChildren();
}
