package signal_processing.signals;

import signal_processing.Signal;

public class SymmetricalRectangularSignal extends Signal {
    public SymmetricalRectangularSignal(int firstSample, int lastSample, double amplitude,
                                        double startTime, double endTime, double basicPeroid,
                                        double fillingFactor, double frequency) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setBasicPeriod(basicPeroid);
        setFillingFactor(fillingFactor);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x, int k) {
        if (x >= getBasicPeriod() * (k + 1) + getStartTime()) {
            k++;
        }

        if (((x) >= getBasicPeriod() * k + getStartTime()) &&
            ((x) < getFillingFactor() * getBasicPeriod() + k * getBasicPeriod() +getStartTime()) ) {
            return getAmplitude();
        } else if ((x) >= (getFillingFactor() * getBasicPeriod() + k * getBasicPeriod() + getStartTime()) ||
                ((x) < (k * getBasicPeriod() + getStartTime()))) {
            return (-getAmplitude());
        }
        return 0d; //TODO check this implementation
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i  = getFirstSample(); i <= samples; i++){
            x.add((double) i);
            y.add(getValue(i, 0));
        }
    }
}
