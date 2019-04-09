package signal_processing.signals;

import signal_processing.Signal;

// Sygnał prostokątny
public class RectangularSignal extends Signal {
    public RectangularSignal(int firstSample, int lastSample, double amplitude,
                             double startTime, double endTime, double basicPeriod,
                             double fillingFactor, double frequency) {
        super(firstSample, lastSample);
        setAmplitude(amplitude);
        setStartTime(startTime);
        setEndTime(endTime);
        setBasicPeriod(basicPeriod);
        setFillingFactor(fillingFactor);
        setFrequency(frequency);
        updateValues();
    }

    public double getValue(double x, double k) {
        if (x >= getBasicPeriod() * (k+1) + getStartTime()) {
            k++;
        }
            if (((x) >= (k * getBasicPeriod() + getStartTime()))
                    &&
                    ((x) < (getFillingFactor() * getBasicPeriod() + k *
                            getBasicPeriod() + getStartTime())) ){
               return getAmplitude();
            } else if (((x) >= (getFillingFactor() * getBasicPeriod() + k *
                    getBasicPeriod() + getStartTime()))
                    ||
                    (x) <(k * getBasicPeriod() + getStartTime())){
                return 0d;
            }
            //TODO: check this implementation
            return 0;
    }

    public void updateValues() {
        //TODO: Add k parameter
        int samples = (int) (getFrequency() * getEndTime());
        for (int i = getFirstSample(); i <= samples; i++) {
            double t = (i / getFrequency()) + getStartTime();
            x.add(t);
            y.add(getValue(t, 0));
        }
    }
}
