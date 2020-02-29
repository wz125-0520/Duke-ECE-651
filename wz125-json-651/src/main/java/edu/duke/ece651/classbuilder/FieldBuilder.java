package edu.duke.ece651.classbuilder;

import org.json.*;
import java.util.*;

public class FieldBuilder{
  //pass in the JSONArray
  //a hashmap to store each name and type
  private HashMap<String, String> field;
  private HashMap<String, String> originalfield;
  private HashMap< String, Integer> fieldline;
  private HashMap<String, String> inner;
  private String constructor;
  private String entire;
  FieldBuilder(JSONArray fieldarray){
    String fieldname,fieldtype,temp;
    
    Integer dimension;
    Object type_temp;
    JSONObject obj;
    AddMethod method ;
    //store the private fieldline and its dimension
    this.fieldline = new HashMap<String, Integer>();
    //store the field type and field name
    this.field = new HashMap<String, String>();
    this.originalfield = new HashMap<String, String>();
    //store the field realted fucntion
    this.inner = new HashMap<String, String>();
    this.entire = "";
    this.constructor = "";
    
   
    //iterate through the entire fields JSONArray to seperate the name and type
      for(int i = 0; i < fieldarray.length(); i++){
        obj = fieldarray.getJSONObject(i);
        fieldname = obj.getString("name");
        type_temp = obj.get("type");
        if(type_temp instanceof String){
          fieldtype = (String)type_temp;
          temp = "private " + fieldtype + " " + fieldname + ";\n";
          this.fieldline.put(temp, 0);
          this.field.put(fieldname, fieldtype);
          this.originalfield.put(fieldname,fieldtype);
          method = new AddMethod(fieldname, fieldtype, fieldtype);
          this.inner.put(temp, method.NonArray());
        }
        else{
          TypeBuilder type_str = new TypeBuilder((JSONObject)type_temp);
          fieldtype = type_str.get_Declared_Type();
          dimension = type_str.getDimension();
          temp = "private " + fieldtype + " " + fieldname + ";\n";
          this.fieldline.put(temp, dimension);
          this.field.put(fieldname, fieldtype);
          this.originalfield.put(fieldname, type_str.get_Original_Type());
          //this.tojsonfield.put(fieldname, type_str.get_Declared_Type());
          method = new AddMethod(fieldname, fieldtype, type_str.get_Output_Type());
          if(dimension == 1){
            this.inner.put(temp, method.OneDimension());
            // method.constructor_func();
          }
          else{
            this.inner.put(temp, method.MultiDimension());
            //method
          }
          //method.constrcutor_func();
          this.constructor = this.constructor + "\tthis." + fieldname + " = " + "new " + fieldtype + "()\n";
        }
            
      }
      //System.out.println(this.inner);
      
  }
  public HashMap<String, String> getField(){
    
    return this.field;
  }
   public HashMap<String, String> getOriginalField(){
    
    return this.originalfield;
  }
  public String getConstruct(){
    return this.constructor;
    
  }
  public String getInnerCode(){
    //String entire = "";
    for(HashMap.Entry<String,String> entries: this.inner.entrySet() ){
      this.entire += "\t" + entries.getKey() + "\n";
      
    }
    for(HashMap.Entry<String,String> entries: this.inner.entrySet() ){
      this.entire += "\t" + entries.getValue() + "\n";
      
    }
    return this.entire;
  }
}
