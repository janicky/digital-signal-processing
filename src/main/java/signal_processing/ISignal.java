package signal_processing;

import java.util.List;

public interface ISignal {
    int getFirstSample();
    void setFirstSample(int firstSample);
    int getLastSample();
    void setLastSample(int lastSample);
    double getAmplitude();
    void setAmplitude(double amplitude);
    double getStartTime();
    void setStartTime(double startTime);
    double getEndTime();
    void setEndTime(double endTime);
    double getFrequency();
    void setFrequency(double frequency);
    double getProbability();
    void setProbability(double probability);
    double getValue(double x);
    void updateValues();
    List<Double> getValuesX();
    List<Double> getValuesY();
}
