package edu.duke.ece651.SallysStash;

import java.util.*;

public class SallysStash {
    private board A_board;
    private board B_board;
    private board AShowForB;
    private board BShowForA;
    private int timesMove_A;
    private int timesSonar_A;
    private int timesMove_B;
    private int timesSonar_B;
    private boolean isComputer_B, isComputer_A;
    private Scanner s;
    SallysStash(Scanner s) {
        this.s = new Scanner(System.in);
        this.timesMove_A = 3;
        this.timesSonar_A = 3;
        this.timesMove_B = 3;
        this.timesSonar_B = 3;
        this.isComputer_A = false;
        this.isComputer_B = false;

    }

    public void ChooseCharacter(char who){
        //  Scanner s = new Scanner(System.in);
        String For;
        while(true) {
            System.out.println("Please choose Player" + who + ": 1 human 2 computer");
            For = this.s.nextLine();
            if (For.equals("2")) {
                if (who == 'A'){
                    this.isComputer_A = true;
                }
                else{
                    this.isComputer_B = true;
                }
                break;
            }
            else if(For.equals("1")){
                break;
            }
            else{
                System.out.println("Input Not Valid. Please choose 1 human or 2 computer.");
                continue;
            }
        }
    }

    public void Init() {
        ChooseCharacter('A');
        ChooseCharacter('B');
        display Display = new display();
        this.A_board = new board();
        Start(isComputer_A,Display, A_board, 'A', 'B', 3);

        this.B_board = new board();
        Start(isComputer_B, Display, B_board, 'B', 'A', 3);
    }

    public void Play() {
        display Display = new display();
        this.AShowForB = new board();
        this.BShowForA = new board();
        //Scanner s = new Scanner(System.in);
        int total_times = 43;
        while (A_board.TotalHit() < total_times) {
            Display.PrintWhole(isComputer_A, A_board, BShowForA, 'A', 'B');
            WaitForOperation(isComputer_A, Display, 'A', 'B', B_board,  BShowForA, this.timesMove_A, this.timesSonar_A, A_board);
            if (B_board.TotalHit() == total_times) {
                System.out.println("Player A won the game!");
                break;
            }
            Display.PrintWhole(isComputer_B, B_board, AShowForB, 'B', 'A');
            WaitForOperation(isComputer_B, Display,'B', 'A', A_board, AShowForB, this.timesMove_B, this.timesSonar_B, B_board);
        }
        if(A_board.TotalHit() == total_times) {
            System.out.println("Player B won the game!");
        }
    }

