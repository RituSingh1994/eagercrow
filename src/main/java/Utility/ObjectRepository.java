package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ObjectRepository {

	protected String getLocator(String l) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("KeywordDrivenFramework/src/main/java/Config/ObjectRepository.properties");
		prop.load(fis);
		String locator = prop.getProperty(l);
		return locator;

	}
}
