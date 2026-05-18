package de.db.webapp.persistence.repository;

import de.db.webapp.persistence.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonenRepository extends CrudRepository<PersonEntity, UUID> {
}
