package application.model;

import application.view.SignalPanel;
import signal_processing.ISignal;
import signal_processing.signals.SteadyNoise;

public class Model {
    private ISignal[] signals = new ISignal[2];

    public ISignal getSignal(int index) {
        return signals[index];
    }

    public void setSignal(int index, ISignal signal) {
        this.signals[index] = signal;
    }

    public void setSignal(int index, int type, SignalPanel sp) {
        ISignal signal;
        int firstSample = (int) sp.getFirstSample().getValue();
        int lastSample = (int) sp.getLastSample().getValue();
        double amplitude = (double) sp.getAmplitude().getValue();
        double startTime = (double) sp.getStartTime().getValue();
        double endTime = (double) sp.getEndTime().getValue();
        double frequency = (double) sp.getFrequency().getValue();
//        double basicPeriod = (double) sp.getBasicPeriod().getValue();
//        double fillingFactor = (double) sp.getFillingFactor().getValue();

        switch (type) {
            default:
                signal = new SteadyNoise(firstSample, lastSample, amplitude, startTime, endTime, frequency);
                break;
        }

        signals[index] = signal;
    }
}
