package halcyon.clemncare.app.dto;

import java.util.List;

import lombok.Data;

@Data
public class ReportCardDTO {
    
    private Long childId;
    private boolean hasNapped;
    private String notes;
    private List<String> sendTo;
}
