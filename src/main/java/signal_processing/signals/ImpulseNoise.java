package signal_processing.signals;

import signal_processing.Signal;

import java.util.Random;

public class ImpulseNoise extends Signal {
    private Random rng = new Random();

    public ImpulseNoise(int firstSample, int lastSample) {
        super(firstSample, lastSample);

    }

    public double getValue(double x) {
        if (rng.nextDouble() <= getProbability()) {
            return 1d;
        } else {
            return 0d;
        }
    }

    public void updateValues() {
        for (int i = getFirstSample(); i <= getLastSample(); i++ ){
            x.add((double) i);
            y.add(getValue(0));
        }
    }
}
