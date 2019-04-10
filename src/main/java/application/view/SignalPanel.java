package application.view;

import signal_processing.Signal;

import javax.swing.*;

public class SignalPanel extends JPanel {
    private JLabel signalName;
    public JPanel signalPanel;
    private JComboBox comboBox;

    public SignalPanel() {
        initializeComboBox();
    }

    private void initializeComboBox() {
        comboBox.setModel(new DefaultComboBoxModel(Signal.getSignals()));
    }
}
