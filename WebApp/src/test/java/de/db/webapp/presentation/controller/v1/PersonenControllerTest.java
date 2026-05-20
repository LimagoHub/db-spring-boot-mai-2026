package de.db.webapp.presentation.controller.v1;

import de.db.webapp.domain.NotFoundException;
import de.db.webapp.domain.PersonenService;
import de.db.webapp.domain.PersonenServiceException;
import de.db.webapp.domain.model.Person;
import de.db.webapp.presentation.dto.PersonDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
//@Sql({"/create.sql", "/insert.sql"})
class PersonenControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private PersonenService personenServiceMock;


    @Test
    void findByIdTest() throws PersonenServiceException {

        // Arrange
        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        // Record
        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

       var personDto =  restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
       assertEquals("John", personDto.getVorname());
    }

    @Test
    void test2() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var s = restTemplate.getForObject("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        System.out.println(s);
    }

    @Test
    void test3() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.of(Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build());

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);


        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        var personDto =entity.getBody();
        assertEquals("John", personDto.getVorname());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    void test4() throws PersonenServiceException {

        final Optional<Person> optionalPerson = Optional.empty();

        when(personenServiceMock.findeNachId(any())).thenReturn(optionalPerson);

        var entity = restTemplate.getForEntity("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDto.class);
        //var personDto =entity.getBody();

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }
    @Test
    void test5() throws Exception{

        var personDto = PersonDto.builder().id(UUID.randomUUID()).vorname("Max").nachname("Mustermann").build();
        HttpEntity<PersonDto> httpEntity = new HttpEntity<>(personDto);
        var personen = List.of(
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Doe").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Rambo").build(),
                Person.builder().id(UUID.fromString("86dac2d5-7edc-483a-abc6-239e5b93eb13")).vorname("John").nachname("Wayne").build()
        );
        when(personenServiceMock.findeAlle()).thenReturn(personen);
        var entity = restTemplate.exchange("/v1/personen", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<PersonDto>>() { });
        assertEquals(3, entity.getBody().size());
        verify(personenServiceMock, times(1)).findeAlle();
    }

    @Test
    void test6() throws PersonenServiceException {
        doThrow(NotFoundException.class).when(personenServiceMock).loesche(any());
        var entity = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

    @Test
    void test7() throws PersonenServiceException {

        var entity = restTemplate.exchange("/v1/personen/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        verify(personenServiceMock, times(1)).loesche(UUID.fromString("b2e24e74-8686-43ea-baff-d9396b4202e0"));
    }


}