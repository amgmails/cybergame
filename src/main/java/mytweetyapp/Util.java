package mytweetyapp;


/**
 * 
 * @author maixent
 * taken on https://dzone.com/articles/converting-java-objects-byte
 *
 */

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;


public class Util {
	/**
	* Convert object to JSON String 
	* @param object
	* @return
	* @throws JsonGenerationException
	* @throws JsonMappingException
	* @throws IOException
	*/
	public static String fromJavaToJson(Message object)
		throws JsonGenerationException, JsonMappingException, IOException {
	    ObjectMapper jsonMapper = new ObjectMapper();
	    return jsonMapper.writeValueAsString(object);
	}

	/**
	* Convert a JSON string to an object
	* @param json
	* @return
	* @throws JsonParseException
	* @throws JsonMappingException
	* @throws IOException
	*/
	public static Object fromJsonToJava(String json, Class type) throws JsonParseException,
			JsonMappingException, IOException {
	     ObjectMapper jsonMapper = new ObjectMapper();
	     return jsonMapper.readValue(json, type);
	}

}
