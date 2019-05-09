package signal_processing.helpers;

import signal_processing.ISignal;
import signal_processing.Signal;
import signal_processing.signals.GeneratedSignal;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operations {

    public static ISignal getMultiplySignals(ISignal signal1, ISignal signal2) {
        GeneratedSignal calculatedSignal = new GeneratedSignal();
        List<Double> tempX = new ArrayList<>();
        List<Double> tempY = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            int count = 0;
            for (int j = 0; j < signal2.getValuesY().size(); j++) {
                if (signal1.getValuesX().get(i).equals(signal2.getValuesX().get(j))) {
                    tempX.add(signal1.getValuesX().get(i));
                    tempY.add(signal1.getValuesY().get(i) * signal2.getValuesY().get(j));
                    count++;
                    tempList.add((double) j);
                }
            }
            if (count == 0) {
                tempX.add(signal1.getValuesX().get(i));
                tempY.add(0d);
            }
        }

        for (int k = 0; k < signal2.getValuesX().size(); k++) {
            int count = 0;
            for (int l = 0; l < tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count++;
                }
            }
            if (count == 0) {
                tempX.add(signal2.getValuesX().get(k));
                tempY.add(0d);
            }
        }

        calculatedSignal.setValuesX(tempX);
        calculatedSignal.setValuesY(tempY);
        return calculatedSignal;
    }

    public static ISignal getAddSignals(ISignal signal1, ISignal signal2) {
        GeneratedSignal calculatedSignal = new GeneratedSignal();
        List<Double> tempX = new ArrayList<>();
        List<Double> tempY = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            int count = 0;
            for (int j = 0; j < signal2.getValuesX().size(); j++) {
                if (signal1.getValuesX().get(i).equals(signal2.getValuesX().get(j))) {
                    tempX.add(signal1.getValuesX().get(i));
                    tempY.add(signal1.getValuesY().get(i) + signal2.getValuesY().get(j));
                    count++;
                    tempList.add((double) j);
                }
            }
            if (count == 0) {
                tempX.add(signal1.getValuesX().get(i));
                tempY.add(signal1.getValuesY().get(i));
            }
        }

        for (int k = 0; k < signal2.getValuesX().size(); k++) {
            int count = 0;
            for (int l = 0; l < tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count++;
                }
            }
            if (count == 0) {
                tempX.add(signal2.getValuesX().get(k));
                tempY.add(signal2.getValuesY().get(k));
            }
        }

        calculatedSignal.setValuesX(tempX);
        calculatedSignal.setValuesY(tempY);
        return calculatedSignal;
    }

    public static ISignal getDivideSignals(ISignal signal1, ISignal signal2) {
        GeneratedSignal calculatedSignal = new GeneratedSignal();
        List<Double> tempX = new ArrayList<>();
        List<Double> tempY = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            int count = 0;
            for (int j = 0; j < signal2.getValuesX().size(); j++) {
                if (signal1.getValuesX().get(i).equals(signal2.getValuesX().get(j))) {
                    if (signal2.getValuesY().get(j) != 0) {
                        tempX.add(signal1.getValuesX().get(i));
                        tempY.add(signal1.getValuesY().get(i) / signal2.getValuesY().get(j));
                    }
                }
                count++;
                tempList.add((double) j);
            }
            if (count == 0) {
                tempX.add(signal1.getValuesX().get(i));
                tempY.add(0d);
            }
        }

        for (int k = 0; k <signal2.getValuesX().size(); k++) {
            int count = 0;
            for (int l = 0; l < tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count++;
                }
            }
            if (count == 0) {
                tempX.add(signal2.getValuesX().get(k));
                tempY.add(0d);
            }
        }

        calculatedSignal.setValuesX(tempX);
        calculatedSignal.setValuesY(tempY);
        return calculatedSignal;
    }

    public static ISignal getSubstractSignals(ISignal signal1, ISignal signal2) {
        GeneratedSignal calculatedSignal = new GeneratedSignal();
        List<Double> tempX = new ArrayList<>();
        List<Double> tempY = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            int count = 0;
            for (int j = 0; j < signal2.getValuesX().size(); j++) {
                if (signal1.getValuesX().get(i).equals(signal2.getValuesX().get(j))) {
                    tempX.add(signal1.getValuesX().get(i));
                    tempY.add(signal1.getValuesY().get(i) - signal2.getValuesY().get(j));
                    count++;
                    tempList.add((double) j);
                }
            }
            if (count == 0) {
                tempX.add(signal1.getValuesX().get(i));
                tempY.add(signal1.getValuesY().get(i));
            }
        }

        for (int k = 0; k < signal2.getValuesX().size(); k++) {
            int count = 0;
            for (int l = 0; l <tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count++;
                }
            }
            if (count == 0) {
                tempX.add(signal2.getValuesX().get(k));
                tempY.add( - signal2.getValuesY().get(k));
            }
            count = 0;
        }

        calculatedSignal.setValuesX(tempX);
        calculatedSignal.setValuesY(tempY);
        return calculatedSignal;
    }

    public static ISignal sampling(ISignal signal, double samples) {
        GeneratedSignal sampledSignal = new GeneratedSignal();

        int i = 0;
        int stepNominator = 0;
        Double start = signal.getStartTime();

        while(start < signal.getLastSample()) {
            double startTmp = (stepNominator / samples) + signal.getStartTime();
            start = startTmp;
            if (signal.getValuesX().get(i) >= start) {
                sampledSignal.getValuesX().add(start);
                sampledSignal.getValuesY().add(signal.getValuesY().get(i));
                stepNominator++;
            }
            i++;
        }

        return sampledSignal;
    }

    public static ISignal quantizationBits(ISignal signal, int bits) {
        ISignal quantizedSignal = signal.copy();

        List<Double> levels = new ArrayList<Double>();
        double max = Collections.max(signal.getValuesY());
        double min = Collections.min(signal.getValuesY());
        double q = (Math.abs(max-min)) / (Math.pow(2, bits) - 1);

        for (int i = 0; i < Math.pow(2, bits); i++) {
            levels.add(min + i * q);
        }

        for (int i = 0; i < signal.getValuesX().size(); i++) {
            quantizedSignal.getValuesX().add(signal.getValuesX().get(i));

            final int j = i;
            Collections.sort(levels, Ordering.natural().onResultOf(p -> Math.abs(signal.getValuesY().get(j) - p)));
            quantizedSignal.getValuesY().add(levels.get(0));
        }

        return  quantizedSignal;
    }

    public static ISignal quantizationLevels(ISignal signal, int numberOfLevels) {
        ISignal quantizedSignal = signal.copy();
        double max = Collections.max(signal.getValuesY());
        double min = Collections.min(signal.getValuesY());
        double step = (Math.abs(max-min)) / (numberOfLevels - 1);
        List<Double> levels = new ArrayList<Double>();

        for (int i = 0; i < numberOfLevels; i++) {
            levels.add(min + i * step);
        }

        for (int i = 0; i < signal.getValuesX().size(); i++) {
            quantizedSignal.getValuesX().add(signal.getValuesX().get(i));

            final int j = i;
            Collections.sort(levels, Ordering.natural().onResultOf(p -> Math.abs(signal.getValuesY().get(j) - p)));
            quantizedSignal.getValuesY().add(levels.get(0));
        }

        return quantizedSignal;
    }


}
