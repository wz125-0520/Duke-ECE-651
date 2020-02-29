package edu.duke.ece651.SallysStash;

import org.junit.jupiter.api.Test;
import java.util.*;

public class TestDisplay {
    @Test
    public  void test_display(){
        board TestBoard = new board();
        board TestOppoBoard = new board();

        for(int i = 0; i < 4; i++){
            TestBoard.SetStack(i, 0, 'G', 2, 3);
            //TestBoard.SetStack(10, 5, 'M', 2, 3);
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

        TestBoard.SetStatus(false,19,9, TestOppoBoard, 'A');
        display Show = new display();
        boolean isComputer = false;
        Show.DrawSingleBoard(TestBoard);
        
        Show.DrawTwoBoard(TestBoard, TestOppoBoard);
        Show.PrintWhole(isComputer,TestBoard, TestOppoBoard, 'A', 'B');
        Show.PrintOperationPrompt(isComputer,'A', 3, 3);
        Show.PrintOperationPrompt(isComputer,'B', 0, 3);
        Show.PrintOperationPrompt(isComputer,'C', 3, 0);
        Show.PrintPrompt(isComputer,"M", 'A', 'B');
        Show.PrintPrompt(isComputer,"D", 'A', 'B');
        Show.PrintPrompt(isComputer,"S", 'A', 'B');
        Show.PrintStartPrompt('A', 'B');
        HashMap<Character, Integer> calculate = new HashMap<Character, Integer>();
        calculate.put('G', 3);
        calculate.put('P', 3);
        calculate.put('R', 3);
        calculate.put('B', 3);
        calculate.put('M', 6);
        Show.PrintSonarResult(isComputer,calculate);
        isComputer = true;
        
        //Show.DrawSingleBoard(TestBoard);
        
        //Show.DrawTwoBoard(TestBoard, TestOppoBoard);
        Show.PrintWhole(isComputer,TestBoard, TestOppoBoard, 'A', 'B');
        Show.PrintOperationPrompt(isComputer,'A', 3, 3);
        Show.PrintOperationPrompt(isComputer,'B', 0, 3);
        Show.PrintOperationPrompt(isComputer,'C', 3, 0);
        Show.PrintPrompt(isComputer,"M", 'A', 'B');
        Show.PrintPrompt(isComputer,"D", 'A', 'B');
        Show.PrintPrompt(isComputer,"S", 'A', 'B');
        //Show.PrintStartPrompt('A', 'B');
        
        Show.PrintSonarResult(isComputer,calculate);

    }

}
