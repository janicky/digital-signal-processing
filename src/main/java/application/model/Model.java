package application.model;

import signal_processing.ISignal;
import signal_processing.helpers.Operations;
import signal_processing.helpers.Statistics;
import signal_processing.signals.*;

public class Model {
    private ISignal[] signals = new ISignal[2];
    private ISignal generatedSignal;
    private Statistics[] stats = new Statistics[2];
    private Statistics generatedStats;
    private double samplingFrequency = 0.1;
    private int samplingSignal = 0;
    private ISignal sampledSignal;
    private int quantizationLevels = 2;
    private int quantizationSignal = 0;
    private ISignal quantizedSignal;
    private int reconstructionSignal = 0;
    private double reconstructionFrequency = 0.1;
    private int reconstructionType = 0;
    private ISignal originalReconstructionSignal;
    private ISignal reconstructedSignal;
    private int filterSignal = 0;
    private ISignal filteredSignal;
    private ISignal originalFilteredSignal;
    private int filterType = 0;
    private int windowType = 0;
    private double cutoffFrequency = 0.05;

    public ISignal getSignal(int index) {
        return signals[index];
    }

    public void setSignal(int index, ISignal signal) {
        this.signals[index] = signal;
    }

    public void setSignal(int index, int type) {
        ISignal signal = getIdentifiedSignal(type);
        signals[index] = signal;
        stats[index] = new Statistics(signal);
    }

    public ISignal getIdentifiedSignal(int type) {
        switch (type) {
            case 0:
                return new SteadyNoise();
            case 1:
                return  new GaussianNoise();
            case 2:
                return new ImpulseNoise();
            case 3:
                return new SinusoidalSignal();
            case 4:
                return new SinusoidalOneHalfSignal();
            case 5:
                return new SinusoidalTwoHalfSignal();
            case 6:
                return new RectangularSignal();
            case 7:
                return new SymmetricalRectangularSignal();
            case 8:
                return new TriangularSignal();
            case 9:
                return new IndividualJumpSignal();
            case 10:
                return new IndividualImpulseSignal();
            default:
                return new GeneratedSignal();
        }
    }

    public Statistics getStats(int index) {
        return stats[index];
    }

    public Statistics getGeneratedStats() {
        return generatedStats;
    }

    public boolean isBothSignalsRendered() {
        return signals[0].isRendered() && signals[1].isRendered();
    }

    public ISignal getGeneratedSignal() {
        return generatedSignal;
    }

    public void generateSignal(int operation, int order) {
        ISignal signal1 = (order == 0 ? signals[0] : signals[1]);
        ISignal signal2 = (order == 0 ? signals[1] : signals[0]);
        generatedSignal = getGeneratedSignal(signal1, signal2, operation);
        generatedStats = new Statistics(generatedSignal);
    }

    private ISignal getGeneratedSignal(ISignal signal1, ISignal signal2, int operation) {
        switch (operation) {
            case 1:
                return Operations.getSubstractSignals(signal1, signal2);
            case 2:
                return Operations.getMultiplySignals(signal1, signal2);
            case 3:
                return Operations.getDivideSignals(signal1, signal2);
            default:
                return Operations.getAddSignals(signal1, signal2);
        }
    }

    public double getSamplingFrequency() {
        return samplingFrequency;
    }

    public void setSamplingFrequency(double samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

    public int getSamplingSignal() {
        return samplingSignal;
    }

    public void setSamplingSignal(int samplingSignal) {
        this.samplingSignal = samplingSignal;
    }

    public ISignal getSampledSignal() {
        return sampledSignal;
    }

    public void setSampledSignal(ISignal sampledSignal) {
        this.sampledSignal = sampledSignal;
    }

    public int getQuantizationLevels() {
        return quantizationLevels;
    }

    public void setQuantizationLevels(int quantizationLevels) {
        this.quantizationLevels = quantizationLevels;
    }

    public int getQuantizationSignal() {
        return quantizationSignal;
    }

    public void setQuantizationSignal(int quantizationSignal) {
        this.quantizationSignal = quantizationSignal;
    }

    public ISignal getQuantizedSignal() {
        return quantizedSignal;
    }

    public void setQuantizedSignal(ISignal quantizedSignal) {
        this.quantizedSignal = quantizedSignal;
    }

    public double getReconstructionFrequency() {
        return reconstructionFrequency;
    }

    public void setReconstructionFrequency(double reconstructionFrequency) {
        this.reconstructionFrequency = reconstructionFrequency;
    }

    public int getReconstructionType() {
        return reconstructionType;
    }

    public int getReconstructionSignal() {
        return reconstructionSignal;
    }

    public void setReconstructionSignal(int reconstructionSignal) {
        this.reconstructionSignal = reconstructionSignal;
    }

    public void setReconstructionType(int reconstructionType) {
        this.reconstructionType = reconstructionType;
    }

    public ISignal getReconstructedSignal() {
        return reconstructedSignal;
    }

    public void setReconstructedSignal(ISignal reconstructedSignal) {
        this.reconstructedSignal = reconstructedSignal;
    }

    public ISignal getOriginalReconstructionSignal() {
        return originalReconstructionSignal;
    }

    public void setOriginalReconstructionSignal(ISignal originalReconstructionSignal) {
        this.originalReconstructionSignal = originalReconstructionSignal;
    }

    public int getFilterSignal() {
        return filterSignal;
    }

    public void setFilterSignal(int filterSignal) {
        this.filterSignal = filterSignal;
    }

    public ISignal getFilteredSignal() {
        return filteredSignal;
    }

    public void setFilteredSignal(ISignal filteredSignal) {
        this.filteredSignal = filteredSignal;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getWindowType() {
        return windowType;
    }

    public void setWindowType(int windowType) {
        this.windowType = windowType;
    }

    public double getCutoffFrequency() {
        return cutoffFrequency;
    }

    public void setCutoffFrequency(double cutoffFrequency) {
        this.cutoffFrequency = cutoffFrequency;
    }

    public ISignal getOriginalFilteredSignal() {
        return originalFilteredSignal;
    }

    public void setOriginalFilteredSignal(ISignal originalFilteredSignal) {
        this.originalFilteredSignal = originalFilteredSignal;
    }
}
