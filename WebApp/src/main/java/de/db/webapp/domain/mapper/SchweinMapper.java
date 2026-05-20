package de.db.webapp.domain.mapper;


import de.db.webapp.domain.model.Schwein;
import de.db.webapp.persistence.entity.SchweinEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinMapper {
    Schwein convert(SchweinEntity schweinEntity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> schweinEntities);
}
