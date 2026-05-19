package de.db.webapp.persistence.repository;

import de.db.webapp.persistence.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonenRepository extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p from PersonEntity p")
    Iterable<PersonEntity> herbert();

    @Query("select p.nachname from PersonEntity p where p.vorname like :vorname")
    Iterable<String> franz(String vorname);

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> projektion();
}
