package edu.duke.ece651.SallysStash;


import java.util.*;

public class MoveOpt {
  public boolean MoveOperation(Scanner s, boolean isComputer, char row, char column, board OwnBoard, char who) {
    if (OwnBoard.GetStack(row - 'A', column - '0').getColor() == 'E') {
      if(!isComputer){
        System.out.println("The current location does not have a stack");
      }
      return true;
    }
    int CurrID = OwnBoard.GetStack(row - 'A', column - '0').getID();
    StackInfo stack  = new StackInfo();
    ArrayList<Integer> TempList = new ArrayList<Integer>();
    TempList = stack.GetStack(CurrID);
    String input;
    if(isComputer == true){
      GenerateRandom gen = new GenerateRandom();
      input = gen.getInputRandom(3, CurrID);
    }
    else {
      System.out.println("Player " + who + ", please choose a new location to put the current stack");
      input = s.nextLine();
    }
    if(input.length() != 3){
      return true;
    }
    if(!CheckMove(isComputer, row, column, input, TempList.get(1), OwnBoard, CurrID, (char) TempList.get(0).intValue())){
      return true;
    }
    return false;
  }

  public boolean CheckMove(boolean isComputer, char BFS_row, char BFS_column, String input, int Dimension, board BD, int times, char ColorSymbol) {
        char row, column, direction;
        row = input.substring(0,1).toUpperCase().charAt(0);
        column = input.charAt(1);
        direction = input.substring(2,3).toUpperCase().charAt(0);
        rectangle TempRec = new rectangle();
        SuperStack TempSup = new SuperStack();
        CrazyStack TempCra = new CrazyStack();
        int Row = row - 'A';
        int Column = column - '0';
        if(times <= 5) {
            if(!TempRec.IsValidInput(row, column, direction)){
                return false;
            }
            if(!TempRec.CheckStack(isComputer, Row, Column, direction, Dimension, BD, times)){
                return false;
            }
        }
        else{
            if(!SetSeparateStack.IsValidInput(row, column, direction)){
                return false;
            }
            if(times >= 6 && times <=8) {
              if (!TempSup.CheckStack(isComputer, Row, Column, direction, Dimension, BD, times)) {
                    return false;
                }
            }
            else{
              if (!TempCra.CheckStack(isComputer, Row, Column, direction, Dimension, BD, times)) {
                    return false;
                }
            }
        }
        ArrayList<Integer> RecordHit = new ArrayList<Integer>();
        RecordHit = BFSForMove(BFS_row - 'A', BFS_column - '0', BD, times);
        ClearSet(TempRec, TempSup, TempCra, times, Row, Column, direction, ColorSymbol, Dimension, BD, RecordHit);
        return true;
    }

    public void ClearSet(rectangle TempRec, SuperStack TempSup, CrazyStack TempCra, int times, int Row, int Column, char direction, char ColorSymbol, int Dimension, board BD, ArrayList<Integer> RecordHit ){
        if(times <= 5) {
            TempRec.SetRectangle(Row, Column, direction, ColorSymbol, Dimension);
            TempRec.SetInBoard(BD, times, RecordHit);
        }
        else if(times >= 6 && times <=8) {
            TempSup.SetSuperStack(ColorSymbol, direction);
            TempSup.SetInBoard(BD, times, RecordHit);
        }
        else {
            TempCra.SetCrazyStack(ColorSymbol, direction);
            TempCra.SetInBoard(BD, times, RecordHit);
        }
    }

    public ArrayList<Integer> BFSForMove(int row, int column, board OwnBoard, int CurrID) {
        ArrayList<Integer> HitNum = new ArrayList<Integer>();
        ArrayList<pixel> visited = new ArrayList<pixel>();
        Queue<pixel> Q = new LinkedList<pixel>();
        Queue<Pair> Q_xy = new LinkedList<>();
        pixel temp_pixel = OwnBoard.GetStack(row, column);
        visited.add(temp_pixel);
        if(temp_pixel.getIsHit()){
            HitNum.add(temp_pixel.getNum());
        }
        temp_pixel.setColor('E');
        temp_pixel.setIsHit(false);
        Q.add(temp_pixel);
        Pair xy = new Pair(row, column);
        Q_xy.add(xy);
        ExecuteBFS(OwnBoard, CurrID, HitNum, visited, Q, Q_xy);
        return HitNum;
    }

    public void ExecuteBFS(board OwnBoard, int CurrID, ArrayList<Integer> hitNum, ArrayList<pixel> visited, Queue<pixel> q, Queue<Pair> q_xy) {
        while(!q.isEmpty()){
            pixel current = q.poll();
            visited.add(current);
            current.setColor('E');
            current.setIsHit(false);
            Pair Curr_xy = q_xy.poll();
            int x = Curr_xy.getX();
            int y = Curr_xy.getY();
            for(int i = x - 1; i <= x + 1; i++){
                for(int j = y - 1; j <= y + 1; j++){
                    if((Math.abs(i - x) + Math.abs(j - y)) <= 1 && i >= 0 && i < 20 && j >= 0 && j < 10){
                        if(OwnBoard.GetStack(i, j).getID() == CurrID && !visited.contains(OwnBoard.GetStack(i, j))){
                            if(OwnBoard.GetPixelIsHit(i, j)){
                                hitNum.add(OwnBoard.GetPixelNum(i, j));
                            }
                            q.add(OwnBoard.GetStack(i, j));
                            Pair put_xy = new Pair(i,j);
                            q_xy.add(put_xy);
                        }
                    }
                }

            }
            current.setID(0);
        }
    }
}
