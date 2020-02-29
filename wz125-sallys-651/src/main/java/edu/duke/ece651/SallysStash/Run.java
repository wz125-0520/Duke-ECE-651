package edu.duke.ece651.SallysStash;

import java.util.*;
public class Run {
    public static void main(String[] args) {
      Scanner s = new Scanner(System.in);
        SallysStash Run = new SallysStash(s);
        Run.Init();//init the game
        Run.Play();//play game
        return;
    }
}

