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

    public void createFile(String fileName, String dataDir) {

        File newFile = new File(getFilePath(fileName, dataDir));
        
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFilePath(String fileName, String dataDir) {
        return dataDir + File.separator + fileName;
    }
}
