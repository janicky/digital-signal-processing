package application.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel signalChart1;
    private JPanel histogramChart1;
    private SignalPanel signalPanel1;
    private SignalPanel signalPanel2;
    private JTabbedPane tabbedPane;
    private JLabel noHistogram2;
    private JPanel signalChart2;
    private JLabel noSignal1;
    private JLabel noSignal2;
    private JLabel noHistogram1;
//    private JPanel histogramChart2;

    public View(String title) {
        frame = new JFrame(title);
        createMenu();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        initializeView();
    }

    private void initializeView() {
        signalPanel1 = new SignalPanel();
        signalPanel2 = new SignalPanel();
        tabbedPane.add("Signal 1", signalPanel1.signalPanel);
        tabbedPane.add("Signal 2", signalPanel2.signalPanel);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

//        File
        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem file_item_1 = new JMenuItem("Load file");

        file.add(file_item_1);
        frame.setJMenuBar(menuBar);
    }

    private void setIcon(JButton button, String path) {
        try {
            Image img = ImageIO.read(getClass().getResource(path));
            img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(img);
            button.setIcon(imgIcon);
        } catch (Exception ex) {
//          TODO: Handle icon not found error
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SignalPanel getSignalPanel1() {
        return signalPanel1;
    }

    public SignalPanel getSignalPanel2() {
        return signalPanel2;
    }

    public JPanel getSignalChart1() {
        return signalChart1;
    }

    public JPanel getHistogramChart1() {
        return histogramChart1;
    }

//    public JPanel getHistogramChart2() {
//        return histogramChart2;
//    }

    public JPanel getSignalChart2() {
        return signalChart2;
    }

    public void hideNoSignal1() {
        noSignal1.setVisible(false);
    }
    public void hideNoSignal2() {
        noSignal2.setVisible(false);
    }
}
