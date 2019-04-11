package application.view;

import javax.swing.*;

public class OperationsPanel {
    private JComboBox operationMode;
    private JComboBox operationSignal1;
    private JComboBox operationSignal2;
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

    public JPanel getOperationsPanel() {
        return operationsPanel;
    }
}
