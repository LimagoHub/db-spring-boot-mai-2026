package de.db.webapp.domain;


import de.db.webapp.domain.internal.PersonenServiceImpl;
import de.db.webapp.domain.mapper.PersonMapper;
import de.db.webapp.domain.model.Person;
import de.db.webapp.persistence.repository.PersonenRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    @Qualifier("antipathen")
    public List<String> getAntipathen() {
        return List.of("Attila","Peter","Paul", "Mary");
    }
    @Bean
    @Qualifier("fruits")
    public List<String> getFruits() {
        return List.of("Banana","Apple","Cherry", "Strawberry");
    }

    @Bean
    public PersonenService createPersonenService(
            final PersonenRepository repository,
            final PersonMapper mapper,
            @Qualifier("antipathen") final List<String> antipathen) {
        return new PersonenServiceImpl(repository, mapper, antipathen);
    }

}
