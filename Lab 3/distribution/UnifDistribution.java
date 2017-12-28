package donchenko.distribution;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;


public class UnifDistribution implements Supplier<Double> {
    private final double min;
    private final double max;

    public UnifDistribution(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public Double get(){
        return min + Math.random()*(max-min);
    }
}
