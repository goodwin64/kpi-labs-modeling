package donchenko.distribution;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

public class ExpDistribution implements Supplier<Double>{
    private final double mean;

    public ExpDistribution(double mean) {
        this.mean = mean;
    }

    public Double get(){
        double a = Math.random();
        if(a==0.0){
            a=Double.MIN_VALUE;
        }
       return  -mean * Math.log(a);
    }
}
