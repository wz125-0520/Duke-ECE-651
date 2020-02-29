package hwk1.testing.prims;
import java.util.*;
import org.json.*;
public class Deserializer{
public static Prims readPrims(JSONObject js) throws JSONException{
	HashMap<Integer, Object> stored = new HashMap<Integer, Object>();
	return new Deserializer().Helper_Prims(stored, js);
}
 public Prims Helper_Prims(HashMap<Integer, Object> stored, JSONObject js){
	int id;
if(js.opt("id") == null){
	id =js.getInt("ref");
}
else{
id = js.getInt("id");
}if(stored.containsKey(id)){
	return (Prims)stored.get(id);
}
else{
		Prims result = new Prims();
	stored.put(id, result);
JSONArray array = js.getJSONArray("values");
	Object current;
		JSONObject temp_0 = array.getJSONObject(0);
		current = temp_0.get("stackOfPancakes");
		result.setStackOfPancakes((short)temp_0.getInt("stackOfPancakes"));
		JSONObject temp_1 = array.getJSONObject(1);
		current = temp_1.get("waysAway");
		result.setWaysAway((long)current);
		JSONObject temp_2 = array.getJSONObject(2);
		current = temp_2.get("ateBits");
		result.setAteBits((byte)temp_2.getInt("ateBits"));
		JSONObject temp_3 = array.getJSONObject(3);
		current = temp_3.get("isntPronouncedLikeCare");
int x = temp_3.getInt("isntPronouncedLikeCare");
		result.setIsntPronouncedLikeCare((char)x);
		JSONObject temp_4 = array.getJSONObject(4);
		current = temp_4.get("x");
		result.setX((int)current);
		JSONObject temp_5 = array.getJSONObject(5);
		current = temp_5.get("trouble");
		result.setTrouble((double)current);
		JSONObject temp_6 = array.getJSONObject(6);
		current = temp_6.get("isAwesome");
		result.setIsAwesome((boolean)current);
		JSONObject temp_7 = array.getJSONObject(7);
		current = temp_7.get("boat");
		result.setBoat((float)current);
	return result;
}
}
}