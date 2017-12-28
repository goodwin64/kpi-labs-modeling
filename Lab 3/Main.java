package donchenko;

import donchenko.distribution.ExpDistribution;
import donchenko.elements.CreateElement;
import donchenko.elements.ProcessElement;

public class Main {

    public static void main(String[] args) {
//        CreateElement c = new CreateElement("Creator1", new ExpDistribution(2.0));
//        ProcessElement p = new ProcessElement("Processor1", new ExpDistribution(1.0), 5);
//        c.addNextElement(p);
//        Model model = new Model(c, p);
//        model.simulate(1000.0);


        CreateElement c = new CreateElement("Creator1", new ExpDistribution(1));
        ProcessElement p1 = new ProcessElement("Processor1", new ExpDistribution(2.0), 5);
        ProcessElement p2 = new ProcessElement("Processor2", new ExpDistribution(1.0), 5);
        ProcessElement p3 = new ProcessElement("Processor3", new ExpDistribution(2.0), 5);
        c.addNextElement(p1);
        p1.addNextElement(p2);
        p2.addNextElement(p3);
        Model model = new Model(c, p1, p2, p3);
        model.simulate(1000.0);

//        CreateElement c = new CreateElement("Creator1", new ExpDistribution(2.0));
//        ProcessElement p1 = new ProcessElement("Processor1", new ExpDistribution(1.0), 5);
//        ProcessElement p2 = new ProcessElement("Processor2", new ExpDistribution(1.0), 5);
//        ProcessElement p3 = new ProcessElement("Processor3", new ExpDistribution(1.0), 5);
//        p3.setReturnToPrev(true);
//        c.addNextElement(p1);
//        c.addNextElement(p2);
//        p1.addNextElement(p3);
//        Model model = new Model(c, p1, p2, p3);
//        model.simulate(1000.0);

    }
}
