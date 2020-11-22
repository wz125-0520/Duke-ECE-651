package edu.duke.ece651.player;

import edu.duke.ece651.shared.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static javafx.scene.text.FontPosture.ITALIC;
import static javafx.scene.text.FontWeight.BOLD;

public class DoneAction {
    //------------- Evolution 2 --------------//
    private PlayerHelper CurrPlayer;
    private String ValidationResult;

    @FXML private Label Result;
    @FXML private Label Food;
    @FXML private Label Tech;
    @FXML private Label AllianceInfo;
    @FXML private TreeView<String> Actions;


    private HashMap<String, Button> ButtonMap;
    private HashMap<Integer, ArrayList<Territory> > TerrMap;

    @FXML private Button UpgradeBtn;
    @FXML private Button MoveBtn;
    @FXML private Button AttackBtn;
    @FXML private Button DoneBtn;

    @FXML private Button ButtonA;
    @FXML private Button ButtonB;
    @FXML private Button ButtonC;
    @FXML private Button ButtonD;
    @FXML private Button ButtonE;
    @FXML private Button ButtonF;
    @FXML private Button ButtonG;
    @FXML private Button ButtonH;
    @FXML private Button ButtonI;
    @FXML private Button ButtonJ;
    @FXML private Button ButtonK;
    @FXML private Button ButtonL;

    @FXML private Label Prompt;
    @FXML private ImageView Figure;


    private Stage Window;
    //create a button map which can relate the Territory name to the Button
    private void InitButtonMap(){
        ButtonMap = new HashMap<>();
        ButtonMap.put("A", ButtonA);
        ButtonMap.put("B", ButtonB);
        ButtonMap.put("C", ButtonC);
        ButtonMap.put("D", ButtonD);
        ButtonMap.put("E", ButtonE);
        ButtonMap.put("F", ButtonF);
        ButtonMap.put("G", ButtonG);
        ButtonMap.put("H", ButtonH);
        ButtonMap.put("I", ButtonI);
        ButtonMap.put("J", ButtonJ);
        ButtonMap.put("K", ButtonK);
        ButtonMap.put("L", ButtonL);
    }
    public DoneAction(PlayerHelper CurrPlayer, String Validation, Stage Window){
        this.CurrPlayer = CurrPlayer;
        this.ValidationResult = Validation;
        this.TerrMap = CurrPlayer.getTerritoryMap();
        this.Window = Window;
    }

    public void initialize(){
        //Set the profile photo of player
        SharedMethod.InitFigure(this.CurrPlayer, this.Figure);
        //Show map
        InitButtonMap();
        new Graph().showMap(this.CurrPlayer.getTerritoryMap(), this.CurrPlayer.getPlayerInfo(), this.ButtonMap);
        //init tooltip with territory information
        SharedMethod.InitTerritoryDetail(this.ButtonMap, this.TerrMap);
        //display the result of validating the player's actions
        this.Result.setText(this.ValidationResult);
        //display the actions of all the players
        Graph Display = new Graph();
        Display.showAction(this.CurrPlayer.getAllAction(), this.CurrPlayer.getPlayerInfo(), this.Actions);
        ColorID PlayerColor = new ColorID();
        String PlayerName = PlayerColor.getPlayerColor(this.CurrPlayer.getPlayerInfo().getKey());
        //print the prompt
        this.Prompt.setText("Your territories are in " + PlayerName + " Color, please choose an action");
        this.Prompt.setFont(Font.font("Arial", BOLD, ITALIC, 18));
        this.Food.setText(String.valueOf(this.CurrPlayer.getFoodResource()));
        this.Tech.setText(String.valueOf(this.CurrPlayer.getTechResource()));
        this.AllianceInfo.setText(SharedMethod.getAllianceInfo(this.CurrPlayer));
    }

    @FXML
    public void Upgrading() throws IOException {
        System.out.println("Click on Upgrade");
        FXMLLoader loaderStart = new FXMLLoader(getClass().getResource("/Views/UpgradeChoose.fxml"));
        loaderStart.setControllerFactory(c->{
            return new UpgradeChoose(this.CurrPlayer, Window);
        });
        Scene scene = new Scene(loaderStart.load());
        this.Window.setScene(scene);
        this.Window.show();
    }

    @FXML
    public void ChooseMove() throws IOException {
        System.out.println("Click on Move");
        FXMLLoader loaderStart = new FXMLLoader(getClass().getResource("/Views/Move_attack.fxml"));
        loaderStart.setControllerFactory(c->{
            return new MoveOrAttack(this.CurrPlayer, "Move", this.Window);
        });
        Scene scene = new Scene(loaderStart.load());
        this.Window.setScene(scene);
        this.Window.show();
    }

    @FXML
    public void ChooseAttack() throws IOException {
        System.out.println("Click on Attack");
        FXMLLoader loaderStart = new FXMLLoader(getClass().getResource("/Views/Move_attack.fxml"));
        loaderStart.setControllerFactory(c->{
            return new MoveOrAttack(this.CurrPlayer, "Attack", this.Window);
        });
        Scene scene = new Scene(loaderStart.load());
        this.Window.setScene(scene);
        this.Window.show();
    }

    @FXML
    public void ChooseAlliance() throws IOException {
        System.out.println("Click on Alliance");
        FXMLLoader loaderStart = new FXMLLoader(getClass().getResource("/Views/Alliance.fxml"));
        loaderStart.setControllerFactory(c->{
            return new AllianceController(this.CurrPlayer, this.Window);
        });
        Scene scene = new Scene(loaderStart.load());
        this.Window.setScene(scene);
        this.Window.show();
    }

    @FXML
    public void ChooseDone() throws IOException {

        //Clicked the Done Button, show a window to remind players
        //if it choose done, receive this turn's result information
        System.out.println("Click on Done in Map");
        this.CurrPlayer.SendAction();


        String Validation = this.CurrPlayer.ReceiveActionRes();
        System.out.println("Validation " + Validation);

        String AllianceResult = this.CurrPlayer.receiveString();

        if(AllianceResult.contains("Successfully")){
            int AllyID = this.CurrPlayer.getAllianceAction().getAlly();
            this.CurrPlayer.setMyAlly(AllyID);
        }
        else if(AllianceResult.contains("broken")){
            this.CurrPlayer.setMyAlly(-1);
        }
        this.CurrPlayer.ReceiveAllAction();

        this.CurrPlayer.AddTechResource(this.CurrPlayer.getTerritoryMap(),this.CurrPlayer.getPlayerInfo());
        //the answer could be map or lose game and game end
        String Answer = this.CurrPlayer.receiveString();

        //After received actions from server, close the waiting window

        //check whether the received string is game end or lose game or normal map
        //display different page with different received string content
        if(Answer.contains("Game End!")){
            System.out.println("Received Game End");
            ShowView.ShowEndVIew(Answer, this.CurrPlayer, this.Window);
        }
        else if(Answer.contains("Lose Game")){
            System.out.println("Received Lose Game");
            this.CurrPlayer.setAsk(true);
            this.CurrPlayer.setLose(true);
            ShowView.ShowLoseView(Validation, this.CurrPlayer, this.Window);
        }
        else {
            System.out.println("Normal Received Map");
            this.CurrPlayer.ContinueReceive(Answer);

            ShowView.ShowDoneView(Validation, this.CurrPlayer, this.Window);
        }
    }
}
