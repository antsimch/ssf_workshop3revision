package sg.edu.nus.iss.ssf_workshop3revision.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressBookController {
    
    @GetMapping("/home")
    public String getLandingPage() {
        return "home";
    }
}
