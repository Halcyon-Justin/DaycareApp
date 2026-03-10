package halcyon.clemncare.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import halcyon.clemncare.app.model.Child;

@Service
public class InvoiceCalculationService {

    @Autowired
    private FamilyService familyService;


    public Long calculateAmountDue(Long familyId) {
        List<Child> children = familyService.getActiveChildrenFromFamilyId(familyId);
        Long amountDue = 0L;
        for (Child child : children) {
            if(child.getAge() < 2) {
                amountDue += 60L * 5;
            } else {
                amountDue += 50L;
            }
        }
        return amountDue;
    }
    
}
