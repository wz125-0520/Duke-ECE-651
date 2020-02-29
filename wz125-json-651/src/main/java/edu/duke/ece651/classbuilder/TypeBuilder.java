package edu.duke.ece651.classbuilder;

//import org.json.JSONObject;
import org.json.*;

public class TypeBuilder {
  private String declared_type;
  private String primitive_type;
  private String type_original;
  private String type_output;
  private int dimension;
  TypeBuilder(JSONObject array_obj){
    this.dimension = 1;
    this.declared_type = "";
    try{
      while (array_obj != null){
        //this.dimension += 1;
        array_obj = array_obj.getJSONObject("e");
        this.dimension += 1;
      }
    }
    catch(JSONException e){
      this.type_original = array_obj.getString("e");
      this.PrimitiveType(type_original);
      if(this.dimension == 1){
        this.declared_type = "ArrayList<" + this.primitive_type + ">";
        this.type_output = this.type_original;
      }
      else{
        for(int i = 1; i <= this.dimension; i++){
          
          this.declared_type = this.declared_type + "Collection<";
        }
        this.declared_type = this.declared_type + this.primitive_type;
       
        for(int i = 1; i <= this.dimension; i++){
          this.declared_type = this.declared_type + ">";
        }
        this.type_output = this.declared_type.replaceFirst("Collection<", "");
        this.type_output = this.type_output.replaceFirst(">","");
       
      }
      //System.out.println("parse declared_type: " + this.declared_type);
  }
}
  public void PrimitiveType(String input){
    switch(input){
    case "int":
      this.primitive_type = "Integer";
      break;
    case "boolean":
      this.primitive_type = "Boolean";
      break;
    case "char":
      this.primitive_type = "Character";
      break;
    case "byte":
      this.primitive_type = "Byte";
      break;
    case "short":
      this.primitive_type = "Short";
      break;
    case "long":
      this.primitive_type = "Long";
      break;
    case "float":
      this.primitive_type = "Float";
      break;
    case "double":
      this.primitive_type = "Double";
      break;
    default:
      this.primitive_type = this.type_original;
      break;
    }
  }
  public String get_Declared_Type(){
    return this.declared_type;
  }
  public String get_Original_Type(){
    return this.type_original;
  }
  public String get_Output_Type(){
    /*this.type_output = this.declared_type.replaceFirst("Collection<", "");
      this.type_output = this.type_output.replaceFirst(">","");*/
    return this.type_output;
  }
  public int getDimension(){
    return this.dimension;
  }
}
