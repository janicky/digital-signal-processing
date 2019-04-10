package signal_processing.helpers;

import signal_processing.ISignal;
import signal_processing.Signal;
import signal_processing.signals.GeneratedSignal;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    private ISignal signal1, signal2, calculatedSignal;

    public Operations (ISignal signal1, ISignal signal2) {
        this.signal1 = signal1;
        this.signal2 = signal2;
    }

    public ISignal getMultiplySignals(ISignal signal1, ISignal signal2) {
        calculatedSignal = new GeneratedSignal();
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

    public ISignal getAddSignals (ISignal signal, ISignal signal2) {
        calculatedSignal = new GeneratedSignal();
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

    public ISignal getDivideSignals (ISignal signal, ISignal signal2) {
        calculatedSignal = new GeneratedSignal();
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

    public ISignal getSubstractSignals (ISignal signal, ISignal signal2) {
        calculatedSignal = new GeneratedSignal();
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
