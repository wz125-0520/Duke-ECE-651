package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

public class TestCrazyStack {
  @Test
  public void test_crazystack() {
    board test_bd = new board();
    ArrayList<Integer> RecordHit = new ArrayList<>();
        RecordHit.add(5);
        RecordHit.add(3);
        RecordHit.add(1);
        RecordHit.add(4);
        display Show = new display();
       
            CrazyStack test1 = new CrazyStack();
            
            test1.CheckStack(true,12, 5, 'R', 3, test_bd, 2);
          test1.SetCrazyStack('P', 'R');
          test1.SetInBoard(test_bd, 2, RecordHit);

          assert(!test1.CheckStack(false,0, 3, 'R', 3, test_bd, 0));
          assert(!test1.CheckStack(true,12, 0, 'D', 3, test_bd, 0));
          //assert(!test1.CheckStack(0, 8, 'R', 3, test_bd, 0));
          
          test1.CheckStack(false, 5, 4, 'L', 3, test_bd, 5);
          test1.SetCrazyStack('P', 'L');
          test1.SetInBoard(test_bd, 5, RecordHit);
          test1.CheckStack(true,5, 4, 'L', 3, test_bd, 5);

          test1.CheckStack(false,10, 7, 'D', 3, test_bd, 0);
          test1.SetCrazyStack('P', 'D');
          test1.SetInBoard(test_bd, 9, RecordHit);
          System.out.println("make sure test:");
          Show.DrawSingleBoard(test_bd);

          assert(test1.CheckStack(false,10, 7, 'U', 3, test_bd, 9));
          assert(!test1.CheckStack(true,9, 8, 'U', 3, test_bd, 7));

          assert(!test1.CheckStack(false, 10, 7, 'D', 3, test_bd, 7));
          test1.CheckStack(false,5, 8, 'U', 3, test_bd, 7);
          test1.SetCrazyStack('P', 'U');
          test1.SetInBoard(test_bd, 5, RecordHit);
          
          test1.CheckStack(false,8, 4, 'L', 3, test_bd, 5);
          test1.SetCrazyStack('P', 'L');
          test1.SetInBoard(test_bd, 5, RecordHit);

          board test2 = new board();
          test1.CheckStack(false,1, 3, 'L', 3, test2, 0);
          test1.SetCrazyStack('B', 'L');
          test1.SetInBoard(test2, 5, RecordHit);
          Show.DrawSingleBoard(test2);
         
          assert(!test1.CheckStack(false,1, 3, 'L', 3, test2, 11));
          assert(test1.CheckStack(false,10, 7, 'U', 3, test2, 5));
          assert (!test1.CheckStack(true,19, 10, 'L', 3, test2, 10));
          
          //assert(!test1.CheckStack(0, 1, 'L', 3, test2, 11));

          assert(!test1.CheckStack(true,0, 1, 'L', 3, test2, 11));
          //Show.DrawSingleBoard(test_bd);
          //assert(!test1.CheckStack(1, 3, 'R', 3, test2, 11));
          board new_test = new board();
          assert(test1.CheckStack(false, 5, 1, 'U', 3, new_test, 8));
          test1.SetCrazyStack('B', 'U');
          test1.SetInBoard(new_test, 0, RecordHit);
          int ID = new_test.GetStack(5, 1).getID();
          
          test1.ExistStack(0, 0, 5, 1, 'U', 3, new_test, ID);

  }

}
