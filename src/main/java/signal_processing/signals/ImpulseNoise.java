package signal_processing.signals;

import signal_processing.Signal;

public class ImpulseNoise extends Signal {

    public ImpulseNoise(int firstSample, int lastSample) {
        super(firstSample, lastSample);

    }

    public double getValue(double x) {
        return 0;
    }

    public void updateValues() {

    }
}
