package signal_processing.signals;

import signal_processing.Signal;

public class IndividualJumpSignal extends Signal {

    public IndividualJumpSignal(int firstSample, int lastSample, double amplitude,
                                double startTime, double endTime, double frequency) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setFrequency(frequency);
        //TODO add "Punkt skoku w czasie" and complete methods
        updateValues();
    }

    public double getValue(double x) {
        return 0;
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i < samples; i++) {
            double t = (i / getFrequency()) + getStartTime();
            x.add(t);
            y.add(getValue(t));
        }
    }
}
