package halcyon.clemncare.app.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import halcyon.clemncare.app.enums.EnrollmentStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Child> children;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Guardian> guardians;

    @OneToOne
    @JoinColumn(name = "primary_guardian_id")
    private Guardian primaryGuardian;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    public boolean hasActiveChildren() {
        return children != null &&
                children.stream().anyMatch(child -> child.getStatus() == EnrollmentStatus.ENROLLED);
    }

    public List<Child> getActiveChildren() {
        return children != null
                ? children.stream()
                        .filter(child -> child.getStatus() == EnrollmentStatus.ENROLLED).toList()
                : Collections.emptyList();
    }

    public List<Guardian> getGuardians() {
        return guardians != null
                ? guardians.stream()
                        .toList()
                : Collections.emptyList();
    }

    @Override
    public String toString() {
        return "Family{" +
                "id=" + id +
                ", address=" + address +
                ", children=" + children +
                ", guardians=" + guardians +
                '}';
    }
}
