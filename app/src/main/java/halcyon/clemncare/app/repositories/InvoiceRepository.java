package halcyon.clemncare.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import halcyon.clemncare.app.model.Invoice;



@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByDueDate(Date dueDate);
    List<Invoice> findByFamilyId(Long familyId);
    Page<Invoice> findByFamilyId(Long familyId, Pageable pageable);
    
}
