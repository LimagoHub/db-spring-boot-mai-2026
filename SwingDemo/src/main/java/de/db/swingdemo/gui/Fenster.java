package de.db.swingdemo.gui;

import javax.swing.*;
import java.awt.*;


public class Fenster extends JFrame {

    public Fenster() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new ZeichenPanel());
        setVisible(true);
    }

    private static class ZeichenPanel extends JPanel {
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.drawString("Hello World", 50, 50);
        }
    }
}
