package signal_processing.signals;

import signal_processing.Signal;

import java.util.Random;

public class GaussianNoise extends Signal {
    private Random rng = new Random();
    private boolean nextGaussian = false;
    private double gaussian = 0, variable1 = 0, variable2 = 0, result = 0, multiplayer = 0;

    public GaussianNoise(int firstSample, int lastSample, double startTime,
                         double endTime, double frequency){
        super(firstSample, lastSample);
        setStartTime(startTime);
        setEndTime(endTime);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x, double k) {
        if (nextGaussian) {
            nextGaussian = false;
            return gaussian;
        } else {
            do {
                variable1 = 2 * rng.nextDouble() - 1;
                variable2 = 2 * rng.nextDouble() - 1;
                result = (variable1 * variable1) + (variable2 * variable2);
            } while (result >= 1 || result == 0);
            multiplayer = StrictMath.sqrt(-2 * StrictMath.log(result) / result);
            gaussian = variable2 * multiplayer;
            nextGaussian = true;
            return variable1 * multiplayer;
        }
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i <= samples; i++) {
            double t = i / getFrequency() + getStartTime();
            x.add(t);
            y.add(getValue(t,0));
        }

    }

    public String[] getAvailableParameters() {
        return new String[] {
                "firstSample",
                "lastSample",
                "startTime",
                "endTime",
                "frequency"
        };
    }
}
