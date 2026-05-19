package de.db.webapp;


import de.db.webapp.persistence.entity.PersonEntity;
import de.db.webapp.persistence.repository.PersonenRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Demo {

    private final PersonenRepository personenRepository;

    @PostConstruct
    public void post() {

        personenRepository.findAllProjectBy().forEach(System.out::println);
    }
}
