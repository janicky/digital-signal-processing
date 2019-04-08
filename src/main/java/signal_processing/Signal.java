package signal_processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Signal implements ISignal {
    protected List<Double> x = new ArrayList<>();
    protected List<Double> y = new ArrayList<>();

    private int firstSample;
    private int lastSample;
    private double amplitude;
    private double startTime;
    private double endTime;
    private double frequency;
    private double basicPeriod;
    private double fillingFactor;
    private double probability;

    public Signal(int firstSample, int lastSample) {
        this.firstSample = firstSample;
        this.lastSample = lastSample;
    }

    public int getFirstSample() {
        return firstSample;
    }

    public void setFirstSample(int firstSample) {
        this.firstSample = firstSample;
    }

    public int getLastSample() {
        return lastSample;
    }

    public void setLastSample(int lastSample) {
        this.lastSample = lastSample;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getBasicPeriod() {
        return basicPeriod;
    }

    public void setBasicPeriod(double basicPeriod) {
        this.basicPeriod = basicPeriod;
    }

    public double getFillingFactor() {
        return fillingFactor;
    }

    public void setFillingFactor(double fillingFactor) {
        this.fillingFactor = fillingFactor;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public List<Double> getValuesX() {
        return Collections.unmodifiableList(x);
    }

    public List<Double> getValuesY() {
        return Collections.unmodifiableList(y);
    }
}
