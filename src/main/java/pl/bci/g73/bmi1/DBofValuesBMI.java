package pl.bci.g73.bmi1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DBofValuesBMI {

    private final List<String> bmiList = new ArrayList<>();

    private DBofValuesBMI() {
        bmiList.add("0,16,wygłodzenie");
        bmiList.add("16,16.99,wychudzenie");
        bmiList.add("17,18.49,niedowaga");
        bmiList.add("18.5,24.99,wartość prawidłowa");
        bmiList.add("25,29.99,nadwaga");
        bmiList.add("30,34.99,I stopień otyłości");
        bmiList.add("35,39.99,II stopień otyłości");
        bmiList.add("40,1000,otyłość skrajna");
    }

    public String textResultFromBMIList(Double result) {
        String message;
        for (String s : bmiList) {
            String[] splitedRecord = s.split(",");
            Double min = Double.parseDouble(splitedRecord[0]);
            Double max = Double.parseDouble(splitedRecord[1]);
            message = splitedRecord[2];
            if ((result >= min) & (result < max)) return message;
        }
        return "otyłość skrajna";
    }


}

