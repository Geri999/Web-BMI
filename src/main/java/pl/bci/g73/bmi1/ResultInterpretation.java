package pl.bci.g73.bmi1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultInterpretation {

    private final List<String> bmiList = new ArrayList<>();

    private ResultInterpretation() {
        bmiList.add("0,16,wygłodzenie");
        bmiList.add("16,17,wychudzenie");
        bmiList.add("17,18.5,niedowaga");
        bmiList.add("18.5,25,wartość prawidłowa");
        bmiList.add("25,30,nadwaga");
        bmiList.add("30,35,I stopień otyłości");
        bmiList.add("35,40,II stopień otyłości");
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

