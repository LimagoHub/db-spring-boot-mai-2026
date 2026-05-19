package de.db.webapp.presentation.mapper;

import de.db.webapp.domain.model.Person;
import de.db.webapp.persistence.entity.PersonEntity;
import de.db.webapp.presentation.dto.PersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDtoMapper {

    PersonDto convert(Person person);
    Person convert(PersonDto personDto);
    Iterable<PersonDto> convert(Iterable<Person> persons);
}
