package signal_processing.signals;

import signal_processing.Signal;

public class IndividualSignal extends Signal {
    public IndividualSignal(int firstSample, int lastSample, double samleJump) {
        super(firstSample, lastSample);
        setSampleJump(samleJump);
        updateValues();
    }

    public double getValue(double x) {
        if (x == getSampleJump()) {
            return 1d;
        } else {
            return 0d;
        }
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i <= samples; i++) {
            x.add((double) i);
            y.add(getValue(i));
        }
    }
}
