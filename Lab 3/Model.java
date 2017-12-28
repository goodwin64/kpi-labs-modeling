package donchenko;

import donchenko.elements.Element;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public class Model {

    private List<Element> elements;
    private double nextTime;
    private double currTime;

    public Model(Element... elements) {
        this.elements = asList(elements);
        nextTime = 0.0;
        currTime = nextTime;
    }


    public void simulate(double time) {
        while (currTime < time) {
            Element nextElement = Collections.min(elements, Comparator.comparingDouble(Element::getNextTime));
            nextTime = nextElement.getNextTime();
            elements.forEach(e->e.doStatistics(nextTime - currTime));
            currTime = nextTime;
            for (Element e : elements) {
                e.setCurrTime(currTime);
            }
            nextElement.outAct();
            for (Element e : elements) {
                if (e.getNextTime() == currTime) {
                    e.outAct();
                }
            }
            printInfo();
        }
        printResult(time);
    }



    public void printInfo() {
        for (Element e : elements) {
            e.printInfo();
        }
        System.out.println("-------------------------------------------------------");
    }

    public void printResult(double time) {
        System.out.println("\n-------------RESULTS-------------");
        for (Element e : elements) {
            e.printResult(time);
        }
    }

}