package halcyon.clemncare.app.dto;

import halcyon.clemncare.app.enums.StateCode;
import lombok.Data;

@Data
public class HomeAddressDTO {

    private Long id;
    private String streetAddress;
    private String city;
    private StateCode state;
    private String zipCode;
}
