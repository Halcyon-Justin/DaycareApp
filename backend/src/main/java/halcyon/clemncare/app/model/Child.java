package halcyon.clemncare.app.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @JoinColumn(name = "family_id")
    private Family family;

    @Column(columnDefinition = "BOOLEAN DEFAULT 'FALSE'")
    private boolean isActive;

    @Column
    private String notes;

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        if (dateOfBirth != null && !dateOfBirth.isEqual(LocalDate.of(1900, 1, 1))) {
            LocalDate now = LocalDate.now();
            return now.getYear() - dateOfBirth.getYear();
        } else {
            return LocalDate.now().getYear() - 1900;
        }

    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isActive=" + isActive +
                ", notes='" + notes + '\'' +
                '}';
    }
}
