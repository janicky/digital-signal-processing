package signal_processing.helpers;

import signal_processing.ISignal;
import signal_processing.Signal;
import signal_processing.signals.GeneratedSignal;

import java.util.ArrayList;
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


}
