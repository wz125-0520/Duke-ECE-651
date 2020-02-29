package edu.duke.ece651.SallysStash;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

public class TestRectangle {
  @Test
  public void test_Rectangle() {
    rectangle test = new rectangle();
    rectangle testV = new rectangle();
    test.SetRectangle(12, 5, 'H', 'G', 4);
    testV.SetRectangle(12, 5, 'V', 'G', 4);
    assert (test.IsValidInput('U', '8', 'H') == false);
    assert (test.IsValidInput('G', 'A', 'H') == false);
    assert (test.IsValidInput('B', '8', 'V') == true);
    assert (test.IsValidInput('B', '8', 'H') == true);
    assert (test.IsValidInput('R', '6', 'F') == false);
    board TestBoard = new board();
    ArrayList<Integer> RecordHit = new ArrayList<Integer>();
    RecordHit.add(1);
    RecordHit.add(2);
    RecordHit.add(3);
    RecordHit.add(5);
    test.SetInBoard(TestBoard, 5, RecordHit);

    testV.SetInBoard(TestBoard, 6, RecordHit);

    test.SetRectangle(6, 5, 'V', 'P', 5);
    test.SetInBoard(TestBoard, 4, RecordHit);
    boolean isComputer = false;
    test.CheckStack(isComputer, 6, 5, 'V', 4, TestBoard, 5);

    test.SetRectangle(7, 3, 'V', 'P', 5);
    test.SetInBoard(TestBoard, 5, RecordHit);
    test.CheckStack(false, 7, 3, 'V', 4, TestBoard, 5);

    test.CheckStack(isComputer, 12, 4, 'H', 4, TestBoard, 5);
    test.CheckStack(false, 12, 4, 'V', 3, TestBoard, 5);
    test.CheckStack(isComputer, 12, 5, 'H', 4, TestBoard, 6);
    assert(!test.CheckStack(false, 17, 3, 'V', 4, TestBoard, 5));
    assert(!test.CheckStack(true, 17, 3, 'V', 4, TestBoard, 5));
    display Show = new display();
    Show.DrawSingleBoard(TestBoard);
  }

}
