package sg.edu.nus.iss.ssf_workshop3revision.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        util.createFile(contact.getId(), dataDir);

        try {
            FileWriter fw = new FileWriter(util.getFilePath(contact.getId(), dataDir));
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

    public Contact getContact(String contactId, String dataDir) {
        
        File newFile = new File(util.getFilePath(contactId, dataDir));

        if (!newFile.exists()) {
            return null;
        }

        Contact contact = new Contact();

        try {
            FileReader fr = new FileReader(newFile);
            BufferedReader br = new BufferedReader(fr);

            contact.setId(br.readLine());
            contact.setName(br.readLine());
            contact.setEmail(br.readLine());
            contact.setPhoneNumber(br.readLine());
            contact.setDateOfBirth(LocalDate.parse(br.readLine()));

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contact;
    }

    public List<Contact> getAllContacts(String dataDir) {

        File directory = new File(dataDir);
        File[] files = directory.listFiles();
        List<Contact> contacts = new ArrayList<>();

        for (File file : files) {
            Contact contact = getContact(file.getName(), dataDir);
            contacts.add(contact);
        }

        return contacts;
    }
}
