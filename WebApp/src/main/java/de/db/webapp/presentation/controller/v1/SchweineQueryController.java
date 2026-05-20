package de.db.webapp.presentation.controller.v1;


import de.db.webapp.domain.SchweineService;
import de.db.webapp.presentation.dto.SchweinDto;
import de.db.webapp.presentation.mapper.SchweinDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/schweine")
@RequiredArgsConstructor
public class SchweineQueryController {

    private final SchweineService schweineService;
    private final SchweinDtoMapper mapper;

    @Operation(summary = "Liefert ein Schwein")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schwein gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SchweinDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Schwein nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})



    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SchweinDto> getSchwein(UUID id) {
        return ResponseEntity.of(schweineService.findeNachId(id).map(mapper::convert));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SchweinDto>> getSchweine(
         @RequestParam(required = false, defaultValue = "0") Integer mindestGewicht
    ) {
        return ResponseEntity.ok(mapper.convert(schweineService.findeAlle(mindestGewicht)));
    }


}
