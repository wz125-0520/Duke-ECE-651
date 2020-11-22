package edu.duke.ece651.SallysStash;

import java.util.*;
public class Run {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int turn = 1;
        while(turn <= 2){
            SallysStash Run = new SallysStash(s);
            Run.PaneChoose(turn);
            Run.setSize(200,200);
            Run.setVisible(true);
            turn++;
        }
        //Run.Play();//play game
        return;
    }
}

