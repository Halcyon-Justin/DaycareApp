package halcyon.clemncare.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Address;

@Repository
public interface HomeAddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(Long id);

}
