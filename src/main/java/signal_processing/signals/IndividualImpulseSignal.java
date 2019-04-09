package signal_processing.signals;

import signal_processing.Signal;

public class IndividualImpulseSignal extends Signal {
    private double sampleJump;

    public IndividualImpulseSignal(int firstSample, int lastSample, double sampleJump) {
        super(firstSample, lastSample);
        this.sampleJump = sampleJump;
        updateValues();
    }

    public double getValue(double x) {
        if (x == sampleJump) {
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

    public double getSampleJump() {
        return sampleJump;
    }

    public void setSampleJump(double sampleJump) {
        this.sampleJump = sampleJump;
    }
}
