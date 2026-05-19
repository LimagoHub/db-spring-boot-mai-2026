package de.db.webapp.presentation.controller.v1;


import de.db.webapp.domain.PersonenService;
import de.db.webapp.domain.PersonenServiceException;
import de.db.webapp.presentation.dto.PersonDto;
import de.db.webapp.presentation.errorhandler.IdMismatchException;
import de.db.webapp.presentation.mapper.PersonDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor// Name der resource
public class PersonenController {

    private final PersonenService  personenService;
    private final PersonDtoMapper personDtoMapper;

    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,  MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> getPerson(@PathVariable UUID id) throws PersonenServiceException {

       return ResponseEntity.of(personenService.findeNachId(id).map(personDtoMapper::convert));
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDto>> getAllPersons(
            @RequestParam(required = false, defaultValue = "Fritz") String vorname,
            @RequestParam(required = false, defaultValue = "Schmitt")String nachname
    )throws PersonenServiceException {
        System.out.printf("Vorname = %s, Nachname = %s\n", vorname, nachname);


        return ResponseEntity.ok(personDtoMapper.convert(personenService.findeAlle()));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) throws PersonenServiceException{
        personenService.loesche(id);
        return ResponseEntity.ok().build();

    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPerson(@Valid @RequestBody PersonDto personDto,  UriComponentsBuilder uriBuilder)throws PersonenServiceException {

        personenService.speichern(personDtoMapper.convert(personDto));
        UriComponents uriComponents = uriBuilder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();


    }

    @PutMapping(path="{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@PathVariable UUID id,@Valid @RequestBody PersonDto personDto)  throws PersonenServiceException{

        if(! id.equals(personDto.getId())) {
            throw new IdMismatchException("The ID in the URL path does not match the ID in the request body.");
        }

        personenService.aendern(personDtoMapper.convert(personDto));

        return ResponseEntity.ok().build();

    }
}
