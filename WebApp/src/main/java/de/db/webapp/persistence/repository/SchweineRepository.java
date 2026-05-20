package de.db.webapp.persistence.repository;

import de.db.webapp.persistence.entity.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SchweineRepository extends CrudRepository<SchweinEntity, UUID> {
    Iterable<SchweinEntity> findByGewichtIsGreaterThanEqual(int mindestgewicht);
}
