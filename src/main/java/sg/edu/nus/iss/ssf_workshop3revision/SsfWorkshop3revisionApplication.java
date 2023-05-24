package sg.edu.nus.iss.ssf_workshop3revision;

import java.io.File;
import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsfWorkshop3revisionApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(SsfWorkshop3revisionApplication.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsValues = appArgs.getOptionValues("dataDir");

		String dataDir;

		if ((opsValues == null) || (opsValues.get(0) == null)) {
			System.out.println("args[0]: dataDir");
			System.exit(1);
		}

		dataDir = opsValues.get(0);
		
		File newDir = new File(dataDir);

		if (!newDir.exists()) {
			newDir.mkdirs();
		}

		app.run(args);
	}
}
