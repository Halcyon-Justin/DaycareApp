package halcyon.clemncare.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import halcyon.clemncare.app.enums.Relationship;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "guardian")
public class Guardian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    @Column(columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean isEmergencyContact;

    public String getName() {
        return firstName + " " + lastName;
    }

}
