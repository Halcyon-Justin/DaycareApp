package halcyon.clemncare.app.listeners;

import java.util.Date;

import halcyon.clemncare.app.model.TimeStampedEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class EntityListener {

    @PrePersist
    public void beforePersist(TimeStampedEntity entity) {
        entity.setCreatedDate(new Date());
    }

    @PreUpdate
    public void beforeUpdate(TimeStampedEntity entity) {
        entity.setUpdateDate(new Date());
    }
    
}
