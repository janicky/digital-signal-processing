package application.view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class SamplingPanel {
    private JPanel mainPanel;
    private JButton setAsSignal1Button;
    private JButton setAsSignal2Button;
    private JButton exportButton;
    private JButton previewButton;
    private JPanel signalPanel;
    private JLabel noSignal;
    private JLabel signalName;
    private JComboBox samplingSignal;
    private JSpinner samplingFrequency;

    public SamplingPanel() {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        comboBoxModel.addElement("Signal 1");
        comboBoxModel.addElement("Signal 2");
        samplingSignal.setModel(comboBoxModel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void addSamplingFrequencyListener(ChangeListener listener) {
        samplingFrequency.addChangeListener(listener);
    }

    public void addSamplingSignalListener(ActionListener listener) {
        samplingSignal.addActionListener(listener);
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
}
