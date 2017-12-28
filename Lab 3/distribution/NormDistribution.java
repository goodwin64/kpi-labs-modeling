package donchenko.distribution;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Supplier;

public class NormDistribution implements Supplier<Double> {
    private final double mean;
    private final double deviation;
    private final Random r;

    public NormDistribution(double mean, double deviation) {
        this.mean = mean;
        this.deviation = deviation;
        r = new Random();
    }

    public Double get(){
        return mean + deviation * r.nextGaussian();
    }
}
