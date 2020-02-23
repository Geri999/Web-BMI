package pl.bci.g73.bmi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BmiController {

    private Calulator calulator;
    private ResultInterpretation resultInterpretation;
    private IO io;

    private double height;
    private double weight;
    private double age;
    private String sex;
    private String name;

    @Autowired
    public BmiController(Calulator calulator, ResultInterpretation resultInterpretation, IO io) {
        this.calulator = calulator;
        this.resultInterpretation = resultInterpretation;
        this.io = io;
        io.loadAndSetLastMainIDfromHDD();
    }

    //--------------------------------------------------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String stronaStartowa() {
        return "BMIMainPage.html";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String stronaStartowa1() {
        return "redirect:";
    }

    @RequestMapping(value = "/BMIMainPage.html", method = RequestMethod.GET)
    public String stronaStartowa2() {
        return "redirect:";
    }


    @RequestMapping(value = "/BMITheory.html", method = RequestMethod.GET)
    public String stronaBMITheory() {
        return "BMITheory.html";
    }


    //--------------------------------------------------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String WebCaclulation(@RequestParam String nameGP, @RequestParam String sexGP,
                                 @RequestParam String ageGP, @RequestParam String weightGP,
                                 @RequestParam String heightGP, Model model) {
        name = nameGP;
        sex = sexGP;
        age = Double.parseDouble(ageGP);
        weight = Double.parseDouble(weightGP);
        height = Double.parseDouble(heightGP);

        MeasurementRecord.setIDmain(MeasurementRecord.getIDmain() + 1);
        MeasurementRecord measurementRecord = new MeasurementRecord(MeasurementRecord.getIDmain(), name, sex, age, weight, height); //todo połącz w 1 linie
        io.save(measurementRecord);

        Double bmi = calulator.calculate(sex, age, weight, height);
        String resultDescriptionFromBMI = resultInterpretation.textResultFromBMIList(bmi);

        model.addAttribute("line1GP", name + ", Twóje BMI wynosi: " + Math.round(bmi));
        model.addAttribute("line2GP", "Stan Twojego organizmu to: \"" + resultDescriptionFromBMI + "\".");

        return "BMIResult";
    }

    //--------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/BMIFind.html", method = RequestMethod.GET)
    public String findByID() {
        return "BMIFind.html";
    }


    @RequestMapping(value = "/BMIFind.html", method = RequestMethod.POST)
    public String findByID(@RequestParam String idRecordGP, Model model) {
        int idRecord = Integer.parseInt(idRecordGP);
        io.findRecordOnHDDByID(idRecord);
        model.addAttribute("finded", io.findRecordOnHDDByID(idRecord).toString());
        return "BMIFind.html";
    }


    //--------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/BMIShowAllFind.html", method = RequestMethod.GET)
    public String stronaShowAll() {
        return "BMIShowAllFind.html";
    }

    @RequestMapping(value = "/BMIShowAllFind.html", method = RequestMethod.POST)
    public String stronaShowAll(@RequestParam String actionGP, Model model) {
        List list = io.loadWholeDBfromHDD();
        
        System.out.println(actionGP);
        model.addAttribute("listaGP", list);
        return "BMIShowAllFind.html";
    }


}
