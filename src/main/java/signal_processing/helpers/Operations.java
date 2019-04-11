package signal_processing.helpers;

import signal_processing.ISignal;
import signal_processing.Signal;
import signal_processing.signals.GeneratedSignal;

import java.util.ArrayList;
import java.util.List;

public class Operations {

    public static ISignal getMultiplySignals(ISignal signal1, ISignal signal2) {
        ISignal calculatedSignal = new GeneratedSignal();
        List<Double> tempList = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            for (int j = 0; j < signal2.getValuesY().size(); j++) {
                if (signal1.getValuesX().get(i) == signal2.getValuesX().get(j)) {
                    calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                    calculatedSignal.getValuesY().add(signal1.getValuesY().get(i) * signal2.getValuesY().get(j));
                    count1++;
                    tempList.add((double) j);
                }
            }
            if (count1 == 0) {
                calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                calculatedSignal.getValuesY().add(0d);
            }
            count1 = 0;

        }

        for (int k = 0; k < signal2.getValuesX().size(); k++) {
            for (int l = 0; l < tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count2++;
                }
            }
            if (count2 == 0) {
                calculatedSignal.getValuesX().add(signal2.getValuesX().get(k));
                calculatedSignal.getValuesY().add(0d);
            }
            count2 = 0;
        }

        return calculatedSignal;
    }

    public static ISignal getAddSignals(ISignal signal1, ISignal signal2) {
        ISignal calculatedSignal = new GeneratedSignal();
        List<Double> tempList = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            for (int j = 0; j < signal2.getValuesX().size(); j++) {
                if (signal1.getValuesX().get(i) == signal2.getValuesX().get(i)) {
                    calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                    calculatedSignal.getValuesY().add(signal1.getValuesY().get(i) + signal2.getValuesY().get(j));
                    count1++;
                    tempList.add((double) j);
                }
            }
            if (count1 == 0) {
                calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                calculatedSignal.getValuesY().add(signal1.getValuesY().get(i));
            }
            count1 = 0;
        }

        for (int k = 0; k < signal2.getValuesX().size(); k++) {
            for (int l = 0; l <tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count2++;
                }
            }
            if (count2 == 0) {
                calculatedSignal.getValuesX().add(signal2.getValuesX().get(k));
                calculatedSignal.getValuesY().add(signal2.getValuesY().get(k));
            }
            count2 = 0;
        }

        return calculatedSignal;
    }

    public static ISignal getDivideSignals(ISignal signal1, ISignal signal2) {
        ISignal calculatedSignal = new GeneratedSignal();
        List<Double> tempList = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            for (int j = 0; j < signal2.getValuesX().size(); j++) {
                if (signal1.getValuesX().get(i) == signal2.getValuesX().get(j)) {
                    if (signal2.getValuesY().get(j) != 0) {
                        calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                        calculatedSignal.getValuesY().add(signal1.getValuesY().get(i) / signal2.getValuesY().get(j));
                    }
                }
                count1++;
                tempList.add((double) j);
            }
            if (count1 == 0) {
                calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                calculatedSignal.getValuesY().add(0d);
            }
            count1 = 0;
        }

        for (int k = 0; k <signal2.getValuesX().size(); k++) {
            for (int l = 0; l < tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count2++;
                }
            }
            if ( count2 == 0) {
                calculatedSignal.getValuesX().add(signal2.getValuesX().get(k));
                calculatedSignal.getValuesY().add(0d);
            }
            count2 = 0;
        }

        return calculatedSignal;
    }

    public static ISignal getSubstractSignals(ISignal signal1, ISignal signal2) {
        ISignal calculatedSignal = new GeneratedSignal();
        List<Double> tempList = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < signal1.getValuesX().size(); i++) {
            for (int j = 0; j < signal2.getValuesX().size(); j++) {
                if (signal1.getValuesX().get(i) == signal2.getValuesX().get(i)) {
                    calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                    calculatedSignal.getValuesY().add(signal1.getValuesY().get(i) - signal2.getValuesY().get(j));
                    count1++;
                    tempList.add((double) j);
                }
            }
            if (count1 == 0) {
                calculatedSignal.getValuesX().add(signal1.getValuesX().get(i));
                calculatedSignal.getValuesY().add(signal1.getValuesY().get(i));
            }
            count1 = 0;
        }

        for (int k = 0; k < signal2.getValuesX().size(); k++) {
            for (int l = 0; l <tempList.size(); l++) {
                if (k == tempList.get(l)) {
                    count2++;
                }
            }
            if (count2 == 0) {
                calculatedSignal.getValuesX().add(signal2.getValuesX().get(k));
                calculatedSignal.getValuesY().add( - signal2.getValuesY().get(k));
            }
            count2 = 0;
        }

        return calculatedSignal;
    }


}
