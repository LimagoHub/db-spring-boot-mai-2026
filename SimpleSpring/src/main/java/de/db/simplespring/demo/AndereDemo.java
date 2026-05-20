package de.db.simplespring.demo;


import de.db.simplespring.translator.Translator;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AndereDemo {

    @Inject
    @Qualifier("upper")
    private Translator translator;

    public AndereDemo() {
        System.out.println("AndereDemo");
    }
}
