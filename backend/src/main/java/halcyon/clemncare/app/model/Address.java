package halcyon.clemncare.app.model;

import halcyon.clemncare.app.enums.StateCode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetAddress;
    private String city;

    @Enumerated(EnumType.STRING)
    private StateCode state;

    private String zipCode;

    public String getAddressDetails() {
        return String.format("%s, %s, %s %s", streetAddress, city, state, zipCode);
    }

}
