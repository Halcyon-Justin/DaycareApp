package halcyon.clemncare.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import halcyon.clemncare.app.enums.EnrollmentStatus;
import lombok.Data;

@Data
public class ChildDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Integer age;

    private String allergies;

    private String notes;

    private Long familyId;

    private LocalDate enrollmentDate;

    private LocalDate withdrawalDate;

    private EnrollmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}