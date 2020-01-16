package Selenium.Figosoft.testing.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject {

	Properties p = new Properties();

	/**
	 * return the object repository
	 * @return
	 * @throws IOException
	 */
	public Properties getObjectRepository() throws IOException {
		// Read object repository file
		InputStream stream = new FileInputStream(
				new File(".\\Config\\prop.properties"));
		// load all objects
		p.load(stream);
		return p;
	}
}