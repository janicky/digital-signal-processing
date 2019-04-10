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
import signal_processing.signals.SteadyNoise;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Controller {
    private View view;
    private Model model;
    private ISignal signal1;
    private ISignal signal2;
    private SignalPanel signalPanell;
    private SignalPanel signalPanel2;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        signalPanell = view.getSignalPanel1();
        signalPanel2 = view.getSignalPanel2();
//        Set defaults
        setDefaults();
//        Actions
        assignActions();
    }

    private void setDefaults() {
        setSignal(1, signalPanell.getSignalType().getSelectedIndex());
        setSignal(2, signalPanel2.getSignalType().getSelectedIndex());
    }

    private void assignActions() {
        signalPanell.getSignalType().addActionListener(e -> onSignal1Change());
        signalPanell.getRenderButton().addActionListener(e -> onSignal1Render());
    }

    private void onSignal1Change() {
        setSignal(1, signalPanell.getSignalType().getSelectedIndex());
    }

    private void onSignal1Render() {
        renderSignal(1);
    }

    private void setSignal(int number, int type) {
        ISignal signal;
        switch (type) {
            case 0:
                signal = new SteadyNoise(1, 100, 2, 0, 500, 0.5);
                break;
            default:
                signal = new SteadyNoise(1, 100, 2, 0, 500, 0.5);
                break;
        }

        if (number == 1) {
            signal1 = signal;
        } else {
            signal2 = signal;
        }
    }

    private void renderSignal(int number) {
        ISignal signal = (number == 1 ? signal1 : signal2);
        JPanel panel = (number == 1 ? view.getSignalChart1() : view.getSignalChart2());
        final XYSeries series = new XYSeries("data");
        List<Double> x = signal.getValuesX();
        List<Double> y = signal.getValuesY();
        for (int i = 0; i < x.size(); i++) {
            System.out.println(x.get(i) + " - " + y.get(i));
            series.add(x.get(i), y.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Signal " + number, "x", "y", dataset, PlotOrientation.VERTICAL, false, false, false);
        panel.add(new ChartPanel(chart), BorderLayout.CENTER);
        panel.validate();
    }
}
