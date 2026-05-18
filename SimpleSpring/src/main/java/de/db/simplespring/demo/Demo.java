package de.db.simplespring.demo;

import de.db.simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")// Default
//@Scope("prototype")
//@Lazy
public class Demo {

    // 1. Field Injection Autowired ueber das feld
    // 2. Setterinjection   Autowired über dem setter
    // 3.Construtor Injecteion Autowired nicht nötig (von Spring empfohlen)


    private final Translator translator;


    public Demo( final Translator translator) {
        this.translator = translator;
        System.out.println(translator.translate("Demo Constructor"));
    }

    /*public Demo() {
        System.out.println(translator.translate("Demo Constructor"));
    }*/

    @PostConstruct
    public void init() {
        System.out.println(translator.translate("Hello World"));
    }
}
