package driver;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class Driver {

	static String browser;
	static String mstrfileName;
	static String mstrsheetName;
	static Logger log;
	static String rprt_name;

	/*
	 *  Set of codes which needs to be executed once for initialization at the start of execution 
	 */

	static {
		try {
			// Properties for setting up logs.
			System.out.println("Static Block Callled");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm");
			String date_log_rprt = dateFormat.format(new Date());
			String lr_path =System.getProperty("user.dir") ;
			lr_path = lr_path.substring(0, lr_path.indexOf("neurosensum"));
			System.setProperty("testDataPath",System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\");
			System.setProperty("lr.path", lr_path + "\\Report_Logs\\" + date_log_rprt.substring(0, 10)+"\\"+date_log_rprt.substring(11, 16)+"\\");
			System.setProperty("log.timeStamp",date_log_rprt );
			PropertyConfigurator.configure("log4j.properties");
			// Reading of global variables framework variables passed through maven parameters
			browser = System.getProperty("browser");
			log = Logger.getLogger("manualLogger");
			log.info("********            EXECUTION STARTES             **************");
			log.info("****************************************************************");
			log.info("Test Suite: " + System.getProperty("testSuite"));
			log.info("Browser: " + browser);
			// ================= Code For Report generation =====================
			rprt_name = "ExecRprt_" + date_log_rprt+".html";
			//	testreport = new ExtentReports(System.getProperty("lr.path")+rprt_name);
			log.info("Test Report Name: " + rprt_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error Occured static block during configuring log4j prperties and creation of reports");
			log.error(e.getStackTrace());
		}
	}

	/*
	 * @Factory creates run time instance of @test. Calls @test for the data provided by data provider.
	 */

	@Factory(dataProvider = "dpMasterPlan")
	public Object[] createInstances(String testCaseID, String testCaseName){
		System.out.println("TestCaseName :" + testCaseName);
		System.out.println("TestCaseID :" + testCaseID);
		Object[] tst = null;
		try {
			log.info("================================================================");
			log.info("Test Execution starts for : " + testCaseName);
			log.info("================================================================");
			Class<?> dynclass = Class.forName(testCaseName);
			Constructor<?> contr = dynclass.getDeclaredConstructor(String.class,String.class);
			tst = new Object[] {contr.newInstance(browser,testCaseID)};	

		}catch(Exception e) {
			log.error("Exception Occured: " + e);
			e.printStackTrace();
		}

		return tst; 
	}

	/*
	 * Data Provider for @Factory, returns list of test classes which needs to be executed. 
	 */

	@DataProvider
	public String[][] dpMasterPlan() throws Exception {
		String[][] testCases=null;
		HashMap<String, String> mapTcs= new HashMap<String, String>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new FileReader(System.getProperty("testDataPath")+"testCases.json"));
			JSONObject testCasesArr = (JSONObject) obj.get("Sanity");
			for (Object key : testCasesArr.keySet()) {
				JSONObject tcDetails = (JSONObject) testCasesArr.get(key);
				if(((String) tcDetails.get("Execute")).toUpperCase().matches("Y")) {
					mapTcs.put((String) key, (String) tcDetails.get("TestCase_Name"));
				}
			}
			testCases = new String[mapTcs.size()][2];
			int i=0;
			for(String mapKey : mapTcs.keySet()) {
				testCases[i][0]=mapKey;
				testCases[i++][1]=mapTcs.get(mapKey);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.error("Exception Occured: " + e);
		}
		return (testCases);
	}




}
