package signal_processing.signals;

import signal_processing.Signal;

public class IndividualSignal extends Signal {
    public IndividualSignal(int firstSample, int lastSample) {
        super(firstSample, lastSample);
        //TODO add SampleJump setter and complete method
        updateValues();
    }

    public double getValue(double x) {
        return 0;
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i <= samples; i++) {
            x.add((double) i);
            y.add(getValue(i));
        }
    }
}
