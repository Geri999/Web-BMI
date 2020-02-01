package pl.bci.g73.bmi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calulator {

    private DBofValuesBMI dBofValuesBMI;

    @Autowired  //gdy jest 1 konstruktor to @Autowired nie jest wymagane (od Springa 4.3)
    public Calulator(DBofValuesBMI dBofValuesBMI) {
        this.dBofValuesBMI = dBofValuesBMI;
    }

    public String prepareMessage(double height, double weight, String sex) {
        return dBofValuesBMI.textResultFromBMIList(calculate(height, weight, sex));

    }

    private double calculate(double height, double weight, String sex) {
        //zmienna sex nie jest używana w tym wzorze.
        return (( weight / Math.pow((height/100), 2)));
    }
}
