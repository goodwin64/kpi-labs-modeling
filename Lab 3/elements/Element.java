package donchenko.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public abstract class Element {
    String name;
    double nextTime;
    int quantity;
    double currTime;
    List<Element> nextElements;

    private Supplier<Double> distribution;

    public Element(String name, Supplier<Double> distribution) {
        this.nextTime = 0.0;
        this.distribution = distribution;
        this.currTime = nextTime;
        this.nextElements = new ArrayList<>();
        this.name = name;
    }

    public double getDelay() {
        return distribution.get();
    }

    public void inAct(Element elem) {

    }

    public abstract void outAct();

    public void printResult(double modelingTime) {
        System.out.println(name + "  quantity = " + quantity);
    }

    public abstract void printInfo();

    public void doStatistics(double delta) {

    }

    public double getNextTime() {
        return nextTime;
    }

    public void setNextTime(double nextTime) {
        this.nextTime = nextTime;
    }

    public double getCurrTime() {
        return currTime;
    }

    public void setCurrTime(double currTime) {
        this.currTime = currTime;
    }

    public Element getNextElement() {
        if(nextElements.isEmpty()){
            return null;
        }
        Random r = new Random();
        int next = r.nextInt(nextElements.size());
        return nextElements.get(next);
    }

    public boolean hasNextElement(){
        return !nextElements.isEmpty();
    }

    public void addNextElement(Element nextElement) {
        nextElements.add(nextElement);
    }
}
