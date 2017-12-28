package donchenko.elements;

import java.util.Random;
import java.util.function.Supplier;

public class ProcessElement extends Element {

    private int queue;
    private int maxQueue;
    private int failure;
    private double meanQueue;
    private double averageLoad;
    private boolean isBusy;
    private boolean returnToPrev;
    private Element prevElem;

    public ProcessElement(String name, Supplier<Double> distribution, int maxQueue) {
        super(name, distribution);
        this.maxQueue = maxQueue;
        queue = 0;
        meanQueue = 0.0;
    }

    @Override
    public void inAct(Element elem) {
        prevElem = elem;
        if (!isBusy) {
            isBusy = true;
            nextTime = currTime + getDelay();
        } else {
            if (queue < maxQueue) {
                queue++;
            } else {
                failure++;
            }
        }
    }

    @Override
    public void outAct() {
        quantity++;
        if (queue > 0) {
            queue--;
            isBusy = true;
            nextTime = currTime + getDelay();
        } else {
            isBusy = false;
            nextTime = Double.MAX_VALUE;
        }
        Element next = nextProcessor();
        if (next != null) {
            next.inAct(this);
        }
    }

    @Override
    public void printInfo() {
        System.out.println(name + " isBusy = " + isBusy +
                ", quantity = " + quantity +
                ", nextTime = " + nextTime +
                ", failure = " + failure);
    }

    @Override
    public void doStatistics(double delta) {
        meanQueue += queue * delta;
        averageLoad += delta * (isBusy ? 1 : 0);
    }

    public Element nextProcessor() {
        Element nextElem = getNextElement();
        if (prevElem != null && returnToPrev) {
            Random random = new Random();
            if (random.nextInt(1000) % 3 == 0) {
                return prevElem;
            }
        }
        return nextElem;
    }

    @Override
    public void printResult(double modelingTime) {
        System.out.println(name + "  quantity = " + quantity +
                ", failure = " + failure +
                ", queue = " + (queue + (isBusy ? 1 : 0)) +
                ", average queue = " + meanQueue / modelingTime +
                ", average load = " + averageLoad / modelingTime);
    }

    public void setReturnToPrev(boolean returnToPrev) {
        this.returnToPrev = returnToPrev;
    }
}
