package application.view;

import signal_processing.Signal;

import javax.swing.*;

public class SignalPanel extends JPanel {
    private JLabel signalName;
    public JPanel signalPanel;
    private JComboBox signalType;
    private JSpinner firstSample;
    private JSpinner lastSample;
    private SpinnerNumberModel lastSampleModel;

    public SignalPanel() {
        initializeComboBox();
    }

    private void initializeComboBox() {
        signalType.setModel(new DefaultComboBoxModel(Signal.getSignals()));
        firstSample.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        firstSample.addChangeListener(e -> onFirstSampleChange());
        lastSampleModel = new SpinnerNumberModel(2, 2, Integer.MAX_VALUE, 1);
        lastSample.setModel(lastSampleModel);
    }

//    Actions
    private void onFirstSampleChange() {
        int lastSampleValue = (int) lastSample.getValue();
        int firstSampleValue = (int) firstSample.getValue();
        lastSampleModel.setMinimum(firstSampleValue + 1);
        if (lastSampleValue < firstSampleValue + 1) {
            lastSample.setValue(firstSampleValue + 1);
        }
    }
}
