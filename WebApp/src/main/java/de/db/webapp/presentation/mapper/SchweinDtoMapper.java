package de.db.webapp.presentation.mapper;


import de.db.webapp.domain.model.Schwein;
import de.db.webapp.presentation.dto.SchweinDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDtoMapper {
    SchweinDto convert(Schwein schwein);
    Schwein convert(SchweinDto schweinDto);
    Iterable<SchweinDto> convert(Iterable<Schwein> schweine);
}
