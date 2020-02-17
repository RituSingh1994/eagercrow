package TestCases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Keywords.Keyword;
import Utility.ObjectRepository;

@Listeners(Utility.MyListeners.class)
public class EagerCrow extends ObjectRepository{
public static Logger log=Logger.getLogger(EagerCrow.class);

@Test
public void BeanBag() throws IOException {
	Keyword.openBrowser("Chrome");
	log.info("Browser opened");
	
	Keyword.openURL("https://eagercrow.com/Home");
	log.info("Web Application Launched");
	
	Keyword.implicitwait(10,"SECONDS");
	log.info("Implicit wait applied on the driver for 10 seconds");
	
	Keyword.clickOnElement("XPATH","//div[@class=\"row d-flex justify-content-center\"][1]");
	
	Keyword.clickOnElement("XPATH", "//a[contains(text(),\"Departments\")]");
	log.info("Click operation performed on Department link");
	
	String actual=Keyword.getWebElement("XPATH", "//strong[contains(text(),'Bean Bags')]").getText();
	Assert.assertEquals("bean bag", actual);
	
	Keyword.closeBrowser();
	log.info("Browser closed");
}

@Test
public void Signin() {
	Keyword.openBrowser("Chrome");
	log.info("Browser opened");
	
	Keyword.openURL("https://eagercrow.com/Home");
	log.info("Web Application launched");
	
	Keyword.implicitwait(10, "SECONDS");
	log.info("Implicit wait applied on the driver for 10 seconds");
	
	
	Keyword.clickOnElement("XPATH", "//div[@class=\"btn-show-menu\"]/a[contains(text(),'Sign In')]");
	log.info("Click operation performed on SignIn button");
	
	
}
}
