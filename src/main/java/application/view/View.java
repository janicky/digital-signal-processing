package application.view;

import javax.swing.*;

public class View {
    private JFrame frame;
    private JPanel mainPanel;

    public View(String title) {
        frame = new JFrame(title);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
