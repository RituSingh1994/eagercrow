package Keywords;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Keyword {
	public static WebDriver driver;
	public static WebElement element = null;
	public static Alert alert = null;
	private static String Locatortype;
	private static String Locatorvalue;
	public static List<WebElement> elements;

	/**
	 * This method will open the specified browser.
	 * 
	 * @param browserName
	 */
	public static void openBrowser(String browserName) {
		switch (browserName) {
		case "Chrome":
			System.out.println("Opening browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			break;

		case "Firefox":
			System.out.println("Opening browser");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "IE":
			System.out.println("Opening browser");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			break;

		default:
			System.out.println("Invalid browser name entered");
			break;
		}

	}

	/**
	 * This method will open the specified url.
	 * 
	 * @param url
	 */
	public static void openURL(String url) {

		driver.get(url);

	}

	/**
	 * This method will enter text to textbox.
	 * 
	 * @param Locatortype
	 *            Type of the locator like xpath,css selector,etc.
	 * @param Locatorvalue
	 *            Path of the element.
	 * @param TextToEnter
	 *            Text to be entered.
	 */
	public static void enterText(String Locatortype, String Locatorvalue, String TextToEnter) {
		getWebElement(Locatortype, Locatorvalue).sendKeys(TextToEnter);

	}

	/**
	 * This method will get the required web element.
	 * 
	 * @param Locatortype
	 *            Type of the locator like xpath,css selector,etc.
	 * @param Locatorvalue
	 *            Path of the element.
	 * @return Web Element
	 */
	public static WebElement getWebElement(String Locatortype, String Locatorvalue) {
		switch (Locatortype) {
		case "XPATH":
			element = driver.findElement(By.xpath(Locatorvalue));
			break;

		case "CSS Selector":
			element = driver.findElement(By.cssSelector(Locatorvalue));
			break;

		case "ID":
			element = driver.findElement(By.id(Locatorvalue));
			break;

		case "ClassName":
			element = driver.findElement(By.className(Locatorvalue));
			break;

		case "Name":
			element = driver.findElement(By.name(Locatorvalue));
			break;

		case "LinkText":
			element = driver.findElement(By.linkText(Locatorvalue));
			break;

		case "Partial_Link_Text":
			element = driver.findElement(By.partialLinkText(Locatorvalue));
			break;

		case "TagName":
			element = driver.findElement(By.tagName(Locatorvalue));
			break;

		default:
			System.out.println(
					"Invalid Locator Type entered. LocatorType shoud be Xpath,CSS Selector,ID,TagName,ClassName,Name,LinkText,Partial_Link_Text.");
			break;
		}
		return element;

	}

	/**
	 * This method will allow clicking on a particular element.
	 * 
	 * @param Locatortype
	 *            Locatortype Type of the locator like xpath,css selector,etc.
	 * @param Locatorvalue
	 *            Path of the element.
	 */
	public static void clickOnElement(String Locatortype, String Locatorvalue) {
		getWebElement(Locatortype, Locatorvalue).click();

	}

	/**
	 * This method will accept alert.
	 */
	public static void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();

	}

	/**
	 * This method will reject the alert.
	 */
	public static void rejectAlert() {
		alert = driver.switchTo().alert();
		alert.dismiss();

	}

	/**
	 * This method will enter text to the alert textbox.
	 * 
	 * @param TextToEnter
	 *            text to be entered.
	 */
	public static void enterTextToAlert(String TextToEnter) {
		alert = driver.switchTo().alert();
		alert.sendKeys(TextToEnter);

	}

	/**
	 * This method will maximize the browser window.
	 */
	public static void maximizeBrowser() {
		driver.manage().window().maximize();

	}

	/**
	 * This method will close the current opened window.
	 */
	public static void closeBrowser() {
		driver.close();

	}

	/**
	 * This method will close all the windows that are opened.
	 */
	public static void closeAllbrowser() {
		driver.quit();

	}

	/**
	 * This method will provide implicit wait for every element.
	 * 
	 * @param time
	 * @param unit
	 */
	public static void implicitwait(int time, String unit) {
		switch (unit) {

		case "MILISECONDS":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
			break;

		case "NANOSECONDS":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.NANOSECONDS);
			break;

		case "MICROSECONDS":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.MICROSECONDS);
			break;

		case "SECONDS":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
			break;

		case "MINUTES":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.MINUTES);
			break;

		case "HOURS":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.HOURS);
			break;

		case "DAYS":
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.DAYS);
			break;
		}

	}

	/**
	 * This method will switch to a particular frame.
	 * 
	 * @param element
	 *            Frame to be switched upon.
	 */
	public static void switchtoFramebyElement(WebElement element) {
		element = getWebElement(Locatortype, Locatorvalue);
		driver.switchTo().frame(element);

	}

	/**
	 * This method will switch to a particular frame.
	 * 
	 * @param index
	 */
	public static void switctoFramebyIndex(int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method will switch to the required window.
	 * 
	 * @param windowtitle
	 *            title of the window to be switched upon.
	 */
	public static void switchToWindow(String windowtitle) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window).getTitle().equals(windowtitle);
		}

	}

	/**
	 * This method will take screenshot of the screen.
	 * 
	 * @param pathtosaveScreenshot
	 * @throws IOException
	 */
	public static void screenshot(String pathtosaveScreenshot) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourcefile, new File(pathtosaveScreenshot));
	}

	public static void forward() {
		driver.navigate().forward();
	}

	public static void backward() {
		driver.navigate().back();
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	@DataProvider
	public static Object[][] excelFileReading(String filepath) {
		Object[][] emails = null;
		try {
			FileInputStream file = new FileInputStream(filepath);
			XSSFWorkbook book = new XSSFWorkbook(file);
			XSSFSheet sheet = book.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			XSSFRow row = sheet.getRow(0);
			int columns = row.getLastCellNum();
			emails = new Object[rows][columns];
			for (int i = 0; i < rows; i++) {
				row = sheet.getRow(i);

				for (int j = 0; j < columns; j++) {

					XSSFCell cell = row.getCell(j);

					switch (cell.getCellType()) {
					case 0:
						emails[i][j] = cell.getNumericCellValue();
						break;

					case 1:
						emails[i][j] = cell.getStringCellValue();
						break;

					case 2:
						emails[i][j] = cell.getCellFormula();
						break;

					case 3:
						emails[i][j] = "";
						break;

					case 4:
						emails[i][j] = cell.getBooleanCellValue();
						break;

					case 5:
						emails[i][j] = cell.getErrorCellValue();
						break;

					default:
						System.out.println("Invalid data");
						break;
					}

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emails;

	}
	
	
	/**
	 * This method will take screenshot of full page.
	 * @param fileFormat Format of the image file like jpeg,jpg,png,etc.
	 * @param filepath Path of the file where it should be stored.
	 */
	public static void fullPageScreenshot(String fileFormat,String filepath) {
		AShot ashot=new AShot();
		Screenshot screenshot=ashot.shootingStrategy(ShootingStrategies.viewportPasting(3000)).takeScreenshot(driver);
		BufferedImage image=screenshot.getImage();
		try {
			ImageIO.write(image, fileFormat, new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	

	}

	public static void failedTestCaseScreenshot() {
		
		String dateFormat=new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		

		try {
			FileUtils.copyFile(source, new File("C:\\Users\\NRK\\javapract\\KeywordDrivenFramework\\Screenshots\\screenshot_"+dateFormat+".png"));
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	
		
		
	}
	
	public static void linkValidation() {
		String url="";
		String homepage="https://www.naukri.com/";
		HttpURLConnection huc=null;
		int responsecode=200;
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.get(homepage);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> links=driver.findElementsByCssSelector("div>a");
		System.out.println(links.size());
		Iterator<WebElement> itr=links.iterator();
		while(itr.hasNext()) {
			url=itr.next().getAttribute("href");
			System.out.println(url);
			
			if(url==null||url.isEmpty()) {
				System.out.println("url is not configured or is empty");
				continue;
			}
			if(!url.startsWith(homepage)) {
				System.out.println("url belongs to another domain,skipping it");
				continue;
			}
			try {
				huc=(HttpURLConnection)(new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				responsecode=huc.getResponseCode();
				if(responsecode>=400) {
					System.out.println(url+" is a broken link");
				}
				else {
					System.out.println(url+" is a valid link");
				}
			}
			catch(MalformedURLException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		driver.quit();
		

	}
	
	
	public static void clickOnElementbyJS(String Locatortype, String Locatorvalue) {
		WebElement element=getWebElement(Locatortype, Locatorvalue);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		element.click();

	}
	
	public static List<WebElement> getWebElements(String LocatorType, String LocatorValue) {
		
		
		switch (LocatorType) {
		case "XPATH":
			elements = driver.findElements(By.xpath(LocatorValue));
			break;
		case "ID":
			elements = driver.findElements(By.id(LocatorValue));
			break;
		case "CLASSNAME":
			elements = driver.findElements(By.className(LocatorValue));
			break;
		case "NAME":
			elements = driver.findElements(By.name(LocatorValue));
			break;
		case "CSS":
			elements = driver.findElements(By.cssSelector(LocatorValue));
			break;
		case "TAGNAME":
			elements = driver.findElements(By.tagName(LocatorValue));
			break;
		case "LINKTEXT":
			elements = driver.findElements(By.linkText(LocatorValue));
			break;
		case "PARTAIL_LINK_TEXT":
			elements = driver.findElements(By.partialLinkText(LocatorValue));
			break;
		default:
			System.err.println("Please enter valid LocatorType :" + LocatorType
					+ "Expected: CSS,XPATH,ID,NAME,CLASSNAME,TAGNAME,LINTEXT,PARTIAL_LINK_TEXT");

		}
		
		
		return elements;
		

	}

	public static void moveToElement(String LocatorType,String LocatorValue) {
		WebElement element=getWebElement(LocatorType, LocatorValue);
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();

	}
}
