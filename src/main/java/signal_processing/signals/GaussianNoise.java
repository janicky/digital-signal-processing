package signal_processing.signals;

import signal_processing.Signal;

import java.util.Random;

public class GaussianNoise extends Signal {
    private Random rng = new Random();

    public GaussianNoise(int firstSample, int lastSample, double startTime,
                         double endTime, double frequency){
        super(firstSample, lastSample);
        setStartTime(startTime);
        setEndTime(endTime);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x) {
        return 0;
        //TODO: Implements getValue method
    }

    public void updateValues() {
        //TODO: implements updateValues method

    }
}
