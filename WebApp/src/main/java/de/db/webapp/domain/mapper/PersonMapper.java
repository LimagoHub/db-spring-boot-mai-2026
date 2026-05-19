package de.db.webapp.domain.mapper;

import de.db.webapp.domain.model.Person;
import de.db.webapp.persistence.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonEntity convert(Person person);
    Person convert(PersonEntity person);
    Iterable<Person> convert(Iterable<PersonEntity> personEnties);
}
