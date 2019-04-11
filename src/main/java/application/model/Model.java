package application.model;

import application.view.SignalPanel;
import signal_processing.ISignal;
import signal_processing.helpers.Statistics;
import signal_processing.signals.*;

public class Model {
    private ISignal[] signals = new ISignal[2];
    private GeneratedSignal generatedSignal;
    private Statistics[] stats = new Statistics[2];

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

    public boolean isBothSignalsRendered() {
        return signals[0].isRendered() && signals[1].isRendered();
    }

    public GeneratedSignal getGeneratedSignal() {
        return generatedSignal;
    }

    public void setGeneratedSignal(GeneratedSignal generatedSignal) {
        this.generatedSignal = generatedSignal;
    }
}
