package signal_processing.signals;

import signal_processing.Signal;

public class IndividualJumpSignal extends Signal {
    private double jumpPoint;

    public IndividualJumpSignal(int firstSample, int lastSample, double amplitude,
                                double startTime, double endTime, double frequency,
                                double jumpPoint) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setFrequency(frequency);
        this.jumpPoint = jumpPoint;
        updateValues();
    }

    public double getValue(double x, double k) {
        return 0;
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i < samples; i++) {
            double t = (i / getFrequency()) + getStartTime();
            x.add(t);
//            TODO: Check if k parameter is necessary
            y.add(getValue(t, 0));
        }
    }

    public double getJumpPoint() {
        return jumpPoint;
    }

    public void setJumpPoint(double jumpPoint) {
        this.jumpPoint = jumpPoint;
    }
}
