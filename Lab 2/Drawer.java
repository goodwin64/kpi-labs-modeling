package donchenko;

import javafx.util.Pair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Drawer {

    List<Pair<Double, Integer>> data = new ArrayList<>();

    public void draw(){
        XYSeries generatedSeries = new XYSeries("failures");
        data.forEach(v -> generatedSeries.add(v.getKey(), v.getValue()));
        XYSeriesCollection xyDataset = new XYSeriesCollection();
        xyDataset.addSeries(generatedSeries);
        JFreeChart chart = ChartFactory
                .createXYLineChart("failures", "time", "Count",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);
        JFrame frame =
                new JFrame("failure statistic");
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(1000, 700);
        frame.setLocation(0,0);
        frame.show();
    }
}