    public void  WaitForOperation(boolean isComputer, display display, char who, char other, board OppoBoard, board ShowForMe, int timesMove, int timesSonar, board OwnBoard) {
        String choice;
        GenerateRandom gen = new GenerateRandom();
        while (true) {
            display.PrintOperationPrompt(isComputer, who, timesMove, timesSonar);
            if(isComputer){
                choice = gen.getInputChoice(timesMove,timesSonar).toUpperCase();
            }
            else {
                choice = this.s.nextLine().toUpperCase();
            }
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
                display.PrintPrompt(isComputer, choice, other, who);
                if(isComputer){
                    location = gen.getInputRandom(2, 0);
                }
                else {
                    location = this.s.nextLine();
                }
                if (location.length() != 2) {
                    System.out.println("The input is not valid! It needs to be two characters.");
                    continue;
                }
                row = location.substring(0, 1).toUpperCase().charAt(0);
                column = location.charAt(1);
                if (!ValidInput(row, column)) {//not valid
                    continue;
                }
                if(!HandleChoice( isComputer, choice, row, column, OppoBoard, OwnBoard, who, ShowForMe, timesMove, timesSonar)){
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

    public boolean HandleChoice( boolean isComputer, String choice, char row, char column, board OppoBoard, board OwnBoard,  char who, board ShowForMe, int timesMove, int timesSonar){
        if (choice.equals("D")) {
            ShowForMe.SetStatus(isComputer, row - 'A', column - '0', OppoBoard, who);
            return true;
        }
        else if (choice.equals("M") && timesMove > 0) {//"M"
            MoveOpt Move = new MoveOpt();
            if (Move.MoveOperation(this.s, isComputer, row, column, OwnBoard, who)) {
                return false;
            }
            //System.out.println("Move Successfully!");
            if(A_board == OwnBoard) {
                timesMove_A--;
            }
            else {
                timesMove_B--;
            }
        }
        else if(choice.equals("S") && timesSonar > 0) {
            SonarOperation(isComputer, OppoBoard, row, column);
            if(A_board == OwnBoard) {
                timesSonar_A--;
            }
            else {
                timesSonar_B--;
            }
        }
        if (isComputer && (choice.equals("M") || choice.equals("S"))) {
            System.out.println("Player " + who + " used a special action");
        }
        return true;
    }


    public void SonarOperation(boolean isComputer, board OtherBoard, char row, char column) {
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
        Dis.PrintSonarResult(isComputer, calculate);
    }



    public void Start( boolean isComputer, display Dis, board a_board, char who, char other, int length) {
        if (!isComputer) {
            Dis.PrintStartPrompt(who, other);
            Dis.DrawSingleBoard(a_board);
        }
        GetInput( isComputer, Dis, a_board, who, length);
    }

    public void GetInput( boolean isComputer, display display, board init_board, char who, int length) {
        //Scanner s = new Scanner(System.in);
        int times = 1;
        ArrayList<Integer> EmptyStart = new ArrayList<Integer>();
        while(times <= 11) {
            InitBoard(isComputer, display, init_board, times, who, length, EmptyStart);
            if (!isComputer) {
                display.DrawSingleBoard(init_board);
            }
            times++;
        }
    }

    public void InitBoard(boolean isComputer, display display, board init_board,  int times, char who, int length, ArrayList<Integer> RecordHit) {
        StackInfo stack  = new StackInfo();
        if (!isComputer) {
            System.out.println("Player " + who + ", where do you want to place the " + stack.GetPrompt(times) + " stack?");
        }
        ArrayList<Integer> TempList = new ArrayList<Integer>();
        TempList = stack.GetStack(times);
        ParseIfValidInput( isComputer, TempList.get(1), init_board, (char) TempList.get(0).intValue(), times, length, RecordHit);
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
    public void ParseIfValidInput( boolean isComputer, int Dimension, board InitBoard, char ColorSymbol, int times, int length, ArrayList<Integer> RecordHit){
        String input;
        while(true){
            if(isComputer){
                GenerateRandom gen = new GenerateRandom();
                input = gen.getInputRandom(3, times);
            }
            else {
                input = this.s.nextLine();
            }
            if(input.length() != length){
                System.out.println("The input is not valid! It needs to contain three characters.");
                continue;
            }
            char row, column, direction;
            row = input.substring(0,1).toUpperCase().charAt(0);
            column = input.charAt(1);
            direction = input.substring(2,3).toUpperCase().charAt(0);
            if(!CheckAll(isComputer, row, column, direction, Dimension, InitBoard, times, ColorSymbol, RecordHit)){
                continue;
            }
            break;
        }
    }

    public boolean CheckAll(boolean isComputer, char row, char column, char direction, int Dimension, board BD, int times, char ColorSymbol, ArrayList<Integer> RecordHit) {
        int Row = row - 'A';
        int Column = column - '0';
        if(times <= 5) {
            rectangle TempRec = new rectangle();
            if(!TempRec.IsValidInput(row, column, direction) ){
                return false;
            }
            if(!TempRec.CheckStack(isComputer,Row, Column, direction, Dimension, BD, 0)){
                return false;
            }
            TempRec.SetRectangle(Row, Column, direction, ColorSymbol, Dimension);
            TempRec.SetInBoard(BD, times, RecordHit);
        }
        else{
            if(!SetSeparateStack.IsValidInput(row, column, direction)){
                return false;
            }
            if(times >= 6 && times <=8) {
                SuperStack TempSup = new SuperStack();
                if (!TempSup.CheckStack(isComputer, Row, Column, direction, Dimension, BD, 0)) {
                    return false;
                }
                TempSup.SetSuperStack(ColorSymbol, direction);
                TempSup.SetInBoard(BD, times, RecordHit);
            }
            else{
                CrazyStack TempCra = new CrazyStack();
                if (!TempCra.CheckStack(isComputer, Row, Column, direction, Dimension, BD, 0)) {
                    return false;
                }
                TempCra.SetCrazyStack(ColorSymbol, direction);
                TempCra.SetInBoard(BD, times, RecordHit);
            }
        }
        return true;
    }

}
