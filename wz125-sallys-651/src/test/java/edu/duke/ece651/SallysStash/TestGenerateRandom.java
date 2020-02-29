package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestGenerateRandom {
  @Test
  public void test_random(){
        GenerateRandom gen = new GenerateRandom();
        System.out.println(gen.getInputRandom(3, 2));
        System.out.println(gen.getInputRandom(3, 9));
        System.out.println(gen.getInputRandom(2, 2));
        System.out.println(gen.getInputChoice(3, 1));
        System.out.println(gen.getInputChoice(0, 1));
        System.out.println(gen.getInputChoice(3, 0));
  }

}
