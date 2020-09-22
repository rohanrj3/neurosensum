package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.BrowserUtility;
import utility.ReadTestData;
import utility.WebUtility;

public class CompareWeather {

	String Browser;
	String tcID;
	ReadTestData testData;
	WebDriver driver;
	WebUtility webutil;
	String url;
	public CompareWeather(String browser,String testCaseID) {
		this.Browser = browser;
		this.tcID=testCaseID;
		this.testData= new ReadTestData();
	}

	@BeforeMethod
	public void fntestSetup() throws IOException {
		
		this.url = testData.getGlobalData("url");
		//driver=BrowserUtility.fncLaunchBrowser(Browser);
		//webutil.openURL(driver, url);
	}

  @Test
  public void f() {
	  System.out.println("Printing Test Case : " + this.tcID);
	  System.out.println("url : " + url);
	  System.out.println("Browser : " + Browser);
  }
}
