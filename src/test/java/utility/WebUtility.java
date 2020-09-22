package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebUtility {

	Logger log;


	public WebUtility() {

		this.log =  Logger.getLogger("manualLogger");
		log.info("WebUtility class instantiated");
	}
	public boolean openURL (WebDriver driver, String URL)
	{
		Boolean fncStatus=true;
		try {
			driver.get(URL);
		}catch(Exception e) {
			log.info("Error Occured in function openURL");
			log.error(e);
			fncStatus =false;
		}
		return fncStatus;
	}
	public boolean fn_click (WebElement Element) throws Exception
	{
		log.info("Click on element " +  Element);
		Element.click();
		return true;
	}
	public void fn_input (WebElement Element, String Data) throws Exception 
	{
		log.info("TESTDATA :: "+Data +" added to "+Element);
		Element.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		Element.sendKeys(Data);
	}
	
	public String fn_getText(WebElement Element) throws Exception {
		log.info("Get Text of an element " + Element);
		return(Element.getText());
	}
}
