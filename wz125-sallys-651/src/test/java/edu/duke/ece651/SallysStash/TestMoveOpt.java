package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

public class TestMoveOpt {
  @Test
  public void test_moveopt() {
    /*board MyBoard = new board();
    for(int i = 1; i < 11; i++){
      if(i <= 
      MyBoard.SetStack(0, 0, 0, 0, 0);*/
    MoveOpt test_move = new MoveOpt();
    board testMov1 = new board();
    testMov1.SetStack(5, 5, 'R', 1, 1);
    testMov1.SetStack(5, 6, 'R', 1, 2);
    testMov1.SetStack(5, 7, 'R', 1, 3);//vertical rectangle


    testMov1.SetStack(9, 1, 'G', 2, 1);
    testMov1.SetStack(10, 1, 'G', 2, 2);
    testMov1.SetStack(11, 1, 'G', 2, 3);
    
    board Opt = new board();
    Opt.SetStack(5, 5, 'R', 1, 1);
    Opt.SetStack(5, 6, 'R', 1, 2);
    Opt.SetStack(5, 7, 'R', 1, 3);//vertical rectangle


    Opt.SetStack(9, 1, 'G', 2, 1);
    Opt.SetStack(10, 1, 'G', 2, 2);
    Opt.SetStack(11, 1, 'G', 2, 3);
    test_move.MoveOperation(null, true, 'F', '5', Opt, 'A');
    
    assert(test_move.CheckMove(true, 'F', '5', "G4h", 3, testMov1, 3, 'R')==true);
    assert(test_move.CheckMove(true, 'F', '5', "J1h", 3, testMov1, 3, 'R')==false);
    
    assert(test_move.CheckMove(true, 'F', '5', "h33", 3, testMov1, 3, 'R')==false);

    //tims 6-8
    assert(test_move.CheckMove(true, 'J', '1', "T5h", 5, testMov1, 8, 'R')==false);
    assert(test_move.CheckMove(true, 'J', '1', "f5u", 5, testMov1, 8, 'R')==false);
    assert(test_move.CheckMove(true, 'J', '1', "o5r", 5, testMov1, 8, 'R')==true);

    testMov1.SetStack(13, 5, 'G', 2, 1);
    testMov1.SetStack(14, 5, 'G', 2, 2);
    testMov1.SetStack(15, 5, 'G', 2, 3);
    //tims 9-11

    
    assert(test_move.CheckMove(true, 'N', '5', "k1u", 5, testMov1, 10, 'R')==false);
    assert(test_move.CheckMove(true, 'N', '5', "f7d", 5, testMov1, 10, 'R')==false);
    assert(test_move.CheckMove(true, 'N', '5', "k8d", 5, testMov1, 10, 'R')==true);
    testMov1.SetStack(16, 7, 'G', 3, 1);
    testMov1.GetStack(16, 7).setIsHit(true);
    testMov1.SetStack(17, 7, 'G', 3, 2);
    testMov1.SetStack(18, 7, 'G', 3, 3);
    testMov1.SetStack(16, 8, 'G', 3, 3);
    testMov1.GetStack(16, 8).setIsHit(true);
    testMov1.SetStack(16, 6, 'G', 3, 3);
    test_move.BFSForMove(16, 7, testMov1, 3);
    display Show = new display();
    Show.DrawSingleBoard(testMov1);
  }
 

}
