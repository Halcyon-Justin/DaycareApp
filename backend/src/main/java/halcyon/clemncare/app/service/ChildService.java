package halcyon.clemncare.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import halcyon.clemncare.app.model.Child;

public interface ChildService {
    Child createChild(Child child);

    Child updateChild(Long id, Child child);

    Child partialUpdateChild(Long id, Child child);

    void deleteChild(Long childId);

    Child getChild(Long childId);

    Page<Child> getAllChildren(Pageable pageable);

    List<Child> findChildrenByAge(int age);

    List<Child> getEnrolledChildren();
}
