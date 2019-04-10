package application.view;

import signal_processing.Signal;

import javax.swing.*;

public class SignalPanel extends JPanel {
    private JLabel signalName;
    public JPanel signalPanel;
    private JComboBox signalType;
    private JSpinner firstSample;
    private JSpinner lastSample;
    private JSpinner startTime;
    private JSpinner durationTime;
    private JSpinner frequency;
    private JSpinner amplitude;
    private SpinnerNumberModel lastSampleModel;

    public SignalPanel() {
        initializeComboBox();
    }

    private void initializeComboBox() {
        firstSample.addChangeListener(e -> onFirstSampleChange());

//        Set models
        setInputModels();
    }

    private void setInputModels() {
//        Signal type
        signalType.setModel(new DefaultComboBoxModel(Signal.getSignals()));
//        First sample & last sample
        firstSample.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        lastSampleModel = new SpinnerNumberModel(2, 2, Integer.MAX_VALUE, 1);
        lastSample.setModel(lastSampleModel);
//        Start & duration time
        startTime.setModel(new SpinnerNumberModel(1.0, 0.0, 999999.0, 0.1));
        durationTime.setModel(new SpinnerNumberModel(1.0, 0.1, 999999.0, 0.1));
//        Frequency & amplitude
        frequency.setModel(new SpinnerNumberModel(1.0, 0.0, 999999.0, 0.1));
        amplitude.setModel(new SpinnerNumberModel(1.0, 0.1, 999999.0, 0.1));
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
