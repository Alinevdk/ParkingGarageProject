package nl.hanze.project.view;

import nl.hanze.project.controller.Simulator;

import javax.swing.*;

import static java.lang.Math.floor;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

import java.awt.*;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;


/**
 *
 * Credits:
 *
 * https://gist.github.com/obatiuk/ca0eb94b1d31310f8c648f506f96e0f8
 *
 *
 */
public class HistogramView extends JPanel
{
    private static final String[] CHARACTERS = { "▁", "▂", "▃", "▅", "▆", "▇" };



    private List<Double> data;

    public HistogramView(List<Double> data)
    {
        this.data = data;
    }

    private static Integer findBin(Double datum, int bins, double min, double max)
    {
        final double binWidth = (max - min) / bins;
        if ( datum >= max )
        {
            return bins - 1;
        }
        else if ( datum <= min )
        {
            return 0;
        }
        return (int)floor((datum - min) / binWidth);
    }

    public Map<Integer, Integer> histogram(int bins)
    {
        final DoubleSummaryStatistics statistics = this.data.stream()
                .mapToDouble(x -> x)
                .summaryStatistics();

        final double max = statistics.getMax();
        final double min = statistics.getMin();

        // Make sure that all bins are initialized
        final Map<Integer, Integer> histogram = range(0, bins).boxed()
                .collect(toMap(identity(), x -> 0));

        histogram.putAll(this.data.stream()
                .map(x -> x)
                .collect(groupingBy(x -> findBin(x, bins, min, max),
                        mapping(x -> 1, summingInt(x -> x)))));

        return histogram;
    }

    public String toString(int bins)
    {
        final Map<Integer, Integer> frequencies = this.histogram(bins);
        final int maxFrequency = frequencies.values().stream()
                .mapToInt(x -> x).max().orElse(0);

        return frequencies.values().stream()
                .mapToDouble(x -> x / (double)maxFrequency)
                .mapToInt(x -> findBin(x, CHARACTERS.length, 0, 1))
                .mapToObj(x -> CHARACTERS[x])
                .collect(joining());
    }

    public static HistogramView from(List<Double> data)
    {
        return new HistogramView(data);
    }

}