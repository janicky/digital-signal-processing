package signal_processing.helpers;

import signal_processing.ISignal;

public class Statistics {
    private ISignal signal;

    public Statistics(ISignal signal){
        this.signal = signal;
    }

    public double getAverage(){
        double sum = 0d;
        for (Double value : signal.getValuesY()) {
            sum += value;
        }
        return sum * (1 / (signal.getEndTime() + 1));
    }

    public double getAbsoluteMean() {
        double sum = 0d;
        for (Double value : signal.getValuesY()) {
            sum += Math.abs(value);
        }
        return sum * (1 / signal.getEndTime() + 1);
    }

    public double getAveragePower() {
        double sum = 0d;
        for (Double value : signal.getValuesY()) {
            sum += Math.pow(value, 2);
        }
        return sum * (1 / (signal.getEndTime() + 1));
    }

    public double getEffectiveValue() {
        double sum = 0d;
        for (Double value : signal.getValuesY()) {
            sum += Math.pow(value - getAverage(), 2);
        }
        return sum * (1 / (signal.getEndTime() + 1));
    }

    public double getVariance() {
        double sum = 0d;
        for (Double value : signal.getValuesY()) {
            sum += Math.pow(value, 2);
        }
        return Math.sqrt(sum * (1 / signal.getEndTime() + 1));
    }
}
