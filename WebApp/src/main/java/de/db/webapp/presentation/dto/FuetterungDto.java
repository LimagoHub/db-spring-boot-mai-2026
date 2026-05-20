package de.db.webapp.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuetterungDto {

    private UUID id;
    private UUID requestID= UUID.randomUUID();

    private int anzahlKartoffel;
}
