package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestPair {
  @Test
  public void test_Pair(){
        Pair test = new Pair(3,4);
        assert (test.getX() == 3);
        assert (test.getY() == 4);

  }

}
