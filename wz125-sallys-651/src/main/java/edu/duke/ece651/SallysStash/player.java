package edu.duke.ece651.SallysStash;


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class player {
    private board OwnBoard;
    private board OppositeShow;
    private int timesMove;
    private int timesSonar;
    private boolean IsComputer;
    public char Who;
    public char Other;
    private Scanner s;
    private boolean Done;

    player(char CurrentUser, char OppositeUser){
        OwnBoard = new board();
        OppositeShow = new board();
        s = new Scanner(System.in);
        timesMove = 3;
        timesSonar = 3;
        Who = CurrentUser;
        Other = OppositeUser;
        Done = false;
    }

    public board getOwnBoard(){
        return OwnBoard;
    }
    public boolean getDone(){
        return Done;
    }


    public void setIsComputer(boolean Computer){
        IsComputer = Computer;
    }

    public void Start(int length) {
        display Dis = new display();
        if (!IsComputer) {
            Dis.PrintStartPrompt(Who, Other);
            Dis.DrawSingleBoard(OwnBoard);
        }
        GetInput(length);
    }
    public void GetInput(int length) {
        display Dis = new display();
        int times = 1;
        ArrayList<Integer> EmptyStart = new ArrayList<Integer>();
        while(times <= 11) {
            InitBoard(times, length, EmptyStart);
            if (!IsComputer) {
                Dis.DrawSingleBoard(OwnBoard);
            }
            times++;
        }
    }

    public void InitBoard(int times,int length, ArrayList<Integer> RecordHit) {
        StackInfo stack  = new StackInfo();
        if (!IsComputer) {
            System.out.println("Player " + Who + ", where do you want to place the " + stack.GetPrompt(times) + " stack?");
        }
        ArrayList<Integer> TempList = new ArrayList<Integer>();
        TempList = stack.GetStack(times);
        ParseIfValidInput(TempList.get(1), (char) TempList.get(0).intValue(), times, length, RecordHit);
    }

    public void ParseIfValidInput(int Dimension,char ColorSymbol, int times, int length, ArrayList<Integer> RecordHit){
        String input;
        while(true){
            if(IsComputer){
                GenerateRandom gen = new GenerateRandom();
                input = gen.getInputRandom(3, times);
            }
            else {
                input = s.nextLine();
            }
            if(input.length() != length){
                System.out.println("The input is not valid! It needs to contain three characters.");
                continue;
            }
            char row, column, direction;
            row = input.substring(0,1).toUpperCase().charAt(0);
            column = input.charAt(1);
            direction = input.substring(2,3).toUpperCase().charAt(0);
            if(!CheckAll(row, column, direction, Dimension,times, ColorSymbol, RecordHit)){
                continue;
            }
            break;
        }
    }

    public boolean CheckAll( char row, char column, char direction, int Dimension, int times, char ColorSymbol, ArrayList<Integer> RecordHit) {
        int Row = row - 'A';
        int Column = column - '0';
        if(times <= 5) {
            rectangle TempRec = new rectangle();
            if(!TempRec.IsValidInput(row, column, direction) ){
                return false;
            }
            if(!TempRec.CheckStack(IsComputer,Row, Column, direction, Dimension, OwnBoard, 0)){
                return false;
            }
            TempRec.SetRectangle(Row, Column, direction, ColorSymbol, Dimension);
            TempRec.SetInBoard(OwnBoard, times, RecordHit);
        }
        else{
            if(!SetSeparateStack.IsValidInput(row, column, direction)){
                return false;
            }
            if(times >= 6 && times <=8) {
                SuperStack TempSup = new SuperStack();
                if (!TempSup.CheckStack(IsComputer, Row, Column, direction, Dimension, OwnBoard, 0)) {
                    return false;
                }
                TempSup.SetSuperStack(ColorSymbol, direction);
                TempSup.SetInBoard(OwnBoard, times, RecordHit);
            }
            else{
                CrazyStack TempCra = new CrazyStack();
                if (!TempCra.CheckStack(IsComputer, Row, Column, direction, Dimension, OwnBoard, 0)) {
                    return false;
                }
                TempCra.SetCrazyStack(ColorSymbol, direction);
                TempCra.SetInBoard(OwnBoard, times, RecordHit);
            }
        }
        return true;
    }
    public void  WaitForOperation(board EnemyBoard) {
        display Dis = new display();
        Dis.PrintWhole(IsComputer, OwnBoard, OppositeShow, Who, Other);
        String choice;
        GenerateRandom gen = new GenerateRandom();
        while (true) {
            Dis.PrintOperationPrompt(IsComputer, Who, timesMove, timesSonar);
            choice = (IsComputer == true) ? gen.getInputChoice(timesMove,timesSonar).toUpperCase() : s.nextLine().toUpperCase();
            String location;
            char row, column;
            if (choice.equals("D") || choice.equals("M") || choice.equals("S")) {
                if(timesMove <= 0 && choice.equals("M")){
                    System.out.println("Your 3 Move chances are already used out. Try again!");
                    continue;
                }
                else if(timesSonar == 0 && choice.equals("S")){
                    System.out.println("Your 3 Sonar chances are already used out. Try again!");
                    continue;
                }
                Dis.PrintPrompt(IsComputer, choice, Other, Who);
                location = IsComputer == true ? gen.getInputRandom(2, 0) : s.nextLine();
                if (location.length() != 2) {
                    System.out.println("The input is not valid! It needs to be two characters.");
                    continue;
                }
                row = location.substring(0, 1).toUpperCase().charAt(0);
                column = location.charAt(1);
                if (!ValidInput(row, column)) {//not valid
                    continue;
                }
                if(!HandleChoice(choice, row, column, EnemyBoard)){
                    continue;
                }
                break;
            }
            else {
                System.out.println("Option is not valid, please try again. M or S or D");
                continue;
            }
        }
    }
    public boolean HandleChoice(String choice, char row, char column, board EnemyBoard){
        if (choice.equals("D")) {
            OppositeShow.SetStatus(IsComputer, row - 'A', column - '0', EnemyBoard, Who);
            return true;
        }
        else if (choice.equals("M") && timesMove > 0) {//"M"
            MoveOpt Move = new MoveOpt();
            if (Move.MoveOperation(s, IsComputer, row, column, OwnBoard, Who)) {
                return false;
            }
            timesMove--;
        }
        else if(choice.equals("S") && timesSonar > 0) {
            SonarOperation(EnemyBoard, row, column);
            timesSonar--;
        }
        if (IsComputer && (choice.equals("M") || choice.equals("S"))) {
            System.out.println("Player " + Who + " used a special action");
        }
        return true;
    }
    public boolean ValidInput(char row, char column){
        if(row > 'T' || row < 'A'){
            System.out.println("The input is not valid! The first character needs to be a~t or A~T.");
        }
        else if(column < '0' || column > '9'){
            System.out.println("The input is not valid! The column needs in the range of 0~9.");
        }
        else{
            return true;
        }
        return false;
    }
    public void SonarOperation(board OtherBoard, char row, char column) {
        HashMap<Character, Integer> calculate = new HashMap<Character, Integer>();
        calculate.put('G', 0);
        calculate.put('P', 0);
        calculate.put('R', 0);
        calculate.put('B', 0);
        int x = row - 'A';
        int y = column - '0';
        for (int i = x - 3; i <= x + 3; i++) {
            for (int j = y - 3; j <= y + 3; j++) {
                if (Math.abs(i - x) + Math.abs(j - y) <= 3) {
                    if((i >= 0 && i < 20) && (j >= 0 && j < 10)) {
                        char color = OtherBoard.GetStack(i, j).getColor();
                        if(color != 'E') {
                            int num = calculate.get(color) + 1;
                            calculate.put(color, num);
                        }
                    }
                }
            }
        }
        display Dis = new display();
        Dis.PrintSonarResult(IsComputer, calculate);
    }

}
