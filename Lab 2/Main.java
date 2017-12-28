package donchenko;

import javafx.util.Pair;

public class Main {

    public static void main(String[] args) {
        Processor processor1 = new Processor(1, () -> RandFunc.Exp(2), () -> RandFunc.Exp(5));
        Processor processor2 = new Processor(2, () -> Double.MAX_VALUE, () -> RandFunc.Exp(5));
        Model model = new Model(20,100000, processor1,processor2);
        model.simulate();
        model.printStatistic();
//        drawStatistic(model);
    }

    private static void drawStatistic(Model model){
        Drawer drawer = new Drawer();
        for (double i = 100; i <10000 ; i+=100) {
            model.restartWithNewTime(i);
            drawer.data.add(new Pair<>(i,model.getTotalFailed()));
        }
        drawer.draw();
    }
}

