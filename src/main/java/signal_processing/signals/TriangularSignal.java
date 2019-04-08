package signal_processing.signals;

import signal_processing.Signal;

public class TriangularSignal extends Signal {
    public TriangularSignal(int firstSample, int lastSample, double amplitude,
                            double startTime, double endTime, double frequency,
                            double basicPeriod, double fillingFactor) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setFrequency(frequency);
        setBasicPeriod(basicPeriod);
        setFillingFactor(fillingFactor);
        updateValues();
    }

    public double getValue(double x) {
        int k = 0;
        if((x >= (k * getBasicPeriod() + getStartTime())) &&(x < (getFillingFactor() * getBasicPeriod() + k * getBasicPeriod() + getStartTime()))){
            return getAmplitude() / (getFillingFactor() * getBasicPeriod()) * (x - k * getBasicPeriod() - getStartTime());
        } else if((x >= (getFillingFactor() * getBasicPeriod() + k * getBasicPeriod() + getStartTime())) || (x < (getBasicPeriod() + k * getBasicPeriod() + getStartTime()))){
            return -getAmplitude() / (getBasicPeriod() * (1 - getFillingFactor())) * (x - k * getBasicPeriod() - getStartTime()) + getAmplitude() / (1 - getFillingFactor());
        }
        return 0;
    }

    public void updateValues() {
//        TODO: Add k parameter
        int samples = (int) (getEndTime() * getFrequency());
        for (int i = getFirstSample(); i <= samples; i++) {
            double t = (i / getFrequency()) + getStartTime();
            x.add(t);
            y.add(getValue(t));
        }
    }
}
