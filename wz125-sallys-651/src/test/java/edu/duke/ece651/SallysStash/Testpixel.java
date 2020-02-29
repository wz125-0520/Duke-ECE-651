package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Testpixel {
  @Test
  public void test_pixel(){
        pixel test = new pixel();
        test.setNum(2);
        assert (test.getNum() == 2);
        test.setColor('G');
        assert (test.getColor() == 'G');
        test.setID(5);
        assert (test.getID() == 5);
        test.setIsHit(true);
        assert (test.getIsHit() == true);
        test.setIsMiss(true);
        assert (test.getIsMiss() == true);

  }

}
