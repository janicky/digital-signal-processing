package signal_processing.signals;

import signal_processing.Signal;

public class SinusoidalOneHalfSignal extends Signal {

    public SinusoidalOneHalfSignal(int firstSample, int lastSample, double amplitude,
                                   double startTime, double endTime, double basicPeroid,
                                   double frequency) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setBasicPeriod(basicPeroid);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x) {
        return 0.5 * getAmplitude() * (Math.sin((2 * Math.PI / getBasicPeriod()) *
                (x - getStartTime())) + Math.abs((2 * Math.PI / getBasicPeriod()) *
                (x -getStartTime())));
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i <= samples; i++) {
            x.add((double) i);
            y.add(getValue(i));
        }
    }
}