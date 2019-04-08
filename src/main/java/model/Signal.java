package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Signal {
    List<Double> x = new ArrayList<>();
    List<Double> y = new ArrayList<>();

    private int firstSample;
    private int lastSample;

    public Signal(int firstSample, int lastSample) {
        this.firstSample = firstSample;
        this.lastSample = lastSample;
    }

    public int getFirstSample() {
        return firstSample;
    }

    public int getLastSample() {
        return lastSample;
    }
}
