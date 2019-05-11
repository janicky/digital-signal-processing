package application.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class ReconstructionPanel {
    private JPanel mainPanel;
    private JComboBox reconstructionSignal;
    private JSpinner reconstrunctionFrequency;
    private JButton setAsSignal1Button;
    private JButton setAsSignal2Button;
    private JButton exportButton;
    private JButton previewButton;
    private JPanel signalPanel;
    private JLabel noSignal;
    private JRadioButton extrapolationRadioButton;
    private JRadioButton interpolationRadioButton;
    private JRadioButton sincRadioButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addReconstructionFrequencyListener(ChangeListener listener) {
        reconstrunctionFrequency.addChangeListener(listener);
    }

    public void addReconstructionSignalListener(ActionListener listener) {
        reconstructionSignal.addActionListener(listener);
    }

    public void addSetAsSignal1ButtonListener(ActionListener listener) {
        setAsSignal1Button.addActionListener(listener);
    }

    public void addSetAsSignal2ButtonListener(ActionListener listener) {
        setAsSignal2Button.addActionListener(listener);
    }

    public void addExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    public void addPreviewButtonListener(ActionListener listener) {
        previewButton.addActionListener(listener);
    }

    public void addRadioButtonListener(ActionListener listener) {
        extrapolationRadioButton.addActionListener(listener);
        interpolationRadioButton.addActionListener(listener);
        sincRadioButton.addActionListener(listener);
    }
}
