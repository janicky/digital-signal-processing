package signal_processing.signals;

import signal_processing.Signal;
import java.util.Random;

public class SteadyNoise extends Signal {
    private Random rng = new Random();

    public SteadyNoise(int firstSample, int lastSample, double amplitude,
                       double startTime, double endTime, double frequency) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x, double k) {
        return rng.nextDouble() * -(2 * getAmplitude()) + getAmplitude();
    }

    public void updateValues() {
        int samples = (int) (getEndTime() * getFrequency());
        for (int i = getFirstSample(); i <= samples; i++) {
            x.add((i / getFrequency()) + getStartTime());
//            TODO: Check if k parameter is necessary
            y.add(getValue(0d, 0));
        }
    }
}
