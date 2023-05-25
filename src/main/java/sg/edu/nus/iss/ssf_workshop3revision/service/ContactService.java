package sg.edu.nus.iss.ssf_workshop3revision.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf_workshop3revision.model.Contact;
import sg.edu.nus.iss.ssf_workshop3revision.utility.Utility;

@Service
public class ContactService {

    Utility util;

    public ContactService(Utility util) {
        this.util = util;
    }

    public void saveContact(Contact contact, String dataDir) {
        
        contact.setId(util.generateHexString());
        String filePath = util.createFile(contact.getId(), dataDir);

        if (filePath == null) {
            return;
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(contact.getId());
            bw.newLine();
            bw.write(contact.getName());
            bw.newLine();
            bw.write(contact.getEmail());
            bw.newLine();
            bw.write(contact.getPhoneNumber());
            bw.newLine();
            bw.write(contact.getDateOfBirth().toString());

            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
