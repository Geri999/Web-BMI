package pl.bci.g73.bmi1;

import org.springframework.stereotype.Component;

@Component
public class Calulator {

    public double calculate(String sex, double age, double weight, double height) {
        //zmienna sex i age nie jest używana w tym wzorze. Ale można go o nie rozszerzyć
        return (( weight / Math.pow((height/100), 2)));
    }
}
