package signal_processing.signals;

import signal_processing.Signal;

// Sygnał prostokątny symetryczny
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

    public double getValue(double x, double k) {
        //TODO Add k parametr
        if (((x) >= getBasicPeriod()  + getStartTime()) &&
            ((x) < getFillingFactor() * getBasicPeriod() +  getBasicPeriod() +getStartTime()) ) {
            return getAmplitude();
        } else if ((x) >= (getFillingFactor() * getBasicPeriod() +  getBasicPeriod() + getStartTime()) ||
                ((x) < ( getBasicPeriod() + getStartTime()))) {
            return (-getAmplitude());
        }
        return 0d;
    }

    public void updateValues() {
        int samples = (int) (getFrequency() * getEndTime());
        for (int i  = getFirstSample(); i <= samples; i++){
            double t = (i / getFrequency()) + getStartTime();
            x.add(t);
//            TODO: Check if k parameter is necessary
            y.add(getValue(t, 0));
        }
    }
}
