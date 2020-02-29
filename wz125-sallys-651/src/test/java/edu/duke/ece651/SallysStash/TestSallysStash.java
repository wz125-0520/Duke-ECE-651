package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

//import org.graalvm.compiler.lir.StandardOp.MoveOp;
import org.junit.jupiter.api.Test;

public class TestSallysStash {
  @Test
  public void test_stash(){
        SallysStash test = new SallysStash(null);
        board MyBoard = new board();
        board OppoBoard = new board();
        board ShowForMe = new board();
        board ShowForOppo = new board();
        //board OwnBoard = new board();
        //board bd = new board();
        display Dis = new display();
       
        //test.Start(true, Dis, MyBoard, 'A', 'B', 3);
        board bd_2 = new board();
        //Scanner s = new Scanner(System.in);
        SallysStash test_1 = new SallysStash(null);
        test_1.Start(true, Dis, OppoBoard, 'B', 'A', 3);
        test_1.WaitForOperation(true, Dis, 'A', 'B', OppoBoard, ShowForMe, 3, 3, MyBoard);
        test_1.WaitForOperation(true, Dis, 'B', 'A', MyBoard, ShowForOppo, 3, 3, OppoBoard);
        test_1.HandleChoice(true, "D", 'D', '4', OppoBoard, MyBoard,'A', ShowForMe, 0, 0);
        test_1.HandleChoice(true, "M", 'D', '4', OppoBoard, MyBoard,'A', ShowForMe, 3, 3);
        test_1.HandleChoice(true, "S", 'D', '4', OppoBoard, MyBoard,'A', ShowForMe, 3, 3);
        board test1 = new board();
        for(int i = 0; i < 4; i++){
          test1.SetStack(i, 6, 'G', 2, 3);
          test1.SetStack(3, i, 'P', 2, 3);
        }
        ArrayList<Integer> RecordHit = new ArrayList<>();
        assert(test.CheckAll(false,'U', '4', 'H', 3, test1, 2,'P', RecordHit) == false);
        assert (test.CheckAll(true,'T', '4', 'H', 3, test1, 2, 'P', RecordHit) == true);
        test.CheckAll(false,'D', '4', 'H', 3, test1, 2,'P', RecordHit);

        for(int i = 5; i < 9; i++){
          test1.SetStack(i, 6, 'G', 2, 3);
          test1.SetStack(3, i, 'G', 2, 3);
        }
        assert(!test.CheckAll(true,'D', '4', 'H', 3, test1, 6,'R', RecordHit));
        assert(!test.CheckAll(true,'A', '6', 'U', 3, test1, 6,'R', RecordHit));
        System.out.println("Verify: board");
        Dis.DrawSingleBoard(test1);
        assert(test.CheckAll(false,'P', '4', 'R', 3, test1, 6,'R', RecordHit));
        
        assert(test.CheckAll(true,'L', '4', 'U', 3, test1, 9,'B', RecordHit));
        assert(!test.CheckAll(false,'o', '8', 'D', 3, test1, 10,'B', RecordHit));
        assert(!test.CheckAll(true,'L', '4', 'D', 3, test1, 10,'B', RecordHit));
        
        
        test.ValidInput('U', '0');
        test.ValidInput('T', 'A');
        test.ValidInput('B', '7');
        test.SonarOperation(true, test1, 'C', '3');
        test.SonarOperation(false, test1, 'C', '3');
        //test.Play();
        //Dis.DrawSingleBoard(bd);
        

  }

}
