package halcyon.clemncare.app.dto;

import java.util.List;
import lombok.Data;

@Data
public class FamilyDTO {

    private Long id;

    private Long addressId;
    private List<Long> childrenIds;
    private List<Long> guardianIds;
    private List<Long> invoiceIds;
}
