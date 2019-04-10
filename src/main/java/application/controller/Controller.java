package application.controller;

import application.model.Model;
import application.view.SignalPanel;
import application.view.View;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import signal_processing.ISignal;
import signal_processing.signals.ImpulseNoise;
import signal_processing.signals.IndividualImpulseSignal;
import signal_processing.signals.IndividualJumpSignal;
import signal_processing.signals.SteadyNoise;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Controller {
    private View view;
    private Model model;
    private SignalPanel[] signalPanels = new SignalPanel[2];

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        signalPanels[0] = view.getSignalPanel1();
        signalPanels[1] = view.getSignalPanel2();
//        Set defaults
        setDefaults();
//        Actions
        assignActions();
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
        }
    }

    private void onSignalChange(int index) {
        setSignal(index, signalPanels[index].getSignalType().getSelectedIndex());
    }

    private void onSignalRender(int index) {
        model.getSignal(index).updateValues();
        renderSignal(index);
    }

    private void setSignal(int index, int type) {
        model.setSignal(index, type, signalPanels[index]);
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
        JFreeChart chart = ChartFactory.createXYLineChart("Signal " + index, "x", "y", dataset, PlotOrientation.VERTICAL, false, false, false);
        panel.add(new ChartPanel(chart), BorderLayout.CENTER);
        panel.validate();
        view.getMainPanel().validate();

        if (index == 0) {
            view.hideNoSignal1();
        } else {
            view.hideNoSignal2();
        }
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
        if (model.getSignal(index).getClass().getName().equals("ImpulseNoise")) {
            ImpulseNoise signal = (ImpulseNoise) model.getSignal(index);
            signal.setProbability((double) signalPanels[index].getProbability().getValue());
        }
    }
    private void updateJumpPoint(int index) {
        if (model.getSignal(index).getClass().getName().equals("IndividualJumpSignal")) {
            IndividualJumpSignal signal = (IndividualJumpSignal) model.getSignal(index);
            signal.setJumpPoint((double) signalPanels[index].getJumpPoint().getValue());
        }
    }
    private void updateSampleJump(int index) {
        if (model.getSignal(index).getClass().getName().equals("IndividualImpulseSignal")) {
            IndividualImpulseSignal signal = (IndividualImpulseSignal) model.getSignal(index);
            signal.setSampleJump((double) signalPanels[index].getSampleJump().getValue());
        }
    }

}
