package de.db.webapp.domain.internal;


import de.db.webapp.domain.AlreadyExistsException;
import de.db.webapp.domain.NotFoundException;
import de.db.webapp.domain.PersonenService;
import de.db.webapp.domain.PersonenServiceException;
import de.db.webapp.domain.mapper.PersonMapper;
import de.db.webapp.domain.model.Person;
import de.db.webapp.persistence.repository.PersonenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = PersonenServiceException.class, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
public class PersonenServiceImpl implements PersonenService {

    private final PersonenRepository personenRepository;
    private final PersonMapper personMapper;


     /*
                person == null -> PSE
                vorname == null oder zu kurz ->PSE
                nachname dito

                Attila -> PSE

                exception in underlying service ->PSE

                happy day -> Person to Repo

             */

    @Override
    public void speichern(final Person person) throws PersonenServiceException {

            if(person == null) throw new PersonenServiceException("Parameter darf nicht null sein");
            if(personenRepository.existsById(person.getId())) throw new AlreadyExistsException("Datensatz existiert bereits") ;
            if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
            if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");

            if(person.getVorname().equalsIgnoreCase("Attila")) throw new PersonenServiceException("Attila will ich nicht");
        try {
            personenRepository.save(personMapper.convert(person));


        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public void aendern(final Person person) throws PersonenServiceException {

            if(person == null) throw new PersonenServiceException("Parameter darf nicht null sein");
            if(! personenRepository.existsById(person.getId())) throw new NotFoundException("Datensatz nicht gefunden");
            if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersonenServiceException("Vorname zu kurz");
            if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersonenServiceException("Nachname zu kurz");

            if(person.getVorname().equalsIgnoreCase("Attila")) throw new PersonenServiceException("Attila will ich nicht");
            try {
                 personenRepository.save(personMapper.convert(person));


        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersonenServiceException {
        try {
            return personenRepository.findById(id).map(personMapper::convert);
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Person> findeAlle() throws PersonenServiceException {
        try {
            return personMapper.convert(personenRepository.findAll());
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }

    @Override
    public void loesche(final UUID id) throws PersonenServiceException {

        if(! personenRepository.existsById(id)) throw new NotFoundException("Datensatz nicht gefunden");
        try {
            personenRepository.deleteById(id);

        }  catch (RuntimeException e) {
            throw new PersonenServiceException("Upps", e);
        }
    }




}
