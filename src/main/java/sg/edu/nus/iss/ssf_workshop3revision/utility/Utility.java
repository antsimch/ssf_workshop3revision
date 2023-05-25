package sg.edu.nus.iss.ssf_workshop3revision.utility;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Component;

import sg.edu.nus.iss.ssf_workshop3revision.model.Constants;

@Component
public class Utility {
  
    public String generateHexString() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        while(sb.length() < Constants.HEX_STRING_LENGTH) {
            sb.append(Integer.toHexString(random.nextInt(Constants.HEX_MAX_VALUE)));
        }

        return sb.toString();
    }

    public void createFile(String name, String dataDir) {

        String dirPathFileName = dataDir + File.separator + name;
        File newFile = new File(dirPathFileName);
        
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
