package application.model;

import application.view.SignalPanel;
import signal_processing.ISignal;
import signal_processing.helpers.Operations;
import signal_processing.helpers.Statistics;
import signal_processing.signals.*;

public class Model {
    private ISignal[] signals = new ISignal[2];
    private ISignal generatedSignal;
    private Statistics[] stats = new Statistics[2];
    private Statistics generatedStats;

    public ISignal getSignal(int index) {
        return signals[index];
    }

    public void setSignal(int index, ISignal signal) {
        this.signals[index] = signal;
    }

    public void setSignal(int index, int type, SignalPanel sp) {
        ISignal signal;

        int firstSample = (int) sp.getFirstSample().getValue();
        int lastSample = (int) sp.getLastSample().getValue();
        double amplitude = (double) sp.getAmplitude().getValue();
        double startTime = (double) sp.getStartTime().getValue();
        double endTime = (double) sp.getEndTime().getValue();
        double frequency = (double) sp.getFrequency().getValue();
        double basicPeriod = (double) sp.getBasicPeriod().getValue();
        double fillingFactor = (double) sp.getFillingFactor().getValue();
        double probability = (double) sp.getProbability().getValue();
        double jumpPoint = (double) sp.getJumpPoint().getValue();
        double sampleJump = (double) sp.getSampleJump().getValue();

        switch (type) {
            case 1:
                signal = new GaussianNoise(firstSample, lastSample, startTime, endTime, frequency);
                break;
            case 2:
                signal = new ImpulseNoise(firstSample, lastSample, probability);
                break;
            case 3:
                signal = new SinusoidalSignal(firstSample, lastSample, amplitude, startTime, endTime, basicPeriod, frequency);
                break;
            case 4:
                signal = new SinusoidalOneHalfSignal(firstSample, lastSample, amplitude, startTime, endTime, basicPeriod, frequency);
                break;
            case 5:
                signal = new SinusoidalTwoHalfSignal(firstSample, lastSample, amplitude, startTime, endTime, basicPeriod, frequency);
                break;
            case 6:
                signal = new RectangularSignal(firstSample, lastSample, amplitude, startTime, endTime, basicPeriod, fillingFactor, frequency);
                break;
            case 7:
                signal = new SymmetricalRectangularSignal(firstSample, lastSample, amplitude, startTime, endTime, basicPeriod, fillingFactor, frequency);
                break;
            case 8:
                signal = new TriangularSignal(firstSample, lastSample, amplitude, startTime, endTime, basicPeriod, fillingFactor, frequency);
                break;
            case 9:
                signal = new IndividualJumpSignal(firstSample, lastSample, amplitude, startTime, endTime, frequency, jumpPoint);
                break;
            case 10:
                signal = new IndividualImpulseSignal(firstSample, lastSample, sampleJump);
                break;
            default:
                signal = new SteadyNoise(firstSample, lastSample, amplitude, startTime, endTime, frequency);
                break;
        }

        signals[index] = signal;
        stats[index] = new Statistics(signal);
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
}
