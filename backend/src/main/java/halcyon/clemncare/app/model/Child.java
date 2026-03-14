package halcyon.clemncare.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonIgnore;

import halcyon.clemncare.app.enums.EnrollmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(columnDefinition = "DATE DEFAULT '1900-01-01'")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "family_id")
    private Family family;

    private LocalDate enrollmentDate;

    private LocalDate withdrawalDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20) DEFAULT 'ENROLLED'")
    private EnrollmentStatus status;

    @Column
    private String notes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Transient
    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }

        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @Transient
    public int getAgeInMonths() {

        if (dateOfBirth == null) {
            return 0;
        }

        return Period.between(dateOfBirth, LocalDate.now()).getYears() * 12
                + Period.between(dateOfBirth, LocalDate.now()).getMonths();
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + getAge() +
                ", enrollmentStatus=" + status +
                ", notes='" + notes + '\'' +
                '}';
    }
}
