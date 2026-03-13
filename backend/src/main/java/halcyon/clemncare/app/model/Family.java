package halcyon.clemncare.app.model;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToOne
    @JoinColumn(name = "address_id")
    private HomeAddress address;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Child> children;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Guardian> guardians;

    @JsonManagedReference
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    public boolean hasActiveChildren() {
        return children != null && children.stream().anyMatch(Child::isActive);
    }

    public List<Child> getActiveChildren() {
        return children != null ? children.stream().filter(Child::isActive).toList() : Collections.emptyList();
    }

    public List<Guardian> getGuardians() {
        return guardians.stream().filter(guardian -> !guardian.isEmergencyContact()).toList();
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
