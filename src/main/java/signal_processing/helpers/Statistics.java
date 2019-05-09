package signal_processing.helpers;

import signal_processing.ISignal;
import sun.jvm.hotspot.runtime.StaticBaseConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistics {
    private ISignal signal;
    private ISignal reconstructedSignal;

    public Statistics(ISignal signal){
        this.signal = signal;
    }

    public Statistics(ISignal signal, ISignal reconstructedSignal) {
        this.signal = signal;
        this.reconstructedSignal = reconstructedSignal;
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

    // Medium square error
    public double MSE (ISignal signal, ISignal reconstructedSignal) {
        double mediumSquareError = 0d;
        double reconstructedSignalSize = reconstructedSignal.getValuesX().size();

        for (int i = 0; i < reconstructedSignalSize; i++) {
            mediumSquareError += Math.pow(signal.getValuesY().get(i) - reconstructedSignal.getValuesY().get(i), 2);
        }
        return mediumSquareError / signal.getValuesX().size();
    }

    // Signal noise ratio
    public double SNR(ISignal signal, ISignal reconstructedSignal) {
        double signalNoiseRatio = 0d;
        double sum = 0d;
        double mediumSquareError = MSE(signal, reconstructedSignal);
        double reconstructedSignalSize = reconstructedSignal.getValuesX().size();

        for (int i = 0; i < reconstructedSignalSize; i++) {
            sum += Math.pow(signal.getValuesY().get(i), 2);
        }
        signalNoiseRatio = 10 * Math.log10(sum / mediumSquareError);

        return signalNoiseRatio;
    }

    // Peak signal to noise ratio
    public double PSNR(ISignal signal, ISignal reconstructedSignal) {
        double peakSignalToNoiseRatio = 0d;
        List<Double> valuesList = new ArrayList<>();
        double up = 0d;
        double meanSquareError = MSE(signal, reconstructedSignal);
        double reconstructedSignalSize = reconstructedSignal.getValuesX().size();

        for (int i = 0; i < reconstructedSignalSize; i++) {
            valuesList.add(signal.getValuesY().get(i));
        }
        up = Collections.max(valuesList);
        peakSignalToNoiseRatio = 10 * Math.log10(up / meanSquareError);

        return peakSignalToNoiseRatio;
    }

    // Maximum difference
    public double MD(ISignal signal, ISignal reconstructedSignal) {
        double maximumDifference = 0d;
        List<Double> valuesList = new ArrayList<>();
        double reconstructedSignalSize = reconstructedSignal.getValuesX().size();

        for (int i = 0; i < reconstructedSignalSize; i++){
            valuesList.add(Math.abs(signal.getValuesY().get(i) - reconstructedSignal.getValuesY().get(i)));
        }
        maximumDifference = Collections.max(valuesList);

        return maximumDifference;
    }

    // Effective number of bits
    public double ENOB(ISignal signal, ISignal reconstructedSignal) {
        double effectiveNumberOfBits = 0d;
        double signalNoiseRatio = SNR(signal, reconstructedSignal);

        effectiveNumberOfBits = (signalNoiseRatio - 1.76) / 6.02;

        return effectiveNumberOfBits;
    }
}
