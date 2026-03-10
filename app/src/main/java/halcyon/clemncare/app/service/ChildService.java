package halcyon.clemncare.app.service;

import java.util.List;

import halcyon.clemncare.app.dto.ChildDTO;
import halcyon.clemncare.app.model.Child;

public interface ChildService {
    public Child createChild(ChildDTO childDTO);

    public Child updateChild(Long id, ChildDTO childDTO);

    public Child partialUpdateChild(Long id, ChildDTO childDTO);

    public void deleteChild(Long childId);

    public Child getChild(Long childId);

    public List<Child> getAllChildren();

    public List<Child> findChildrenByAge(int age);
}
