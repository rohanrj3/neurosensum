package utility;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadTestData {
	JSONObject obj;
	public ReadTestData() {
		JSONParser parser = new JSONParser();
		try {
			this.obj =(JSONObject) parser.parse(new FileReader(System.getProperty("testDataPath")+"testData.json"));
			System.out.println(obj.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getGlobalData(String key) {
		String data = (String) this.obj.get(key);
		return data;
	}
	
}
