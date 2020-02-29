package edu.duke.ece651.classbuilder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.*;

public class TestClassBuilder {
  @Test
  public void test_ClassBuilder() {
    String str1 = "{ 'classes' : [{'name' : 'Test', 'fields' : [{'name' : 'x', 'type' : 'int'}]}]}";
    ClassBuilder cb1 = new ClassBuilder(str1);
    cb1.getSourceCode(str1);
    String str2 =" { 'classes' : [{'name' : 'Test', 'fields' : [{'name' : 'x', 'type': 'int'},{'name' : 'mytest', 'type': 'float'}]}]}";
    ClassBuilder cb2 = new ClassBuilder(str2);
    String str3 = "{ 'classes' : [ {'name' :'Matrix3d', 'fields' : [ {'name' : 'data', 'type': {'e':  'Cat'}}]}]}";
    ClassBuilder cb3 = new ClassBuilder(str3);
    String str4 = "{ 'classes' : [ {'name' : 'Course', 'fields' : [ {'name' : 'instructor', 'type' : 'Faculty'}, {'name' : 'numStudents', 'type' : 'int' }, {'name' : 'grades', 'type' : {'e': 'Grade'}}]}, {'name' : 'Office',  'fields' : [ {'name' : 'occupant', 'type': 'Faculty'}, {'name' : 'number', 'type': 'int'}, {'name' : 'building' , 'type': 'String'}]}, {'name' : 'Faculty', 'fields' : [ {'name' : 'name', 'type' : 'String' }, {'name' : 'taught', 'type' : {'e': 'Course'}} ]}, {'name' : 'Grade', 'fields' : [ {'name' : 'course', 'type' : 'Course'}, {'name' : 'student', 'type' : 'String'}, {'name' : 'grade', 'type': 'double'}]} ]}";
;
    ClassBuilder cb4 = new ClassBuilder(str4);
    cb4.getClassNames();
    String str5_multi = "{ 'classes' : [ {'name' : 'Matrix3dint', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'int'}}}}]},{'name' : 'Matrix3dshort', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'short'}}}}]},{'name' : 'Matrix3dboolean', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'boolean'}}}}]},{'name' : 'Matrix3ddouble', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'double'}}}}]},{'name' : 'Matrix3dbyte', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'byte'}}}}]},{'name' : 'Matrix3dchar', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'char'}}}}]},{'name' : 'Matrix3dlong', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'long'}}}}]},{'name' : 'Matrix3dfloat', 'fields' : [ {'name' : 'data', 'type': {'e': {'e' : {'e' : 'float'}}}}]} ]}";
    ClassBuilder cb5 = new ClassBuilder(str5_multi);
    String str6 ="{ 'package' : 'hwk1.testing.simplearray', 'classes' : [ {'name' : 'Test', 'fields' : [{'name' :'arr', 'type' : {'e' : 'int'}}]},  {'name' : 'Test', 'fields' : [{'name' :'arr', 'type' : {'e' : 'byte'}}]},  {'name' : 'Test', 'fields' : [{'name' :'arr', 'type' : {'e' : 'short'}}]},  {'name' : 'Test', 'fields' : [{'name' :'arr', 'type' : {'e' : 'char'}}]},  {'name' : 'Test', 'fields' : [{'name' :'arr', 'type' : {'e' : 'double'}}]},  {'name' : 'Test', 'fields' : [{'name' :'arr', 'type' : {'e' : 'String'}}]}]}";
    ClassBuilder cb6 = new ClassBuilder(str6);
 
    //cb.createAllClasses("/home/weihan/wz125-json-651/src/test/resources");
    //System.out.println(cb.Add_deserialier());
    try{
      InputStream f = getClass().getResourceAsStream("/prims.json");
    
    
      ClassBuilder cb = new ClassBuilder(f);
    
      /*System.out.println("All the classnames: " + cb.getClassNames());
      System.out.println("Source code:\n" + cb.getSourceCode("Course") +"\n");
      System.out.println("Source code:\n" + cb.getSourceCode("Office")+"\n");
      System.out.println("Source code:\n" + cb.getSourceCode("Faculty")+"\n");
      System.out.println("Source code:\n" + cb.getSourceCode("Grade")+"\n");*/
      //cb.createAllClasses("/home/weihan/ece651-hwk1-tester/src/main/java");
     cb.createAllClasses("src/test/resources/results/");
     cb.createAllClasses("sr/tste/e");
    }
    catch(IOException e){
      System.out.println("Exception");
      }
    
  }

}
