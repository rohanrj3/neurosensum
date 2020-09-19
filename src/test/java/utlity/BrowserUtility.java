package utlity;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserUtility {

	public static WebDriver fncLaunchBrowser(String browser)
	{
		WebDriver driver = null;
		Logger log = Logger.getLogger("manualLogger");	
		try {	
		String path;
		System.out.println(browser);
		String drivpath= System.getProperty("user.dir")+"\\src\\test\\resources\\";
		//accepts string value in brsr="firefox, chrome, ie";
		log.info("************************************************");
		log.info("FUNCTION:   fncLaunchBrowser called");
		//returns driver of passed browser
		if(browser.equalsIgnoreCase("chrome")){
			log.info("Test to run in Chrome Browser");
			System.setProperty("webdriver.chrome.driver", drivpath + "drivers\\chromedriver.exe");
			log.info("Set Chrome Driver Path as :"+ drivpath + "drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("start-maximized");
//			DesiredCapabilities handlSSLErr = DesiredCapabilities.chrome ();       
//			handlSSLErr.setCapability (CapabilityType.ACCEPT_SSL_CERTS, options);
			driver = new ChromeDriver(options);
		}
		else if (browser.equalsIgnoreCase("firefox")){
			log.info("Test to run in firefox Browser");
			log.info("Verify Firefox binary at location : C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary firefoxBinary;
			File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			if(pathBinary.exists()) {
				firefoxBinary = new FirefoxBinary(pathBinary);
				log.info("FireFix binary exist at location: C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			}
			else {
				log.info("FireFix binary do not exist at location: C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				pathBinary = new File("C:\\Users\\ " + System.getProperty("user.name") +"\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
				if(pathBinary.exists()) {
					firefoxBinary = new FirefoxBinary(pathBinary);
					log.info("FireFix binary exist at location: C:\\Users\\" + System.getProperty("user.name") +"\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
					log.info("Set FireFix binary path as : C:\\Users\\" + System.getProperty("user.name") +"\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
				}else {
					log.info("FireFix binary do not exist at location: C:\\Users\\" + System.getProperty("user.name") +"\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
					pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
					if(pathBinary.exists()) {
						firefoxBinary = new FirefoxBinary(pathBinary);
						log.info("Set FireFix binary path as : C:\\Program Files\\Mozilla Firefox\\firefox.exe");
						log.info("FireFix binary exist at location: C:\\Program Files\\Mozilla Firefox\\firefox.exe");
					}else {
						log.error("Firefox binary do not exist. Please install firefox");
						return null;
					}
				}
			}
			firefoxBinary = new FirefoxBinary(pathBinary);   
			DesiredCapabilities desired = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
			System.setProperty("webdriver.gecko.driver",drivpath + "drivers\\geckodriver.exe");
			log.info("Set Firefox Driver Path as : "+drivpath + "drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}catch(Exception e) {
			System.out.println("Exception in Function : fncLaunchBrowser");
			log.info("Exception in Function : fncLaunchBrowser");
			log.error(e);
		}
		return driver;
	}
	public static void fncCloseBrowser(WebDriver driver) {
		Logger log = Logger.getLogger("manualLogger");
		log.info("fncCloseBrowser function called");
		driver.quit();
		log.info("Close Browser");
	}
	
	
}
