package de.db.swingdemo;


import de.db.swingdemo.gui.Fenster;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.SwingUtilities;

@Component
public class MyRunner implements CommandLineRunner {
    @Override
    public void run(final String... args) throws Exception {
        SwingUtilities.invokeLater(Fenster::new);
    }
}
