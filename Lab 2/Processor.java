package donchenko;

import java.util.function.Supplier;

public class Processor {
    int number;
    double nextCreationTime;
    double nextProcessingTime;
    boolean isBusy;
    Supplier<Double> delayCreate;
    Supplier<Double> delayProcess;
    int totalCreated;
    int totalProcessed;
    int totalFailed;
    double summaryLoad;

    public Processor(int number, Supplier<Double> delayCreate, Supplier<Double> delayProcess) {
        this.number = number;
        this.nextCreationTime = 0.0;
        this.nextProcessingTime = Double.MAX_VALUE;
        this.delayCreate = delayCreate;
        this.delayProcess = delayProcess;
    }

    public void refresh(){
        this.nextCreationTime = 0.0;
        this.nextProcessingTime = Double.MAX_VALUE;
        isBusy = false;
        totalCreated = 0;
        totalProcessed = 0;
        totalFailed = 0;
    }
}
