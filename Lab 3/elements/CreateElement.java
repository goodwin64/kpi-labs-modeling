package donchenko.elements;

import java.util.function.Supplier;

public class CreateElement extends Element {

    public CreateElement(String name, Supplier<Double> distribution) {
        super(name, distribution);
    }

    @Override
    public void outAct() {
        quantity++;
        nextTime = currTime + getDelay();
        if (hasNextElement()) {
            getNextElement().inAct(this);
        }
    }

    @Override
    public void printInfo() {
        System.out.println(name +
                " quantity = " + quantity +
                " nextTime= " + nextTime);
    }
}
