package halcyon.clemncare.app.service;

import java.util.List;

import halcyon.clemncare.app.model.Child;

public interface ChildService {
    Child createChild(Child child);

    Child updateChild(Long id, Child child);

    Child partialUpdateChild(Long id, Child child);

    void deleteChild(Long childId);

    Child getChild(Long childId);

    List<Child> getAllChildren();

    List<Child> findChildrenByAge(int age);

    List<Child> getActiveChildren();
}
