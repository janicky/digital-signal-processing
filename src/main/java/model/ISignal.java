package model;

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
}
