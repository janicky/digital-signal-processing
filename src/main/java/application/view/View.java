package application.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import signal_processing.ISignal;
import signal_processing.helpers.Operations;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class View {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel signalChart1;
    private JPanel histogramChart1;
    private SignalPanel signalPanel1;
    private SignalPanel signalPanel2;
    private OperationsPanel operationsPanel;
    private JTabbedPane tabbedPane;
    private JPanel signalChart2;
    private JLabel noSignal1;
    private JLabel noSignal2;
    private JLabel noHistogram1;
    private JPanel histogramChart3;
    private JLabel noHistogram2;
    private JPanel defaultPanel;
    private JPanel correlationPanel;
    private JPanel sentSignal;
    private JPanel receivedSignal;
    private JPanel correlationSignal;
    private JPanel sentSignalHide;
    private JPanel receivedSignalHide2;
    private JPanel correlatedSignalHide;
    private ChartPanel chartPanel1;
    private ChartPanel chartPanel2;
    private ChartPanel chartPanel3;
    private ChartPanel chartPanel4;
    private ChartPanel chartPanel5;
    private JMenuItem file_item_1;
    private JMenuItem file_item_2;
    private ChartPanel sentChartPanel;
    private ChartPanel receivedChartPanel;
    private ChartPanel correlatedChartPanel;

    public void displayError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public View(String title) {
        frame = new JFrame(title);
        createMenu();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        sentChartPanel = new ChartPanel(null);
        receivedChartPanel = new ChartPanel(null);
        correlatedChartPanel = new ChartPanel(null);
        correlationPanel.setVisible(false);

        initializeView();
    }

    private void initializeView() {
        signalPanel1 = new SignalPanel();
        signalPanel2 = new SignalPanel();
        operationsPanel = new OperationsPanel();
        tabbedPane.add("Signal 1", signalPanel1.signalPanel);
        tabbedPane.add("Signal 2", signalPanel2.signalPanel);
        tabbedPane.add("Operations", operationsPanel.getOperationsPanel());

        chartPanel1 = new ChartPanel(null);
        chartPanel2 = new ChartPanel(null);
        chartPanel3 = new ChartPanel(null);
        chartPanel4 = new ChartPanel(null);
        chartPanel5 = new ChartPanel(null);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

//        File
        JMenu file = new JMenu("File");
        menuBar.add(file);

        file_item_1 = new JMenuItem("Import Signal 1");
        file_item_2 = new JMenuItem("Import Signal 2");

        file.add(file_item_1);
        file.add(file_item_2);
        frame.setJMenuBar(menuBar);
    }

    private void setIcon(JButton button, String path) {
        try {
            Image img = ImageIO.read(getClass().getResource(path));
            img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(img);
            button.setIcon(imgIcon);
        } catch (Exception ex) {
//          TODO: Handle icon not found error
        }
    }

    public void renderSignal(int index, ISignal signal, final XYSeriesCollection dataset) {
        JPanel panel = (index == 0 ? getSignalChart1() : getSignalChart2());
        ChartPanel chartPanel = (index == 0 ? chartPanel1 : chartPanel2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                signal.getSignalName(),
                "x",
                "y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);
        chartPanel.setChart(chart);

        if (panel.getComponentCount() != 2) {
            panel.add(chartPanel);
        }

        if (index == 0) {
            hideNoSignal1();
        } else {
            hideNoSignal2();
        }
    }

    public void renderGeneratedSignal(final XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Generated signal",
                "x",
                "y",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false);
        chartPanel5.setChart(chart);

        JPanel signalPanel = operationsPanel.getSignalPanel();
        if (signalPanel.getComponentCount() != 2) {
            signalPanel.add(chartPanel5);
            signalPanel.validate();
            operationsPanel.getNoSignal().setVisible(false);
        }
    }

    public void renderHistogram(int index, final HistogramDataset dataset) {
        JPanel panel = (index == 0 ? getHistogramChart1() : getHistogramChart2());
        ChartPanel chartPanel = (index == 0 ? chartPanel3 : chartPanel4);
        JFreeChart chart = ChartFactory.createHistogram(
                "Histogram",
                null,
                null,
                dataset,
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );

        if (panel.getComponentCount() != 2) {
            panel.add(chartPanel);
        }

        chartPanel.setChart(chart);

        if (index == 0) {
            hideNoHistogram1();
        } else {
            hideNoHistogram2();
        }
    }

    public void enableOperationsButtons() {
        operationsPanel.enableButtons();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public SignalPanel getSignalPanel1() {
        return signalPanel1;
    }

    public SignalPanel getSignalPanel2() {
        return signalPanel2;
    }

    public JPanel getSignalChart1() {
        return signalChart1;
    }

    public JPanel getHistogramChart1() {
        return histogramChart1;
    }

    public JPanel getHistogramChart2() {
        return histogramChart3;
    }

    public JPanel getSignalChart2() {
        return signalChart2;
    }

    public OperationsPanel getOperationsPanel() {
        return operationsPanel;
    }

    public void hideNoSignal1() {
        noSignal1.setVisible(false);
    }
    public void hideNoSignal2() {
        noSignal2.setVisible(false);
    }
    public void hideNoHistogram1() {
        noHistogram1.setVisible(false);
    }
    public void hideNoHistogram2() {
        noHistogram2.setVisible(false);
    }

    public int getOperation() {
        return operationsPanel.getOperation();
    }
    public int getOrder() {
        return operationsPanel.getOrder();
    }

    public JMenuItem getFile_item_1() {
        return file_item_1;
    }

    public JMenuItem getFile_item_2() {
        return file_item_2;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void addTabbedPaneListener(ChangeListener listener) {
        tabbedPane.addChangeListener(listener);
    }

    public void setDefaultPanelVisible(boolean state) {
        defaultPanel.setVisible(state);
    }
    public void setCorrelationPanelVisible(boolean state) {
        correlationPanel.setVisible(state);
    }

    public void renderSentSignal(ISignal signal) {
        renderCorrelationSignal(signal, sentSignal, sentChartPanel, sentSignalHide);
    }

    public void renderReceivedSignal(ISignal signal) {
        renderCorrelationSignal(signal, receivedSignal, receivedChartPanel, receivedSignalHide2);
    }

    public void renderCorrelatedSignal(ISignal signal) {
        renderCorrelationSignal(signal, correlationSignal, correlatedChartPanel, correlatedSignalHide);
    }

    private void renderCorrelationSignal(ISignal signal, JPanel targetPanel, ChartPanel targetChart, JPanel hide) {
        JFreeChart chart = Operations.getChart(signal);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(1));
        renderer.setSeriesPaint(0, Color.gray);
        plot.setRenderer(renderer);

        targetChart.setChart(chart);
        targetChart.validate();

        if (targetPanel.getComponentCount() != 2) {
            targetPanel.add(targetChart, 0);
            targetPanel.validate();
            hide.setVisible(false);
        }
    }

}
