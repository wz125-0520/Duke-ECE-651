package edu.duke.ece651.SallysStash;

import java.util.HashMap;

public class display{
    public void DrawSingleBoard(board bd){
        System.out.println("-----------------------");
        NumSymbol();
        System.out.println(' ');
        for(int i = 0; i < 20; i++){
            System.out.print((char)('A' + i) + " ");
            for(int j = 0; j < 10; j++){
                if(bd.GetPixelColor(i,j) == 'E'){
                    System.out.print(' ');
                }
                else{
                    System.out.print(bd.GetPixelColor(i,j));
                }
                if(j != 9){
                    System.out.print('|');
                }
            }

            System.out.println(" " + (char)('A' + i));
        }
        NumSymbol();
        System.out.println(' ');
        System.out.println("-----------------------");

    }
    public void NumSymbol() {
        System.out.print("  ");
        for(int i = 0; i < 10; i++){
            System.out.print(i);
            if(i != 9) {
                System.out.print('|');
            }
        }

    }
    public void DrawTwoBoard(board myself, board guess){
        NumSymbol();
        System.out.print("\t\t\t\t");
        NumSymbol();
        System.out.println(' ');
        for(int i = 0; i < 20; i++){
            PrintMyselfBoard(myself, i);
            System.out.print("\t\t\t\t");
            PrintOtherBoard(guess, i);
        }
        NumSymbol();
        System.out.print("\t\t\t\t");
        NumSymbol();
        System.out.println(' ');
        System.out.println("----------------------------------------------------------");

    }
    public void PrintMyselfBoard(board myself, int i){
        System.out.print((char)('A' + i) + " ");
        for(int j = 0; j < 10; j++) {
            if (myself.GetPixelIsHit(i, j)) {
                System.out.print('*');
            }
            else {
                if (myself.GetPixelColor(i, j) == 'E') {
                    System.out.print(' ');
                } else {
                    System.out.print(myself.GetPixelColor(i, j));
                }
            }
            if(j != 9){
                System.out.print('|');
            }

        }
        System.out.print(" " + (char)('A' + i));
    }
    public void PrintOtherBoard(board guess, int i){
        System.out.print((char)('A' + i) + " ");
        for(int j = 0; j < 10; j ++) {
            if (guess.GetPixelColor(i, j) != 'E') {
                System.out.print(guess.GetPixelColor(i, j));
            }
            else if (guess.GetPixelIsMiss(i, j)) {
                System.out.print('X');
            }
            else {
                System.out.print(' ');
            }
            if(j != 9){
                System.out.print('|');
            }

        }
        System.out.println(" " + (char)('A' + i));
    }
  public void PrintWhole(boolean isComputer, board MyBoard, board OtherBoard, char who, char other){
    if(!isComputer){
        System.out.println("----------------------------------------------------------");
        System.out.println("Player " + who +"'s turn:");
        System.out.println("\t  Your tree\t\t\t\t\t    Player "+ other +"'s tree");
        DrawTwoBoard(MyBoard, OtherBoard);
    }
    }
  public void PrintOperationPrompt(boolean isComputer, char who, int timesMove, int timesSonar){
    if (!isComputer) {
      System.out.println("------------------------------------------------");
      System.out.println("Possible actions for Player " + who + ":\n");
      System.out.println("D Dig in a square");
      if (timesMove > 0) {
        System.out.println("M Move a stack to another square (" + timesMove + "remaining)");
      }
      if (timesSonar > 0) {
        System.out.println("S Sonar scan (" + timesSonar + "remaining)");
      }
      System.out.println("------------------------------------------------");
    }
    }

  public void PrintPrompt(boolean isComputer, String choice, char other, char who){
    if (!isComputer) {
      switch (choice) {
      case "D":
        System.out.println("Player " + who + ", please input a location to find " + other + "'s stack: ");
        break;
      case "M":
        System.out.println("Player " + who + ", please input a location to Move that stack: ");
        break;
      case "S":
        System.out.println("Player " + who + ", please input a location to sonar it's neighbor's stack: ");
        break;
      }
    }
    
    }
  public void PrintSonarResult(boolean isComputer,HashMap<Character, Integer> calculate){
    if(!isComputer){
        System.out.println("---------------------------------------------");
        String color;
        int number;
        try{
          for(Character character: calculate.keySet()){
            switch (character){
            case 'G':
              color = "Green";
              number = calculate.get(character);
              break;
            case 'P':
              color = "Purple";
              number = calculate.get(character);
              break;
            case 'R':
              color = "Red";
              number = calculate.get(character);
              break;
            case 'B':
              color = "Blue";
              number = calculate.get(character);
              break;
            default:
              throw new IllegalStateException("Unexpected value: " + character);
            }
            System.out.println(color + " Stacks occupy " + number + " squares");
          }
        }
        catch(IllegalStateException e){
              System.out.println("Invalid Color");
            }
        System.out.println("---------------------------------------------");
    }
    }
  public void PrintStartPrompt( char who, char other) {
    
        System.out.println("Player " + who +" , you are going place Sally’s stash on the board. Make sure Player " + other + " isn’t\n" +
                "looking! For each stack, type the coordinate of the upper left side of the stash, \n" +
                "followed by either H (for horizontal) or V (for vertical) or U(for up) or D(for down)\n" +
                "or L(for left) or R(for right). For example, M4H would place a stack horizontally\n" +
                "starting at M4 and going to the right. You have\n" +
                "2 Green stacks that are 1x2\n"+
                "3 Purple stacks that are 1x3\n" +
                "3 Red \"Superstacks\" \n" +
                "3 Blue \"Crazystacks\" \n");
    
   }
}

