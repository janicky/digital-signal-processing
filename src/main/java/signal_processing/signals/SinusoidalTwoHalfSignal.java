package signal_processing.signals;

import signal_processing.Signal;

// Syngał sinusoidalny wyprostowany dwuopołówkowo
public class SinusoidalTwoHalfSignal extends Signal {

    public SinusoidalTwoHalfSignal(int firstSample, int lastSample, double amplitude,
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

    public double getValue(double x, double k) {
        return getAmplitude() * Math.abs(Math.sin((2 * Math.PI / getBasicPeriod()) *
                (x -getStartTime())));
    }

    public void updateValues() {
        x.clear();
        y.clear();
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i <= samples; i++) {
            double t = (i / getFrequency()) + getStartTime();
            x.add(t);
            y.add(getValue(t, 0d));
        }
    }

    public String getSignalName() {
        return "Sinusoidal Two-half Signal";
    }

    public String[] getAvailableParameters() {
        return new String[] {
                "firstSample",
                "lastSample",
                "amplitude",
                "startTime",
                "endTime",
                "basicPeroid",
                "frequency"
        };
    }
}
