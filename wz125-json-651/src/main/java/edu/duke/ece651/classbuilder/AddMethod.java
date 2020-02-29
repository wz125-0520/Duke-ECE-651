package edu.duke.ece651.classbuilder;

import java.util.ArrayList;;

public class AddMethod{
  //private String method;
  private String Var;
  private String var;
  private String type;
  private String output;
  private String constructor;
  
  AddMethod(String var, String type, String output_type) {
    this.var = var;
    this.Var = var.substring(0,1).toUpperCase() + var.substring(1);
    this.type = type;
    this.output = output_type;
    this.constructor = "";
  }
 
  public String NonArray(){
    String get = this.getfunction();
    String set = this.setfunction();
    return get +"\n" + set;
  }

  public String getfunction(){
    String method ="public " + this.type + " get" + this.Var + "(){\n" + "\t\treturn this." + this.var + ";\n\t}\n";
    return method;
  }
  public String setfunction(){
    String first =  this.var.substring(0,1);
    String method = "public " + "void set" + this.Var + "("+ this.type + " " +  first + "){\n" + "\t\tthis." + this.var + " = " + first + ";\n\t}\n";
    return method;
  }

  
  public String OneDimension(){
    return this.numfunc() + "\n" + this.addfunc() + "\n" + this.getfunc() + "\n" + this.setfunc();
  }
  
  public String numfunc(){
    String method = "public int num" + this.Var + "(){\n" + "\t\treturn this." + this.var + ".size();\n\t}\n";
    return method;
  }
  
  public String addfunc(){
    String first =  this.var.substring(0,1);
    //ArrayList<Integer> x =  new ArrayList<Integer> ();
    String method = "public void add" + this.Var + "("+ this.output + " " +  first + "){\n" + "\t\tthis." + this.var + ".add(" + first + ");\n\t}\n";
    return method;
  }
  
  public String getfunc(){
    String method = "public " + this.output + " get" + this.Var + "(int index){\n" + "\t\treturn this." + this.var + ".get(index);\n\t}\n";
    return method;
  }
  
  public String setfunc(){
    String first =  this.var.substring(0,1);
    String method = "public void set" + this.Var + "(int index, "+ this.output + " " +  first + "){\n" + "\t\tthis." + this.var + ".set(index, " + first + ");\n\t}\n";
    return method;
  }
  /*public constructor_func(){
    constructor = constructor + "\tthis." + this.var + " = " + "new " + this.type + "()\n";
    return constructor;
  }

  public String getConstruct(){
    return this.constructor;
    }*/
  public String MultiDimension(){
    return this.addfunc() + "\n" + this.getfunc() + "\n" + this.setfunc();
  }
  
  
}
