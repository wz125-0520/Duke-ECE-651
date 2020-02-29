package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

public class TestSuperStack {
  @Test
  public void test_SuperStack() {board test_bd = new board();
        ArrayList<Integer> RecordHit = new ArrayList<>();
        RecordHit.add(5);
        RecordHit.add(3);
        RecordHit.add(1);
        RecordHit.add(4);
        display Show = new display();
       
            SuperStack test1 = new SuperStack();
            
            test1.CheckStack(false,12, 5, 'R', 3, test_bd, 2);
          test1.SetSuperStack('P', 'R');
          test1.SetInBoard(test_bd, 2, RecordHit);

          assert (!test1.CheckStack(false, 19, 5, 'U', 3, test_bd, 3));//out of bound for row

          //assert (!test1.CheckStack(12, 9, 'R', 3,test_bd, 1));//out of bound for column
          assert (!test1.CheckStack(true,12, 0, 'L', 3, test_bd, 4));//out of bound for column, this.single_y < 0
          //test1.SetInBoard(test_bd, 4, RecordHit);
          assert (!test1.CheckStack(false,18, 0, 'U', 3, test_bd, 5));//out of bound for row, this.long_y < 0

          test1.CheckStack(false,5, 4, 'L', 3, test_bd, 5);
          test1.SetSuperStack('P', 'L');
          test1.SetInBoard(test_bd, 5, RecordHit);
          test1.CheckStack(false,5, 4, 'L', 3, test_bd, 5);

          test1.CheckStack(false,10, 7, 'D', 3, test_bd, 0);
          test1.SetSuperStack('P', 'D');
          test1.SetInBoard(test_bd, 9, RecordHit);

          Show.DrawSingleBoard(test_bd);

          test1.CheckStack(true,10, 7, 'D', 3, test_bd, 9);
          test1.CheckStack(true,9, 8, 'U', 3, test_bd, 7);
          test1.CheckStack(false, 10, 7, 'D', 3, test_bd, 7);

          test1.CheckStack(false, 5, 8, 'U', 3, test_bd, 7);
          test1.SetSuperStack('P', 'U');
          test1.SetInBoard(test_bd, 5, RecordHit);

          test1.CheckStack(true,8, 6, 'L', 3, test_bd, 5);
          test1.SetSuperStack('P', 'L');
          test1.SetInBoard(test_bd, 5, RecordHit);

          Show.DrawSingleBoard(test_bd);
          test1.CheckStack(false, 8, 6, 'L', 3, test_bd, 5);
          test1.CheckStack(false, 8, 6, 'R', 3, test_bd, 7);

          test1.CheckStack(true, 10, 7, 'R', 3, test_bd, 5);
       
        assert(!SetSeparateStack.IsValidInput('U', '9', 'U'));
        assert(!SetSeparateStack.IsValidInput('D', 'I', 'R'));
        assert(SetSeparateStack.IsValidInput('D', '9', 'L'));
        assert(!SetSeparateStack.IsValidInput('U', '9', 'M'));
    

  }

}
