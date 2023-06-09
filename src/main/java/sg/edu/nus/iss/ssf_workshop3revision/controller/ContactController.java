package sg.edu.nus.iss.ssf_workshop3revision.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.ssf_workshop3revision.model.Contact;
import sg.edu.nus.iss.ssf_workshop3revision.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    ContactService service;

    @Value("${data.dir}") 
    private String dataDir;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/home")
    public String getLandingPage() {
        return "home";
    }

    @GetMapping("/new")
    public String getContactForm(Model model) {

        model.addAttribute("contact", new Contact());
        return "form";
    }

    @PostMapping(consumes = "application/x-www-form-urlencoded", path = "/save")
    public String createContact(@Valid Contact contact, BindingResult result, Model model) {

        System.out.println("Name: " + contact.getName());
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Phone Number: " + contact.getPhoneNumber());
        System.out.println("Date of Birth: " + contact.getDateOfBirth());

        if (result.hasErrors()) {
            return "form";
        }

        service.saveContact(contact, dataDir);
        model.addAttribute("successMessage", "Contact is successfully saved " + HttpStatus.CREATED + ".");

        return "contact";
    }

    @GetMapping("/{contactId}")
    public String getContactById(Model model, @PathVariable String contactId) {
        
        Contact contact = service.getContact(contactId, dataDir);

        if (contact == null) {
            model.addAttribute("errorMessage", "Contact not found " + HttpStatus.NOT_FOUND + ".");
            return "home";
        }

        model.addAttribute("contact", contact);
        return "contact";
    }

    @GetMapping("/list")
    public String getContactList(Model model) {

        model.addAttribute("contacts", service.getAllContacts(dataDir));
        return "contacts";
    }
}
