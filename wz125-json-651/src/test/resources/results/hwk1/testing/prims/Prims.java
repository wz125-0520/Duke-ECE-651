package hwk1.testing.prims;
import java.util.*;
import org.json.*;
public class Prims{
	private float boat;

	private char isntPronouncedLikeCare;

	private long waysAway;

	private byte ateBits;

	private int x;

	private double trouble;

	private boolean isAwesome;

	private short stackOfPancakes;

	public float getBoat(){
		return this.boat;
	}

public void setBoat(float b){
		this.boat = b;
	}

	public char getIsntPronouncedLikeCare(){
		return this.isntPronouncedLikeCare;
	}

public void setIsntPronouncedLikeCare(char i){
		this.isntPronouncedLikeCare = i;
	}

	public long getWaysAway(){
		return this.waysAway;
	}

public void setWaysAway(long w){
		this.waysAway = w;
	}

	public byte getAteBits(){
		return this.ateBits;
	}

public void setAteBits(byte a){
		this.ateBits = a;
	}

	public int getX(){
		return this.x;
	}

public void setX(int x){
		this.x = x;
	}

	public double getTrouble(){
		return this.trouble;
	}

public void setTrouble(double t){
		this.trouble = t;
	}

	public boolean getIsAwesome(){
		return this.isAwesome;
	}

public void setIsAwesome(boolean i){
		this.isAwesome = i;
	}

	public short getStackOfPancakes(){
		return this.stackOfPancakes;
	}

public void setStackOfPancakes(short s){
		this.stackOfPancakes = s;
	}

public JSONObject toJSON() throws JSONException{
	 HashMap<Object, Integer> record = new HashMap<Object, Integer>();
 	 return Helper_tojson(record);
}
public JSONObject Helper_tojson(HashMap< Object, Integer> record){
	 JSONObject result = new JSONObject();
	 if(record.containsKey(this)){
		result.put("ref", record.get(this));
	}
	else{
		result.put("id", record.size()+1);
		result.put("type",this.getClass().getSimpleName());
		record.put(this, record.size()+1);
		JSONArray array = new JSONArray();
		JSONObject temp_0 = new JSONObject();
		temp_0.put("stackOfPancakes", this.stackOfPancakes);
		array.put(temp_0);
		JSONObject temp_1 = new JSONObject();
		temp_1.put("waysAway", this.waysAway);
		array.put(temp_1);
		JSONObject temp_2 = new JSONObject();
		temp_2.put("ateBits", this.ateBits);
		array.put(temp_2);
		JSONObject temp_3 = new JSONObject();
		temp_3.put("isntPronouncedLikeCare", this.isntPronouncedLikeCare);
		array.put(temp_3);
		JSONObject temp_4 = new JSONObject();
		temp_4.put("x", this.x);
		array.put(temp_4);
		JSONObject temp_5 = new JSONObject();
		temp_5.put("trouble", this.trouble);
		array.put(temp_5);
		JSONObject temp_6 = new JSONObject();
		temp_6.put("isAwesome", this.isAwesome);
		array.put(temp_6);
		JSONObject temp_7 = new JSONObject();
		temp_7.put("boat", this.boat);
		array.put(temp_7);
		result.put("values", array);
	}
 	return result;
}
}
