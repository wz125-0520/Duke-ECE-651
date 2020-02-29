package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestStackInfo {
  @Test
  public void test_StackInfo() {
    StackInfo s = new StackInfo();
    for(int i = 1; i <= 10; i++){
      System.out.println(s.GetPrompt(i));
      System.out.println(s.GetStack(i));
    }
  }
}
