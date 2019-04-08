package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Signal implements ISignal {
    List<Double> x = new ArrayList<>();
    List<Double> y = new ArrayList<>();

    private int firstSample;
    private int lastSample;
    private double amplitude;
    private double startTime;
    private double endTime;
    private double frequency;

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
}
