package de.db.webapp.domain.model;

import lombok.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Person {

    private UUID id;


    private String vorname;


    private String nachname;


}
