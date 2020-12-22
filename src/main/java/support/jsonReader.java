package support;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class jsonReader {

	static public JSONObject  ReadJsonFile(String filename) throws ParseException
	{
		JSONObject jsonObject = null ;

		JSONParser parser = new JSONParser();
		try
		{
			Object obj = parser.parse(new FileReader(
					"src/test/resources/Data/"+filename));
			jsonObject = (JSONObject) obj;	
		}
		catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		} 
		return jsonObject;
	}	
}
