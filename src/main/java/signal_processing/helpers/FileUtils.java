package signal_processing.helpers;

import signal_processing.ISignal;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileUtils {
    public static void saveSignal(ISignal signal, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeDouble(signal.getStartTime());
        dos.writeDouble(signal.getFrequency());
        dos.writeInt(0); // value type

        List<Double> x = signal.getValuesX();
        List<Double> y = signal.getValuesY();

        dos.writeInt(x.size()); // values count

        for (Double d : x) {
            dos.writeDouble(d);
        }
        for (Double d : y) {
            dos.writeDouble(d);
        }

        dos.close();
    }
}
