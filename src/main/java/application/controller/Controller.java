package application.controller;

import application.model.Model;
import application.view.SignalPanel;
import application.view.View;
import signal_processing.ISignal;
import signal_processing.signals.SteadyNoise;

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

//        Actions
        assignActions();
    }

    private void assignActions() {
        signalPanell.getSignalType().addActionListener(e -> onSignal1Change());
    }

    private void onSignal1Change() {
        setSignal(1, signalPanell.getSignalType().getSelectedIndex());
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
}
