package application.controller;

import application.model.Model;
import application.view.*;
import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import signal_processing.ISignal;
import signal_processing.Signal;
import signal_processing.helpers.*;
import signal_processing.signals.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {
    private View view;
    private Model model;
    private SignalPanel[] signalPanels = new SignalPanel[2];
    private OperationsPanel operationsPanel;
    private DecimalFormat df;
    private JFileChooser fileChooser;
    private SamplingPanel samplingPanel;
    private QuantizationPanel quantizationPanel;
    private ReconstructionPanel reconstructionPanel;
    private FilterPanel filterPanel;
    private CorrelationPanel correlationPanel;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        signalPanels[0] = view.getSignalPanel1();
        signalPanels[1] = view.getSignalPanel2();
        operationsPanel = view.getOperationsPanel();
//        Set defaults
        setDefaults();
//        Actions
        assignActions();
//        Decimal format
        setDecimalFormat();
//        Set signal controls
        for (int i = 0; i < signalPanels.length; i++) {
            updateSignalControls(i);
        }
        initializeSamplingPanel();
        initializeQuantizationPanel();
        initializeReconstructionPanel();
        initializeFilterPanel();
        initializeCorrelationPanel();

        view.addTabbedPaneListener(e -> onTabbedPaneChange(e));
    }

    private void onTabbedPaneChange(ChangeEvent event) {
        JTabbedPane tabbedPane = (JTabbedPane) event.getSource();
        view.setDefaultPanelVisible(tabbedPane.getSelectedIndex() != 7);
        view.setCorrelationPanelVisible(tabbedPane.getSelectedIndex() == 7);

        if (tabbedPane.getSelectedIndex() == 7) {
            ISignal signal = new SinusoidalSignal();
            signal.setBasicPeriod(signal.getBasicPeriod() / 4);
            signal.updateValues();
//            view.renderSentSignal(signal);
        }
    }

    private void initializeSamplingPanel() {
        samplingPanel = new SamplingPanel();
        JTabbedPane tabbedPane = view.getTabbedPane();
        tabbedPane.addTab("Sampling", samplingPanel.getMainPanel());
        samplingPanel.addSamplingFrequencyListener(e -> onSamplingFrequencyChange(e));
        samplingPanel.addSamplingSignalListener(e -> onSamplingSignalChange(e));
        samplingPanel.addSetAsSignal1ButtonListener(e -> onSetSamplingSignalAsSignal(0));
        samplingPanel.addSetAsSignal2ButtonListener(e -> onSetSamplingSignalAsSignal(1));
        samplingPanel.addExportButtonListener(e -> onExportButtonInSampling());
        samplingPanel.addPreviewButtonListener(e -> onPreviewButtonInSampling());
    }

    private void initializeQuantizationPanel() {
        quantizationPanel = new QuantizationPanel();
        JTabbedPane tabbedPane = view.getTabbedPane();
        tabbedPane.addTab("Quantization", quantizationPanel.getMainPanel());
        quantizationPanel.addQuantizationLevelsListener(e -> onQuantizationLevelsChange(e));
        quantizationPanel.addQuantizationSignalListener(e -> onQuantizationSignalChange(e));
        quantizationPanel.addSetAsSignal1ButtonListener(e -> onSetQuantizationSignalAsSignal(0));
        quantizationPanel.addSetAsSignal2ButtonListener(e -> onSetQuantizationSignalAsSignal(1));
        quantizationPanel.addExportButtonListener(e -> onExportButtonInQuantization());
        quantizationPanel.addPreviewButtonListener(e -> onPreviewButtonInQuantization());
    }

    private void initializeReconstructionPanel() {
        reconstructionPanel = new ReconstructionPanel();
        JTabbedPane tabbedPane = view.getTabbedPane();
        tabbedPane.addTab("Reconstruction", reconstructionPanel.getMainPanel());
        reconstructionPanel.addReconstructionFrequencyListener(e -> onReconstructionFrequencyChange(e));
        reconstructionPanel.addReconstructionSignalListener(e -> onReconstructionSignalChange(e));
        reconstructionPanel.addSetAsSignal1ButtonListener(e -> onSetReconstructionSignalAsSignal(0));
        reconstructionPanel.addSetAsSignal2ButtonListener(e -> onSetReconstructionSignalAsSignal(1));
        reconstructionPanel.addExportButtonListener(e -> onExportButtonInReconstruction());
        reconstructionPanel.addPreviewButtonListener(e -> onPreviewButtonInReconstruction());
        reconstructionPanel.addRadioButtonListener(e -> onReconstructionTypeChange(e));
    }

    private void initializeFilterPanel() {
        filterPanel = new FilterPanel();
        JTabbedPane tabbedPane = view.getTabbedPane();
        tabbedPane.addTab("Filter", filterPanel.getMainPanel());
        filterPanel.addFilterSignalListener(e -> onFilterSignalChange(e));
        filterPanel.addFilterTypeListener(e -> onFilterTypeChange(e));
        filterPanel.addWindowTypeListener(e -> onWindowTypeChange(e));
        filterPanel.addCutoffFrequencyListener(e -> onCutoffFrequencyChange(e));
        filterPanel.addPreviewButtonListener(e -> onPreviewButtonInFilter());
        filterPanel.addSetAsSignal1ButtonListener(e -> onSetFilterSignalAsSignal(0));
        filterPanel.addSetAsSignal2ButtonListener(e -> onSetFilterSignalAsSignal(1));
        filterPanel.addExportButtonListener(e -> onExportButtonInFilter());
    }

    private void initializeCorrelationPanel() {
        correlationPanel = new CorrelationPanel();
        JTabbedPane tabbedPane = view.getTabbedPane();
        tabbedPane.addTab("Correlation", correlationPanel.getMainPanel());
        correlationPanel.addSpeedSliderListener(e -> onSpeedSliderChange(e));
        correlationPanel.addStartButtonListener(e -> onCorrelationStart());
//        correlationPanel.addStopButtonListener(e -> onCorrelationStop());
    }

    private void onSamplingFrequencyChange(ChangeEvent event) {
        JSpinner source = (JSpinner) event.getSource();
        model.setSamplingFrequency((double)source.getValue());
    }

    private void onSamplingSignalChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setSamplingSignal(source.getSelectedIndex());
        samplingPanel.updateButtons(source.getSelectedIndex());
    }

    private void onSetSamplingSignalAsSignal(int index) {
        try {
            sampleSignal();
            ISignal signal = model.getSampledSignal();
            model.setSignal(index, signal);
            onSignalRender(index);
            onPreviewButtonInSampling();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onExportButtonInSampling() {
        try {
            sampleSignal();
            ISignal signal = model.getSampledSignal();

            fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(view.getMainPanel());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFile = fileChooser.getSelectedFile().getPath();
                try {
                    FileUtils.saveSignal(signal, selectedFile);
                    JOptionPane.showMessageDialog(view.getFrame(), "Saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    view.displayError("Could not save file: " + selectedFile);
                }
            }

        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onPreviewButtonInSampling() {
        try {
            sampleSignal();
            ISignal signal = model.getSignal(model.getSamplingSignal());
            JFreeChart chart = Operations.getChart(signal, model.getSampledSignal());
            samplingPanel.displaySignal(chart);
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void sampleSignal() throws Exception {
            int index = model.getSamplingSignal();
            ISignal signal = model.getSignal(index);
            if (signal == null || signal.getValuesX().size() == 0 || signal.getValuesY().size() == 0) {
                throw new Exception("Signal not found.");
            }
            ISignal sampled = Operations.sampling(signal, model.getSamplingFrequency());
            model.setSampledSignal(sampled);
            samplingPanel.hideNoSignal();
    }

    private void onQuantizationLevelsChange(ChangeEvent event) {
        JSpinner source = (JSpinner) event.getSource();
        model.setQuantizationLevels((int)source.getValue());
    }

    private void onQuantizationSignalChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setQuantizationSignal(source.getSelectedIndex());
        quantizationPanel.updateButtons(source.getSelectedIndex());
    }

    private void onSetQuantizationSignalAsSignal(int index) {
        try {
            quantizeSignal();
            ISignal signal = model.getQuantizedSignal();
            model.setSignal(index, signal);
            onSignalRender(index);
            onPreviewButtonInSampling();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void quantizeSignal() throws Exception {
        int index = model.getQuantizationSignal();
        ISignal signal = model.getSignal(index);
        if (signal == null || signal.getValuesX().size() == 0 || signal.getValuesY().size() == 0) {
            throw new Exception("Signal not found.");
        }
        ISignal sampled = Operations.quantization(signal, model.getQuantizationLevels());
        model.setQuantizedSignal(sampled);
        quantizationPanel.hideNoSignal();
    }

    private void onPreviewButtonInQuantization() {
        try {
            quantizeSignal();
            ISignal signal = model.getSignal(model.getQuantizationSignal());
            JFreeChart chart = Operations.getChart(signal, model.getQuantizedSignal());
            quantizationPanel.displaySignal(chart);
            quantizationPanel.hideNoSignal();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onExportButtonInQuantization() {
        try {
            quantizeSignal();
            ISignal signal = model.getQuantizedSignal();

            fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(view.getMainPanel());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFile = fileChooser.getSelectedFile().getPath();
                try {
                    FileUtils.saveSignal(signal, selectedFile);
                    JOptionPane.showMessageDialog(view.getFrame(), "Saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    view.displayError("Could not save file: " + selectedFile);
                }
            }

        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onReconstructionFrequencyChange(ChangeEvent event) {
        JSpinner source = (JSpinner) event.getSource();
        model.setReconstructionFrequency((double)source.getValue());
    }

    private void onReconstructionSignalChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setReconstructionSignal(source.getSelectedIndex());
        reconstructionPanel.updateButtons(source.getSelectedIndex());
    }

    private void onReconstructionTypeChange(ActionEvent event) {
        JRadioButton source = (JRadioButton) event.getSource();
        String name = source.getActionCommand();
        switch (name) {
            default:
                model.setReconstructionType(0);
                break;
            case "Interpolation":
                model.setReconstructionType(1);
                break;
            case "Sinc":
                model.setReconstructionType(2);
                break;
        }
    }

    private void reconstructSignal() throws Exception {
        int index = model.getReconstructionSignal();
        ISignal signal = model.getSignal(index);
        if (signal == null || signal.getValuesX().size() == 0 || signal.getValuesY().size() == 0) {
            throw new Exception("Signal not found.");
        }
        ISignal reconstructed;
        double reconstructionFrequency = model.getReconstructionFrequency();
        switch (model.getReconstructionType()) {
//            case 1:
//              ...
//                break;
            case 2:
                reconstructed = Operations.reconstruction(signal, reconstructionFrequency);
                break;
            default:
                reconstructed = Operations.zeroExploration(signal, reconstructionFrequency);
                break;
        }
        model.setReconstructedSignal(reconstructed);
        model.setOriginalReconstructionSignal(signal.copy());
        reconstructionPanel.hideNoSignal();
        updateReconstructionStats();
    }

    private void onSetReconstructionSignalAsSignal(int index) {
        try {
            reconstructSignal();
            ISignal signal = model.getReconstructedSignal();
            model.setSignal(index, signal);
            onSignalRender(index);
            onPreviewButtonInReconstruction();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onPreviewButtonInReconstruction() {
        try {
            reconstructSignal();
            ISignal signal = model.getSignal(model.getReconstructionSignal());
            JFreeChart chart = Operations.getChart(signal, model.getReconstructedSignal());
            reconstructionPanel.displaySignal(chart);
            reconstructionPanel.hideNoSignal();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onExportButtonInReconstruction() {
        try {
            reconstructSignal();
            ISignal signal = model.getReconstructedSignal();

            fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(view.getMainPanel());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFile = fileChooser.getSelectedFile().getPath();
                try {
                    FileUtils.saveSignal(signal, selectedFile);
                    JOptionPane.showMessageDialog(view.getFrame(), "Saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    view.displayError("Could not save file: " + selectedFile);
                }
            }

        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onSetFilterSignalAsSignal(int index) {
        try {
            filterSignal();
            ISignal signal = model.getFilteredSignal();
            model.setSignal(index, signal);
            onSignalRender(index);
            onPreviewButtonInFilter();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onFilterSignalChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setFilterSignal(source.getSelectedIndex());
        filterPanel.updateButtons(model.getFilterSignal());
    }

    private void onFilterTypeChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setFilterType(source.getSelectedIndex());
    }

    private void onWindowTypeChange(ActionEvent event) {
        JComboBox source = (JComboBox) event.getSource();
        model.setWindowType(source.getSelectedIndex());
    }

    private void onCutoffFrequencyChange(ChangeEvent event) {
        JSpinner source = (JSpinner) event.getSource();
        model.setCutoffFrequency((double) source.getValue());
    }

    private void filterSignal() throws Exception {
        int index = model.getFilterSignal();
        ISignal signal = model.getSignal(index);
        if (signal == null || signal.getValuesX().size() == 0 || signal.getValuesY().size() == 0) {
            throw new Exception("Signal not found.");
        }
        ISignal filtered;
        double cutoffFrequency = model.getCutoffFrequency();

        int filterType = model.getFilterType();
        int windowType = model.getWindowType();
        int m = 15;

        if (filterType == 0 && windowType == 0) {
            filtered = Filter.filterSignal(signal, 0, m, cutoffFrequency);
        } else if (filterType == 1 && windowType == 0) {
            filtered = Filter.filterSignal(signal, 1, m, cutoffFrequency);
        } else if (filterType == 0 && windowType == 1) {
            filtered = Filter.filterSignal(signal, 2, m, cutoffFrequency);
        } else {
            filtered = Filter.filterSignal(signal, 3, m, cutoffFrequency);
        }

        model.setFilteredSignal(filtered);
        model.setOriginalFilteredSignal(signal.copy());
        filterPanel.hideNoSignal();
    }

    private void onPreviewButtonInFilter() {
        try {
            filterSignal();
            ISignal signal = model.getSignal(model.getFilterSignal());
            JFreeChart chart = Operations.getChart(signal, model.getFilteredSignal());
            filterPanel.displaySignal(chart);
            filterPanel.hideNoSignal();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void onExportButtonInFilter() {
        try {
            filterSignal();
            ISignal signal = model.getFilteredSignal();

            fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(view.getMainPanel());
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFile = fileChooser.getSelectedFile().getPath();
                try {
                    FileUtils.saveSignal(signal, selectedFile);
                    JOptionPane.showMessageDialog(view.getFrame(), "Saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    view.displayError("Could not save file: " + selectedFile);
                }
            }

        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    private void updateReconstructionStats() {
        Statistics statistics = new Statistics(model.getOriginalReconstructionSignal(), model.getReconstructedSignal());
        Object[][] stats = {
                { "Mean square error", df.format(statistics.MSE()) },
                { "Signal noise ratio", df.format(statistics.SNR()) },
                { "Peak signal to noise ratio", df.format(statistics.PSNR()) },
                { "Maximum difference", df.format(statistics.MD()) },
                { "Effective number of bits", df.format(statistics.ENOB()) }
        };
        reconstructionPanel.updateStats(stats);
    }

    private void onSpeedSliderChange(ChangeEvent event) {
        JSlider source = (JSlider) event.getSource();
        model.setSpeed(source.getValue());
    }

    private void onCorrelationStart() {
        try {
            Correlation correlation = model.getCorrelation();

            ISignal signal1 = model.getSignal(0);
            ISignal signal2 = model.getSignal(1);
            if (signal1 == null || signal2 == null) {
                throw new Exception("Both signals must be generated.");
            }

            Position position = model.getPosition();
            if (position == null) {
                position = new Position(0.05);
                model.setPosition(position);
            }

            correlation = new Correlation(100, 0.5, 0.5, 0.5, 0, signal1, signal2, position);
            model.setCorrelation(correlation);
            correlation.distanceSensor();

            GeneratedSignal correlated = correlation.getCorrelatedSignal();
            correlated.setName("Signals correlation");

            view.renderSentSignal(signal1);
            view.renderReceivedSignal(signal2);
            view.renderCorrelatedSignal(correlated);


//            Thread thread = new Thread(new CorrelationThread());
//            thread.start();

//            model.setCorrelationWorking(true);
//            updateCorrelationButtons();

        } catch (Exception e) {
            e.printStackTrace();
            view.displayError(e.getMessage());
        }
    }

//    private void onCorrelationStop() {
//        model.setCorrelationWorking(false);
//        updateCorrelationButtons();
//    }

//    private void updateCorrelationButtons() {
//        boolean isWorking = model.isCorrelationWorking();
//        correlationPanel.updateButtons(isWorking);
//    }

    public class CorrelationThread implements Runnable {
        @Override
        public void run() {
            Position position = model.getPosition();
            Correlation correlation = model.getCorrelation();
            double time = 0.0;

            while (model.isCorrelationWorking()) {
                try {
                    Thread.sleep(1000);

                    System.out.println(correlation.getDistance());
                    position.setPosition(time++);

                    correlation.distanceSensor();
                    ISignal correlated = correlation.getCorrelatedSignal();
                    view.renderCorrelatedSignal(correlated);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setDecimalFormat() {
        df = new DecimalFormat("0.00000");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(symbols);
    }

    private void assignActions() {
        for (int i = 0; i < signalPanels.length; i++) {
            final int x = i;
            signalPanels[i].getSignalType().addActionListener(e -> onSignalChange(x));
            signalPanels[i].getFirstSample().addChangeListener(e -> updateFirstSample(x));
            signalPanels[i].getLastSample().addChangeListener(e -> updateLastSample(x));
            signalPanels[i].getAmplitude().addChangeListener(e -> updateAmplitude(x));
            signalPanels[i].getStartTime().addChangeListener(e -> updateStartTime(x));
            signalPanels[i].getEndTime().addChangeListener(e -> updateEndTime(x));
            signalPanels[i].getFrequency().addChangeListener(e -> updateFrequency(x));
            signalPanels[i].getBasicPeriod().addChangeListener(e -> updateBasicPeriod(x));
            signalPanels[i].getFillingFactor().addChangeListener(e -> updateFillingFactor(x));
            signalPanels[i].getProbability().addChangeListener(e -> updateProbability(x));
            signalPanels[i].getJumpPoint().addChangeListener(e -> updateJumpPoint(x));
            signalPanels[i].getSampleJump().addChangeListener(e -> updateSampleJump(x));
            signalPanels[i].getRenderButton().addActionListener(e -> onSignalRender(x));
            signalPanels[i].getHistogramBins().addChangeListener(e -> onHistogramChange(x));
        }
        operationsPanel.getPreviewButton().addActionListener(e -> onPreview());
        operationsPanel.getExportButton().addActionListener(e -> onExport());
        operationsPanel.getSetAsSignal1Button().addActionListener(e -> onSetAsSignal(0));
        operationsPanel.getSetAsSignal2Button().addActionListener(e -> onSetAsSignal(1));

        view.getFile_item_1().addActionListener(e -> onImport(0));
        view.getFile_item_2().addActionListener(e -> onImport(1));
    }

    private void onSignalChange(int index) {
        int selectedSignal = signalPanels[index].getSignalType().getSelectedIndex();

        if (selectedSignal != 11) {
            setSignal(index, selectedSignal);
            updateSignalControls(index);
        }
    }

    public void onSetAsSignal(int index) {
        generateSignal();
        model.setSignal(index, model.getGeneratedSignal());
        onSignalRender(index);
    }

    private void updateSignalControls(int index) {
        SignalPanel panel = signalPanels[index];
        ISignal signal = model.getSignal(index);
        for (String parameter : Signal.getAllParameters()) {
            try {
                Method method = panel.getClass().getMethod("get" + StringUtils.capitalize(parameter));
                JComponent component = (JComponent) method.invoke(panel, null);
                boolean exists = Arrays.stream(signal.getAvailableParameters()).anyMatch(parameter::equals);
//                component.setEnabled(exists);
                component.getParent().getParent().setVisible(exists);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        signalPanels[index].getFirstSample().setValue(signal.getFirstSample());
        signalPanels[index].getLastSample().setValue(signal.getLastSample());
        signalPanels[index].getAmplitude().setValue(signal.getAmplitude());
        signalPanels[index].getStartTime().setValue(signal.getStartTime());
        signalPanels[index].getEndTime().setValue(signal.getEndTime());
        signalPanels[index].getFrequency().setValue(signal.getFrequency());
        signalPanels[index].getBasicPeriod().setValue(signal.getBasicPeriod());
        signalPanels[index].getFillingFactor().setValue(signal.getFillingFactor());

        if (signal.getClass().getName().equals("signal_processing.signals.ImpulseNoise")) {
            ImpulseNoise tmp = (ImpulseNoise) signal;
            signalPanels[index].getProbability().setValue((int) (tmp.getProbability() * 100));
        }
        if (signal.getClass().getName().equals("signal_processing.signals.IndividualJumpSignal")) {
            IndividualJumpSignal tmp = (IndividualJumpSignal) signal;
            signalPanels[index].getJumpPoint().setValue(tmp.getJumpPoint());
        }
        if (signal.getClass().getName().equals("signal_processing.signals.IndividualImpulseSignal")) {
            IndividualImpulseSignal tmp = (IndividualImpulseSignal) signal;
            signalPanels[index].getSampleJump().setValue(tmp.getSampleJump());
        }
    }

    private void onSignalRender(int index) {
        ISignal signal = model.getSignal(index);
        signal.updateValues();

        Statistics stats = model.getStats(index);
        renderSignal(index);
        renderHistogram(index);

        signal.setRendered(true);

        if (model.isBothSignalsRendered()) {
            view.enableOperationsButtons();
        }

        signalPanels[index].getInfoAverage().setText(df.format(stats.getAverage()));
        signalPanels[index].getInfoAbsoluteAverage().setText(df.format(stats.getAbsoluteMean()));
        signalPanels[index].getInfoAveragePower().setText(df.format(stats.getAveragePower()));
        signalPanels[index].getInfoVariance().setText(df.format(stats.getVariance()));
        signalPanels[index].getInfoRootMeanSquare().setText(df.format(stats.getEffectiveValue()));
    }

    private void onHistogramChange(int index) {
        renderHistogram(index);
    }

    private void onPreview() {
        generateSignal();
        ISignal generatedSignal = model.getGeneratedSignal();

        final XYSeries series = new XYSeries("data");

        List<Double> x = generatedSignal.getValuesX();
        List<Double> y = generatedSignal.getValuesY();
        for (int i = 0; i < x.size(); i++) {
            series.add(x.get(i), y.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        view.renderGeneratedSignal(dataset);

        Statistics stats = model.getGeneratedStats();

        operationsPanel.getInfoAverage().setText(df.format(stats.getAverage()));
        operationsPanel.getInfoAbsoluteAverage().setText(df.format(stats.getAbsoluteMean()));
        operationsPanel.getInfoAveragePower().setText(df.format(stats.getAveragePower()));
        operationsPanel.getInfoVariance().setText(df.format(stats.getVariance()));
        operationsPanel.getInfoRootMeanSquare().setText(df.format(stats.getEffectiveValue()));
    }

    public void onExport() {
        fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(view.getMainPanel());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getPath();
            try {
                generateSignal();
                FileUtils.saveSignal(model.getGeneratedSignal(), selectedFile);
                JOptionPane.showMessageDialog(view.getFrame(), "Saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                String message = "Could not save file: " + selectedFile;
                JOptionPane.showMessageDialog(view.getFrame(), message, "Saving error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void onImport(int index) {
        fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(view.getMainPanel());
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getPath();
            try {
                ISignal signal = FileUtils.loadSignal(selectedFile);
                model.setSignal(index, signal);
                renderSignal(index);
                renderHistogram(index);
                SignalPanel signalPanel = signalPanels[index];
                signalPanel.getSignalType().setSelectedIndex(11);
                updateSignalControls(index);

                signal.setRendered(true);
                if (model.isBothSignalsRendered()) {
                    view.enableOperationsButtons();
                }

            } catch (IOException ex) {
                String message = "Could not import file: " + selectedFile;
                JOptionPane.showMessageDialog(view.getFrame(), message, "Loading error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setSignal(int index, int type) {
        model.setSignal(index, type);
    }

    private void generateSignal() {
        model.generateSignal(view.getOperation(), view.getOrder());
    }

    private void renderSignal(int index) {
        ISignal signal = model.getSignal(index);
        JPanel panel = (index == 0 ? view.getSignalChart1() : view.getSignalChart2());
        final XYSeries series = new XYSeries("data");
        List<Double> x = signal.getValuesX();
        List<Double> y = signal.getValuesY();
        for (int i = 0; i < x.size(); i++) {
            series.add(x.get(i), y.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        view.renderSignal(index, signal, dataset);
        signalPanels[index].getHistogramBins().setEnabled(true);
    }

    private void renderHistogram(int index) {
        ISignal signal = model.getSignal(index);
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.RELATIVE_FREQUENCY);
        double[] values = new double[signal.getValuesY().size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = signal.getValuesY().get(i);
        }
        int bins = signalPanels[index].getHistogramBins().getValue();
        dataset.addSeries("H1", values, bins, Collections.min(signal.getValuesY()), Collections.max(signal.getValuesY()));
        view.renderHistogram(index, dataset);
    }

    private void setDefaults() {
        for (int i = 0; i < signalPanels.length; i++) {
            setSignal(i, signalPanels[i].getSignalType().getSelectedIndex());
            updateFirstSample(i);
            updateLastSample(i);
            updateAmplitude(i);
            updateStartTime(i);
            updateEndTime(i);
            updateFrequency(i);
            updateBasicPeriod(i);
            updateFillingFactor(i);
            updateProbability(i);
            updateJumpPoint(i);
            updateSampleJump(i);
        }
    }

    private void updateFirstSample(int index) {
        model.getSignal(index).setFirstSample((int) signalPanels[index].getFirstSample().getValue());
    }
    private void updateLastSample(int index) {
        model.getSignal(index).setLastSample((int) signalPanels[index].getLastSample().getValue());
    }
    private void updateAmplitude(int index) {
        model.getSignal(index).setAmplitude((double) signalPanels[index].getAmplitude().getValue());
    }
    private void updateStartTime(int index) {
        model.getSignal(index).setStartTime((double) signalPanels[index].getStartTime().getValue());
    }
    private void updateEndTime(int index) {
        model.getSignal(index).setEndTime((double) signalPanels[index].getEndTime().getValue());
    }
    private void updateFrequency(int index) {
        model.getSignal(index).setFrequency((double) signalPanels[index].getFrequency().getValue());
    }
    private void updateBasicPeriod(int index) {
        model.getSignal(index).setBasicPeriod((double) signalPanels[index].getBasicPeriod().getValue());
    }
    private void updateFillingFactor(int index) {
        model.getSignal(index).setFillingFactor((double) signalPanels[index].getFillingFactor().getValue());
    }
    private void updateProbability(int index) {
        if (model.getSignal(index).getClass().getName().equals("signal_processing.signals.ImpulseNoise")) {
            ImpulseNoise signal = (ImpulseNoise) model.getSignal(index);
            signal.setProbability((double) (signalPanels[index].getProbability().getValue() / 100d));
        }
    }
    private void updateJumpPoint(int index) {
        if (model.getSignal(index).getClass().getName().equals("signal_processing.signals.IndividualJumpSignal")) {
            IndividualJumpSignal signal = (IndividualJumpSignal) model.getSignal(index);
            signal.setJumpPoint((double) signalPanels[index].getJumpPoint().getValue());
        }
    }
    private void updateSampleJump(int index) {
        if (model.getSignal(index).getClass().getName().equals("signal_processing.signals.IndividualImpulseSignal")) {
            IndividualImpulseSignal signal = (IndividualImpulseSignal) model.getSignal(index);
            signal.setSampleJump((double) signalPanels[index].getSampleJump().getValue());
        }
    }

}
