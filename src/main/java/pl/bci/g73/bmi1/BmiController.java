package pl.bci.g73.bmi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BmiController {

    private Calulator calulator;
    private double height;
    private double weight;
    private String sex;
    private String name;

    @Autowired
    public BmiController(Calulator calulator) {
        this.calulator = calulator;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String stronaStartowa() {
        return "BMIMainPage.html";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String stronaStartowa1() {
        return "redirect:";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String loginAction(@RequestParam String heightGP, @RequestParam String weightGP,
                              @RequestParam String sexGP, @RequestParam String nameGP, Model model) {
        height = Double.parseDouble(heightGP);
        weight = Double.parseDouble(weightGP);
        sex = sexGP;
        name = nameGP;

        model.addAttribute("komunikat", "\""+calulator.prepareMessage(height, weight, sex)+"\"");
        model.addAttribute("nameGP", name);

        return "BMIResult.html";
    }
}
