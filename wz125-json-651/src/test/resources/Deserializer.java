package ;
import java.util.*;
import org.json.*;
public class Deserializer{
public static Test readTest(JSONObject js) throws JSONException{
	HashMap<Integer, Object> stored = new HashMap<Integer, Object>();
	return new Deserializer().Helper_Test(stored, js);
}
 public Test Helper_Test(HashMap<Integer, Object> stored, JSONObject js){
	int ref =js.optInt("ref");
	int id;
	Test result = new Test();
	if(ref != 0){
		return (Test)stored.get(ref);
	}
	else{
		id = js.getInt("id");
		int index = 0;
		JSONArray array = js.getJSONArray("values");
		JSONObject temp = new JSONObject();
		temp = array.getJSONObject(index);
		Object current = temp.get("x");
		result.setX((int)current);
		index++;
	}
	stored.put(id, result);
	return result;
}
}