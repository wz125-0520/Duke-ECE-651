
package edu.duke.ece651.classbuilder;

import org.json.*;
import java.util.*;
import java.io.*;
//import java.io.File;


public class ClassBuilder {
  private HashMap<String, String> classes;
  private HashMap<String, String> allcode;
  //private String classnames;
  //private ArrayList<String> class_name;
  private HashMap<String, String> Name;
  private HashMap<String, String> Original;
  private String subdir;
  private String path;
  private String code_construct;
  private String deserial;
  ClassBuilder(String str) {
    JSONObject outer_class;
    JSONArray field;
    String classnames;
    this.subdir = "";
    this.classes = new HashMap<String, String>();
    this.allcode = new HashMap<String, String>();
    this.Name = new HashMap<String,String>();
    this.Original = new HashMap<String,String>();
    this.deserial = "public class Deserializer{\n";
    //this.class_name = new ArrayList<String>();
    JSONObject input_json = new JSONObject(str);
    //Object pack = input_json.getString("package");
    Subdirectory(input_json);
    JSONArray array = input_json.getJSONArray("classes");
    for(int i = 0; i < array.length(); i++){
      outer_class = array.getJSONObject(i);
      classnames = outer_class.getString("name");
      field = outer_class.getJSONArray("fields");
      FieldBuilder innerfield = new FieldBuilder(field);
      Subdirectory(input_json);
      this.Name = innerfield.getField();
      this.Original = innerfield.getOriginalField();
      this.deserial += Add_deserialier(classnames);
      //System.out.println("TOJSON METHOD:"+ AddToJSON());
      //add constructor
      //this.class_name.add(classnames);
      this.code_construct = innerfield.getConstruct();
      if(this.code_construct != ""){
        this.add_construct(classnames);
        
    }
      this.classes.put(classnames, innerfield.getInnerCode() + this.code_construct + AddToJSON());
      
    }
  }
  public void Subdirectory(JSONObject input){
    try{
      Object pack = input.getString("package");
      // String path;
      //if (pack != null) {
	this.path = pack.toString();
        this.subdir = pack.toString().replace(".", "/");
     
        //System.out.println("changed:"+ this.subdir);
        //}
      return;
    }
    catch(JSONException e){
      this.subdir = "";
      this.path = "";
    }
    //System.out.println(this.subdir);
  }
  public Boolean If_Primitive(String type){
    switch(type){
    case "int":
      return true;
    case "char":
      return true;
    case "boolean":
      return true;
    case "String":
      return true;
    case "float":
      return true;
    case "double":
      return true;
    case "byte":
      return true;
    case "short":
      return true;
    case "long":
      return true;
    default:
      return false; 
    }
  }
  
  public String AddToJSON(){
    //Iterator<HashMapString> Original_Iterator = originalfield.keySet().iterator();
    Iterator<HashMap.Entry<String, String>> original =this.Original.entrySet().iterator();
    String inside = "";
    String toJSON = "public JSONObject toJSON() throws JSONException{\n\t HashMap<Object, Integer> record = new HashMap<Object, Integer>();\n \t return Helper_tojson(record);\n}\n";
    
    String Helper = "public JSONObject Helper_tojson(HashMap< Object, Integer> record){\n";
	Helper+="\t JSONObject result = new JSONObject();\n";
	Helper+="\t if(record.containsKey(this)){\n";
	Helper+="\t\tresult.put(\"ref\", record.get(this));\n\t}\n";
	Helper+= "\telse{\n\t\tresult.put(\"id\", record.size()+1);\n";
	Helper +="\t\tresult.put(\"type\",this.getClass().getSimpleName());\n";
	Helper += "\t\trecord.put(this, record.size()+1);\n";
	Helper += "\t\tJSONArray array = new JSONArray();\n";
	int index = 0;
    for(HashMap.Entry<String,String> entries: this.Name.entrySet()){
      HashMap.Entry<String,String> type_iter = original.next();
      String fieldname = entries.getKey();
      String fieldtype = entries.getValue();
      System.out.println("Array?:" + If_Primitive(type_iter.getValue()) );
      inside += "\t\tJSONObject temp_" + index + " = new JSONObject();\n";
      if(If_Primitive(fieldtype)||If_Primitive(type_iter.getValue())){
        inside = inside + "\t\ttemp_"+ index +".put(\"" + fieldname + "\", this." + fieldname + ");\n";
      }
      else{
        if(fieldtype.indexOf("ArrayList") != -1){
          inside = inside + "\t\tJSONArray inner_array = new JSONArray();\n\t\tfor(int i = 0; i < " + fieldname
              + ".size(); i++){\n\t\t\tinner_array.put(" + fieldname + ".get(i)"
              + ".Helper_tojson(record));\n\t\t}\n\t\ttemp_"+ index+".put(\"" + fieldname
              + "\", inner_array);\n";
        }
        else{
          inside = inside + "\t\ttemp_"+index+".put(\"" + fieldname + "\", " + fieldname + ".Helper_tojson(record));\n";
        }
      }
      inside = inside + "\t\tarray.put(temp_"+index+");\n";
	index++;
      }
      String end = "\t\tresult.put(\"values\", array);\n\t}\n \treturn result;\n}\n";
      return toJSON + Helper + inside + end;
    }
 
