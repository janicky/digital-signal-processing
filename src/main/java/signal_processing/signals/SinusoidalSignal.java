package signal_processing.signals;

import signal_processing.Signal;

public class SinusoidalSignal extends Signal {

    public SinusoidalSignal(int firstSample, int lastSample, double amplitude,
                            double startTime, double endTime, double basicPeriod,
                            double frequency) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setBasicPeriod(basicPeriod);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x) {
        return getAmplitude() * Math.sin((2 * Math.PI / getBasicPeriod()) *
                (x - getStartTime()));
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample();  i <= samples ; i++) {
            x.add((double) i);
            y.add(getValue(i));
        }
    }
}
