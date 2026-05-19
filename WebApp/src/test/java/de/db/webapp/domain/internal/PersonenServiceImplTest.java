package de.db.webapp.domain.internal;

import de.db.webapp.domain.PersonenServiceException;
import de.db.webapp.domain.mapper.PersonMapper;
import de.db.webapp.persistence.repository.PersonenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {
    @InjectMocks
    private PersonenServiceImpl objectUnderTest;

    @Mock
    private PersonenRepository personRepositoryMock;


    @Mock
    private PersonMapper mapperMock;

    @Test
    @DisplayName("speichern mit leerem Parameter erwartet eine PersonenServiceException")
    void speichernParameterNull() throws Exception {
        final PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Parameter darf nicht null sein", ex.getMessage());
    }
}