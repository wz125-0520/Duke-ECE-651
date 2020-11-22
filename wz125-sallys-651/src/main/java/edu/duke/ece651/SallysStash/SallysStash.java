package edu.duke.ece651.SallysStash;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
public class SallysStash extends JFrame implements ActionListener{
        private player PlayerA;
        private player PlayerB;
        private int turn;
        SallysStash(Scanner s) {
            PlayerA = new player('A', 'B');
            PlayerB = new player('B', 'A');
        }
        public void PaneChoose(int turnNum){
            turn = turnNum;
            JButton ComputerBtn, HumanBtn;
            Container c = getContentPane();
            c.setLayout(new FlowLayout());
            ComputerBtn = new JButton("Computer");
            HumanBtn = new JButton("Player");
            ComputerBtn.addActionListener(this);
            HumanBtn.addActionListener(this);
            c.add(ComputerBtn);
            c.add(HumanBtn);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean IsComputer = false;
        System.out.println("Action activated!!" + turn);
        if(e.getActionCommand().equals("Computer")){
            IsComputer = true;
        }
        else if(e.getActionCommand().equals("Player")){
            IsComputer = false;
        }
        if(turn == 1){
            PlayerA.setIsComputer(IsComputer);
        }
        else{
            PlayerB.setIsComputer(IsComputer);
            Init();
            Play();
        }

    }
    public void Init() {
            PlayerA.Start(3);
            PlayerB.Start(3);
        }

        public void Play() {
            int total_times = 43;
            while (PlayerA.getOwnBoard().TotalHit() < total_times) {
                PlayerA.WaitForOperation(PlayerB.getOwnBoard());
                if (PlayerB.getOwnBoard().TotalHit() == total_times) {
                    System.out.println("Player A won the game!");
                    break;
                }
                PlayerB.WaitForOperation(PlayerA.getOwnBoard());
            }
            if(PlayerA.getOwnBoard().TotalHit() == total_times) {
                System.out.println("Player B won the game!");
            }
        }

}
