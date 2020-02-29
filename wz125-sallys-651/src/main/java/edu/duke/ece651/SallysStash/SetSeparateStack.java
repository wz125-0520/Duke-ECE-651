package edu.duke.ece651.SallysStash;

import java.util.ArrayList;

public class SetSeparateStack {
  public static void SetRow(board bd, int ID, int start_x, int start_y, char color, int Num, ArrayList<Integer> RecordHit){
        for(int i = 0; i < 3; i++){
            bd.SetStack(start_x, start_y + i, color, ID, Math.abs(Num - i) + 1 );
            if(RecordHit.contains(Math.abs(Num - i) + 1)){
                bd.SetPixelIsHit(start_x, start_y + i, true);
            }
        }
    }
    public static void SetColumn(board bd, int ID, int start_x, int start_y, char color, int Num, ArrayList<Integer> RecordHit){
        for(int i = 0; i < 3; i++){
            bd.SetStack(start_x + i, start_y, color, ID, Math.abs(Num - i) + 1);
            if(RecordHit.contains(Math.abs(Num - i) + 1)){
                bd.SetPixelIsHit(start_x + i, start_y, true);
            }
        }
    }
    public static boolean IsValidInput(char row, char column, char direction){
        if (direction != 'U' && direction != 'R' && direction != 'D' && direction != 'L'){
            System.out.println("The input is not valid! The third character needs to be U/R/D/L or u/r/d/l.");
        }
        else if(row > 'T' || row < 'A'){
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
}
