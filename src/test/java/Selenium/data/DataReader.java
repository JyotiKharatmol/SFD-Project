package Selenium.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getDataMap(String filename) throws IOException
	{
		
		//json to string
		
		String jsonContent = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
		
		//standardCharSets.UTF_8 - converted string format
		
		//convert string to hashmap
		
		//jackson data bind dependencies is used to convert string to hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		
	List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
			{
			});
		return data;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
