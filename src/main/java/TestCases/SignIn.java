package TestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Keywords.Keyword;

public class SignIn {
 
	public static void signInWithEmail() {
		

	}
	
	@BeforeTest
	public static void signinWithGoogle() {
		Keyword.openBrowser("Chrome");
		Keyword.openURL("https://eagercrow.com/Home");
		Keyword.implicitwait(10, "SECONDS");
		Keyword.maximizeBrowser();
		Keyword.clickOnElementbyJS("XPATH", "//div[@class=\"header-icons\"]/a[contains(text(),'Sign In')]");
	
		Keyword.clickOnElement("XPATH", "//button[@class=\"firebaseui-idp-button mdl-button mdl-js-button mdl-button--raised firebaseui-idp-google firebaseui-id-idp-button\"]");
		Keyword.switchToWindow("Sign in - Google Accounts");
		Keyword.enterText("XPATH", "//input[@type=\"email\"]", "rosesinghritu");
		Keyword.clickOnElement("XPATH", "//span[contains(text(),'Next')]");
		Keyword.enterText("XPATH", "//input[@type=\"password\"]", "");
		Keyword.clickOnElement("XPATH", "//span[contains(text(),'Next')]");

	}
}
