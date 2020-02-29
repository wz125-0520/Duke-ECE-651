package ;
import java.util.*;
import org.json.*;
public class Test{
	private int x;

	public int getX(){
		return this.x;
	}

public void setX(int x){
		this.x = x;
	}

public JSONObject toJSON() throws JSONException{
 	 int id = 1;
	 HashMap<Object, Integer> record = new HashMap<Object, Integer>();
 	 return Helper_tojson(record, id);
 }
public JSONObject Helper_tojson(HashMap< Object, Integer> record, int id){
 	 JSONObject result = new JSONObject();
 	 if(record.containsKey(this)){
		result.put("ref", record.get(this));
	}
 	else{
		result.put("id", id);
		result.put("type",this.getClass().getSimpleName());
		record.put(this, id);
		id++;
		JSONObject temp = new JSONObject();
		JSONArray array = new JSONArray();
		temp.put("x", this.x);
		array.put(temp);
		result.put("values", array);
	}
 	return result;
}
}
