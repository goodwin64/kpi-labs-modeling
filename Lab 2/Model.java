package donchenko;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public class Model {
    private double currentTime;
    private int maxQueueSize;
    private int currentQueueSize;
    private double summaryQueueLength;
    private double timeModeling;
    private List<Processor> processors;


    public Model(int maxQ, double timeModeling, Processor... processors) {
        this.processors = asList(processors);
        this.currentTime = 0.0;
        this.maxQueueSize = maxQ;
        this.summaryQueueLength = 0;
        this.timeModeling = timeModeling;
    }

    public void restartWithNewTime(double timeModeling) {
        processors.forEach(Processor::refresh);
        this.currentTime = 0.0;
        this.summaryQueueLength = 0;
        this.currentQueueSize = 0;
        this.timeModeling = timeModeling;
        simulate();
    }

    public void simulate() {
        while (currentTime < timeModeling) {
            Processor processor = findNextProcessorToProcess();
            double prevTime = currentTime;
            if (processor.nextCreationTime < processor.nextProcessingTime) {
                currentTime = processor.nextCreationTime;
                create(processor);
            } else {
                currentTime = processor.nextProcessingTime;
                process(processor);
            }
            summaryQueueLength += currentQueueSize * (currentTime - prevTime);
            processor.summaryLoad += processor.isBusy ? 1 : 0;
//            printInfo(processor);
        }

    }

    void printStatistic() {
        int totalCreated = getTotalCreated();
        int totalProcessed = getTotalProcessed();
        int totalFailed = getTotalFailed();
        System.out.println("\ntotal created = " + totalCreated +
                "\ntotal processed = " + totalProcessed +
                "\ntotal failed = " + totalFailed +
                "\npossibility of failure = " + totalFailed / 1.0 / totalCreated +
                "\naverage queue size = " + summaryQueueLength / timeModeling +
                "\naverage system waiting = " + summaryQueueLength / totalProcessed);
        processors.forEach(e -> System.out.println("average load = " + e.summaryLoad/timeModeling));
    }

    private void printInfo(Processor processor) {
        System.out.println("processor:" + processor.number + " current time = " + currentTime + " isBusy = " + processor.isBusy + " queue size = " + currentQueueSize);
    }

    private void create(Processor processor) {
        processor.nextCreationTime = currentTime + processor.delayCreate.get();
        processor.totalCreated++;
        if (!processor.isBusy) {
            takeToProcess(processor);
        } else {
            if (currentQueueSize < maxQueueSize)
                currentQueueSize++;
            else
                processor.totalFailed++;
        }
    }

    private void process(Processor processor) {
        processor.totalProcessed++;
        if (currentQueueSize > 0) {
            currentQueueSize--;
            takeToProcess(processor);
        } else {
            processor.nextProcessingTime = currentTime+0.1;
            processor.isBusy = false;
        }
    }

    private void takeToProcess(Processor processor) {
        processor.isBusy = true;
        processor.nextProcessingTime = currentTime + processor.delayProcess.get();
    }

    private Processor findNextProcessorToProcess() {
        Comparator<Processor> comparator = (p1, p2) -> {
            Double t1 = Double.min(p1.nextCreationTime, p1.nextProcessingTime);
            Double t2 = Double.min(p2.nextCreationTime, p2.nextProcessingTime);
            return Double.compare(t1, t2);
        };
        return Collections.min(processors, comparator);
    }

    public int getTotalCreated() {
        return processors.stream().mapToInt(e -> e.totalCreated).sum();

    }

    public int getTotalProcessed() {
        return processors.stream().mapToInt(e -> e.totalProcessed).sum();
    }

    public int getTotalFailed() {
        return processors.stream().mapToInt(e -> e.totalFailed).sum();
    }

    public double getTimeModeling() {
        return timeModeling;
    }
}