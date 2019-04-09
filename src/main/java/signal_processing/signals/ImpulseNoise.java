package signal_processing.signals;

import signal_processing.Signal;

import java.util.Random;

public class ImpulseNoise extends Signal {
    private Random rng = new Random();
    private double probability;

    public ImpulseNoise(int firstSample, int lastSample, double probability) {
        super(firstSample, lastSample);
        this.probability = probability;
        updateValues();

    }

    public double getValue(double x) {
        if (rng.nextDouble() <= probability) {
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

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
