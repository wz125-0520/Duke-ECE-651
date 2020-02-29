package edu.duke.ece651.SallysStash;

import org.junit.jupiter.api.Test;

public class TestBoard {
    @Test
    public void test_board(){
        board TestBoard = new board();
        board TestOppoBoard = new board();

        for(int i = 0; i < 4; i++){
            TestBoard.SetStack(i, 0, 'G', 2, 3);
            TestBoard.SetStack(i, 3, 'G', 3, 3);
            TestBoard.SetPixelIsHit(0, 0, true);
            TestOppoBoard.SetPixelIsMiss(3, 3, true);
            TestOppoBoard.SetPixelColor(19, 9, 'B');
            System.out.println("GetPixelColorTest:" + TestBoard.GetPixelColor(i, 0));
            System.out.println("GetPixelIsMissTest:" + TestBoard.GetPixelIsMiss(i, 0));
            System.out.println("GetPixelIsHitTest:" + TestBoard.GetPixelIsHit(i, 1));
        }

        for(int i = 0; i < 3; i++) {
            TestBoard.SetStack(15, i, 'P', 1, 3);
            TestBoard.SetStack(5, i, 'P', 4, 3);
        }
        TestBoard.SetStatus(false,19,9, TestOppoBoard,'A');
        assert (TestBoard.GetPixelNum(0,0) == 3);
        TestBoard.SetPixelColor(10, 9, 'R');
        assert (TestBoard.GetPixelColor(10, 9) == 'R');
        TestOppoBoard.SetStatus(true, 10, 9, TestBoard, 'B');
        TestBoard.SetStatus(true,0, 3, TestOppoBoard ,'A');
        
        TestBoard.SetStatus(true ,5, 0, TestOppoBoard ,'A');
        TestBoard.SetStatus(false,1, 4, TestOppoBoard ,'A');
        TestBoard.SetStatus(false,5, 0, TestOppoBoard ,'A');
        pixel test = TestBoard.GetStack(0, 3);
        System.out.println("Total Hit Number:" + TestBoard.TotalHit());
    }
}
