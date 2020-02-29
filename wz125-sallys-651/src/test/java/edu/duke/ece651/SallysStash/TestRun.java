package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class TestRun {
  @Test
  public void test_main() throws FileNotFoundException{
    System.setIn(new FileInputStream("src/test/resources/input_hvh.txt"));
    Run r_hvc = new Run();
    r_hvc.main(null);
    System.setIn(new FileInputStream("src/test/resources/input_cvc.txt"));
    Run r_cvc = new Run();
    r_cvc.main(null);
    /* System.setIn(new FileInputStream("src/test/resources/input_cvh.txt"));
    Run r_hvc = new Run();
    r_hvc.main(null);*/
  }

}
