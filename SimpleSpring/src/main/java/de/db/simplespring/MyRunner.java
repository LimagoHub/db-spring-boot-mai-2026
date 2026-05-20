package de.db.simplespring;

import de.db.simplespring.math.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    @Qualifier("logger")
    private final Calculator calculator;

    @Override
    public void run(final String... args) throws Exception {

        System.out.println(calculator);
        //System.out.println(calculator.add(4,3));
    }
}
