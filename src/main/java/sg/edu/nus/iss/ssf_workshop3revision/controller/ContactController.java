package sg.edu.nus.iss.ssf_workshop3revision.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.ssf_workshop3revision.model.Contact;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    
    @GetMapping("/home")
    public String getLandingPage() {

        return "home";
    }

    @GetMapping("/new")
    public String showForm(Model model) {

        Contact contact = new Contact();

        model.addAttribute("contact", contact);

        return "form";
    }


    @PostMapping(consumes = "application/x-www-form-urlencoded", path = "/save")
    public String createContact(@Valid Contact contact, BindingResult result, Model model) {

        System.out.println("Name: " + contact.getName());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Tel No.: " + contact.getPhoneNumber());

        if (result.hasErrors()) {

            return "form";
        }

        return "home";
    }
}
