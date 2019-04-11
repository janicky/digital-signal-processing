package application.view;

import signal_processing.Signal;

import javax.swing.*;

public class OperationsPanel {
    private JComboBox operationMode;
    private JButton setAsSignal1Button;
    private JButton exportButton;
    private JPanel operationsPanel;
    private JLabel noGeneratedSignal;
    private JPanel generatedSignal;
    private JLabel signalName;
    private JLabel infoAverage;
    private JLabel infoAbsoluteAverage;
    private JLabel infoAveragePower;
    private JLabel infoVariance;
    private JLabel infoRootMeanSquare;
    private JButton operationsSignalA;
    private JButton operationsSignalB;
    private JButton reverseButton;
    private String[] signals = new String[] { "Signal 1", "Signal 2" };

    public JPanel getOperationsPanel() {
        return operationsPanel;
    }

    public OperationsPanel() {
        initializeView();
        assignActions();
    }

    private void initializeView() {
        String[] operations = new String[] { "+", "-", "*", "/" };
        operationMode.setModel(new DefaultComboBoxModel(operations));
    }

    private void assignActions() {
        reverseButton.addActionListener(e -> reverseSignals());
        operationsSignalA.addActionListener(e -> reverseSignals());
        operationsSignalB.addActionListener(e -> reverseSignals());
    }

    private void reverseSignals() {
        if (operationsSignalA.getText().equals(signals[0])) {
            operationsSignalA.setText(signals[1]);
        } else {
            operationsSignalA.setText(signals[0]);
        }
        if (operationsSignalB.getText().equals(signals[0])) {
            operationsSignalB.setText(signals[1]);
        } else {
            operationsSignalB.setText(signals[0]);
        }
    }


}
