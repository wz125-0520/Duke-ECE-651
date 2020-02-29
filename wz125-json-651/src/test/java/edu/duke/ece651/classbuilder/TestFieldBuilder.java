package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.json.*;
import org.junit.jupiter.api.Test;

public class TestFieldBuilder {
  @Test
  public void test_FieldBuilder() {
    //JSONArray arr = new JSONArray("[ {'name' : 'instructor', 'type' : 'Faculty'},{'name' : 'numStudents', 'type' : 'int' }]");
    //wJSONArray arr = new JSONArray("[ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'int'}}}}]");
    JSONArray arr = new JSONArray("[ {'name' : 'data', 'type': {'e': {'e' : 'int'}}}]");
    
    //JSONArray arr = new JSONArray("[ {'name' : 'data', 'type': {'e': 'int'}}]");
   
    //JSONArray arr = new JSONArray("[{'name' : 'instructor', 'type' : 'Faculty'},{'name' : 'numStudents', 'type' : 'int' }]");
    //System.out.println(arr);
    FieldBuilder fb = new FieldBuilder(arr);
    //fb.innercode();
    //fb.ConvertToField();
  }

}
