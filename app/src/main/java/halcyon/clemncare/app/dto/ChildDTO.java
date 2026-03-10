package halcyon.clemncare.app.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import halcyon.clemncare.app.enums.DayOfWeek;
import lombok.Data;

@Data
public class ChildDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Long familyId;
    private List<String> allergies;
    private Set<DayOfWeek> frequency;
    private boolean isActive;
    private String notes;


}