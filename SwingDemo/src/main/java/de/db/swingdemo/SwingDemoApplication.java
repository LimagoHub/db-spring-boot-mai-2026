package de.db.swingdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwingDemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SwingDemoApplication.class);
        app.setHeadless(false);
        app.run(args);
    }

}
