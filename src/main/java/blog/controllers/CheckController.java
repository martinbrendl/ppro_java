package blog.controllers;

import blog.forms.CheckForm;
import blog.models.Check;
import blog.services.CheckService;
import blog.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CheckController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CheckService checkService;



    @RequestMapping("/check")
    public String showCheckForm(CheckForm checkForm) {
        return "check/index";
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String showCheckForm(@Valid CheckForm checkForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            notificationService.addErrorMessage(
                    "Chyba!");
            return "check/index";
        }

        checkService.create(new Check(checkForm.getName(), checkForm.getConsequences(), checkForm.getPrice(), checkForm.getPeriod()));


        notificationService.addInfoMessage("Kontrola vytvořena!");
        return "redirect:/";
    }
}
