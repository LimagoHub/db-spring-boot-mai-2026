package de.db.webapp;

import de.db.webapp.persistence.repository.PersonenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class StapelImplTest {

    @InjectMocks
    private StapelImpl objectUnderTest;

    @Mock
    private PersonenRepository personenRepositoryMock;


    @Test
    void isEmpty__EmptyStack__returnsTrue() {
        assertTrue(objectUnderTest.isEmpty());
    }

}