  ClassBuilder(InputStream input) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(input));
    String str = "";
    String line = br.readLine();
    while(line != null){
      str += line;
      line = br.readLine();
    }
    
    JSONObject outer_class;
    JSONArray field;
    String classnames;
    this.subdir = "";
    this.classes = new HashMap<String, String>();
    this.allcode = new HashMap<String, String>();
    this.Name = new HashMap<String,String>();
    this.Original = new HashMap<String,String>();
    this.deserial = "public class Deserializer{\n";
    //this.class_name = new ArrayList<String,String>();
    JSONObject input_json = new JSONObject(str);
    //Object pack = input_json.getString("package");
    Subdirectory(input_json);
    JSONArray array = input_json.getJSONArray("classes");
    for(int i = 0; i < array.length(); i++){
      outer_class = array.getJSONObject(i);
      classnames = outer_class.getString("name");
      field = outer_class.getJSONArray("fields");
      FieldBuilder innerfield = new FieldBuilder(field);
      Subdirectory(input_json);
      this.Name = innerfield.getField();
      this.Original = innerfield.getOriginalField();
      this.deserial += Add_deserialier(classnames);
      //System.out.println("TOJSON METHOD:"+ AddToJSON());
      //add constructor
      this.code_construct = innerfield.getConstruct();
      if(this.code_construct != ""){
        this.add_construct(classnames);
        
    }
     
System.out.print("\n\n\n"+this.Name);
      this.classes.put(classnames, innerfield.getInnerCode() + this.code_construct + AddToJSON());
      
    }
    System.out.println(this.classes);

  }


  
  public void add_construct(String classnames){
    this.code_construct = classnames + "(){\n\t" + this.code_construct + ";\n}\n";
  }
  
  public Collection<String> getClassNames() {
    Collection<String> ClassNames = new ArrayList<String>();
    for (HashMap.Entry<String, String> entries : this.classes.entrySet()) {
      ClassNames.add(entries.getKey());
      //return this.classnames;
    }
    return ClassNames;
  }
  
  public String getSourceCode(String className){
    //String entirecode = "public class " + className + "{\n" + this.classes.get(className) + "}\n";
    return this.allcode.get(className);
    //return entirecode;
  }
  public void CreateCodeForAll(){
    for (HashMap.Entry<String, String> entries : this.classes.entrySet()){
      String entirecode = "public class " + entries.getKey() + "{\n" + entries.getValue() + "}\n";
      this.allcode.put(entries.getKey(), entirecode);
    }
  }

  public void createAllClasses(String basePath){
 
    CreateCodeForAll();
    try{
      for(HashMap.Entry<String, String> into_file : this.allcode.entrySet()){
        
        String output = into_file.getValue();
        String path = basePath + "/" + this.subdir + "/" + into_file.getKey() + ".java";
        //File file = new File( basePath + "/" + this.subdir);
        /* 
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(output);*/
        PrintWriter p = new PrintWriter(path);
        p.print("package "+this.path + ";\nimport java.util.*;\nimport org.json.*;\n" + output);
        p.close();
      }
      //File file_deserail = new File();
      /*PrintStream ps_deserial = new PrintStream(new FileOutputStream(file_deserail));
        ps_deserial.println(Add_deserialier());*/
      String path = basePath + "/" + this.subdir + "/" + "Deserializer.java";
      System.out.println(path);
      PrintWriter p_de = new PrintWriter(path);
      p_de.print("package " + this.path + ";\nimport java.util.*;\nimport org.json.*;\n" + this.deserial + "}");
      p_de.close();
    }
    catch(FileNotFoundException e){
      //System.out.println("PathPPPPPPPPPPPPPP"+path);
      e.printStackTrace();
    }
    //create the source code class per file(output)
  }
  /*public String inter_des(String classname){
    String code = "public class Deserializer{\n";
    code += Add_deserialier(classname);
    return code;
    }*/
  public String Add_deserialier(String classname){
    //String front = "public class Deserializer{\n";
    String code = "";
    //for(HashMap.Entry<String, String> entries : this.classes.entrySet()){
    //String classname = entries.getKey();
      String func_dec = "public static " + classname + " read" + classname + "(JSONObject js) throws JSONException{\n";
      String readfunc = "\tHashMap<Integer, Object> stored = new HashMap<Integer, Object>();\n" + "\treturn new Deserializer().Helper_"
        + classname + "(stored, js);\n}\n";
        
	String helper = " public " + classname +" Helper_" + classname + "(HashMap<Integer, Object> stored, JSONObject js){\n\tint id;\nif(js.opt(\"id\") == null){\n\tid =js.getInt(\"ref\");\n}\nelse{\nid = js.getInt(\"id\");\n}" + "if(stored.containsKey(id)){\n\treturn (" + classname + ")stored.get(id);\n}\n";

helper += "else{\n\t\t"+ classname + " result = new " + classname + "();\n\tstored.put(id, result);\nJSONArray array = js.getJSONArray(\"values\");\n\tObject current;\n";
	int index = 0;
       Iterator<HashMap.Entry<String, String>> original =this.Original.entrySet().iterator();
        for(HashMap.Entry<String, String> entries_inner : this.Name.entrySet()){
          HashMap.Entry<String,String> type_iter = original.next();
          String fieldname = entries_inner.getKey();
          String fieldtype = entries_inner.getValue();
          String fieldtype_original = type_iter.getValue();
          //helper += "\t\tJSONObject temp_"+index+" = new JSONObject();\n";
          helper += "\t\tJSONObject temp_"+index+" = array.getJSONObject(" + index+ ");\n\t\tcurrent = temp_"+index+".get(\"" + fieldname + "\");\n";
          if(If_Primitive(fieldtype) || If_Primitive(fieldtype_original)){
            if(fieldtype.indexOf("ArrayList") != -1){
              helper += "for(int i = 0; i < temp_"+index+".getJSONArray(\"" + fieldname + "\").length(); i++){\n";
              if (fieldtype_original.equals("byte")) {
                helper += "\t\tresult.add" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "(("
                    + fieldtype_original + ")temp_" + index +".getJSONArray(\""+ fieldname+ "\").getInt(\"" + fieldname + "\"));\n";
              } else if (fieldtype_original.equals("short")) {
                helper += "\t\tresult.add" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "(("
                    + fieldtype_original + ")temp_" + index +".getJSONArray(\""+ fieldname+ "\").getInt(\"" + fieldname + "\"));\n";
              } else if (fieldtype_original.equals("char")) {
                helper += "int x = temp_" + index + ".getJSONArray(\""+ fieldname+ "\").getInt(\"" + fieldname + "\");\n" + "\t\tresult.set"
                    + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "((" + fieldtype_original
                    + ")x);\n";
              }
              else{
              helper += "\t\tresult.add" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1)
                + "(temp_"+index+".getJSONArray(\""+ fieldname+"\").get" + fieldtype_original.substring(0, 1).toUpperCase() + fieldtype_original.substring(1) + "(i));\n}\n";}
            }
            else {
              if (fieldtype_original.equals("byte")) {
                helper += "\t\tresult.set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "(("
                    + fieldtype_original + ")temp_" + index + ".getInt(\"" + fieldname + "\"));\n";
              } else if (fieldtype_original.equals("short")) {
                helper += "\t\tresult.set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "(("
                    + fieldtype_original + ")temp_" + index + ".getInt(\"" + fieldname + "\"));\n";
              } else if (fieldtype_original.equals("char")) {
                helper += "int x = temp_" + index + ".getInt(\"" + fieldname + "\");\n" + "\t\tresult.set"
                    + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "((" + fieldtype_original
                    + ")x);\n";
              } else {
                helper += "\t\tresult.set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "(("
                    + fieldtype_original + ")" + "current);\n";
              }
            }
          }
          else{
            if(fieldtype.indexOf("ArrayList") != -1 ){
              helper += "for(int i = 0; i < temp_"+index+".getJSONArray(\"" + fieldname + "\").length(); i++){\n";
              helper += "\t\tresult.add" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1)
                  + "(Helper_" + fieldtype_original + "(stored, temp_"+index+".getJSONArray(\""+ fieldname+"\").getJSONObject(i)));\n}\n";
              //helper+= fieldname + ".set" +  + "(i, Helper_" + fieldtype + "(stored, current));";
              }
              else {

                helper += fieldtype + " type_inner = new " + fieldtype + "();\n\tresult.set"
                    + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1) + "(Helper_" + fieldtype
                    + "(stored, temp_"+index+".getJSONObject(\""+ fieldname+"\")));\n";
              }
          }
	index++;
          //helper += "\t\tindex++;\n" ;
        }
        
        helper += "\treturn result;\n}\n}\n";
      code += func_dec + readfunc + helper;
    
    return code;  
  }
  
}
