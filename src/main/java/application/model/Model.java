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

    public void setSignal(int index, int type) {
        ISignal signal = getIdentifiedSignal(type);
        signals[index] = signal;
        stats[index] = new Statistics(signal);
    }

    public ISignal getIdentifiedSignal(int type) {
        switch (type) {
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
                return new SteadyNoise();
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
}
