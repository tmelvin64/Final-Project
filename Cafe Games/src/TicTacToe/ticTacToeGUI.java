package TicTacToe;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Overwatch Battles
 * This program was made by Jordan (DXHHH101) on June 2, 2016
 * 
 */
public class ticTacToeGUI extends javax.swing.JFrame {

    JButton[][] boxes = new JButton[3][3];
    int team1Score=0, team2Score=0, tieScore=0;
    int winTeam; //track who the winning team is/what killstreak accolade.
    
    ArrayList<String> pantheons = new ArrayList();
    ArrayList<Icon> iconsBig = new ArrayList();
    ArrayList<Icon> iconsSmall = new ArrayList();
    ArrayList<Icon> killStreak = new ArrayList();
    String winText, winSound, winAnnouncer;
    
    ImageIcon Hollywood=new ImageIcon(getClass().getResource("/TicTacToe/Hollywood.png")), Dorado=new ImageIcon(getClass().getResource("/TicTacToe/Dorado.png")),
            Hanamura=new ImageIcon(getClass().getResource("/TicTacToe/Hanamura.png")), Volskaya=new ImageIcon(getClass().getResource("/TicTacToe/Volskaya.png")),
            Numbani=new ImageIcon(getClass().getResource("/TicTacToe/Numbani.png")); //Initialize pictures
    
    ImageIcon BastionP=new ImageIcon(getClass().getResource("/TicTacToe/Bastion/BastionP.png")), DVaP=new ImageIcon(getClass().getResource("/TicTacToe/DVa/DVaP.png")),
            GenjiP=new ImageIcon(getClass().getResource("/TicTacToe/Genji/GenjiP.png")), HanzoP=new ImageIcon(getClass().getResource("/TicTacToe/Hanzo/HanzoP.png")),
            JunkratP=new ImageIcon(getClass().getResource("/TicTacToe/Junkrat/JunkratP.png")), LucioP=new ImageIcon(getClass().getResource("/TicTacToe/Lucio/LucioP.png")),
            McCreeP=new ImageIcon(getClass().getResource("/TicTacToe/McCree/McCreeP.png")), MeiP=new ImageIcon(getClass().getResource("/TicTacToe/Mei/MeiP.png")),
            MercyP=new ImageIcon(getClass().getResource("/TicTacToe/Mercy/MercyP.png")), PharahP=new ImageIcon(getClass().getResource("/TicTacToe/Pharah/PharahP.png")),
            ReaperP=new ImageIcon(getClass().getResource("/TicTacToe/Reaper/ReaperP.png")), ReinhardtP=new ImageIcon(getClass().getResource("/TicTacToe/Reinhardt/ReinhardtP.png")),
            RoadhogP=new ImageIcon(getClass().getResource("/TicTacToe/Roadhog/RoadhogP.png")), Soldier76P=new ImageIcon(getClass().getResource("/TicTacToe/Soldier76/Soldier76P.png")),
            SymmetraP=new ImageIcon(getClass().getResource("/TicTacToe/Symmetra/SymmetraP.png")), TorbjornP=new ImageIcon(getClass().getResource("/TicTacToe/Torbjorn/TorbjornP.png")),
            TracerP=new ImageIcon(getClass().getResource("/TicTacToe/Tracer/TracerP.png")), WidowmakerP=new ImageIcon(getClass().getResource("/TicTacToe/Widowmaker/WidowmakerP.png")),
            WinstonP=new ImageIcon(getClass().getResource("/TicTacToe/Winston/WinstonP.png")), ZaryaP=new ImageIcon(getClass().getResource("/TicTacToe/Zarya/ZaryaP.png")),
            ZenyattaP=new ImageIcon(getClass().getResource("/TicTacToe/Zenyatta/ZenyattaP.png"));
    
    ImageIcon BastionU=new ImageIcon(getClass().getResource("/TicTacToe/Bastion/BastionU.png")), DVaU=new ImageIcon(getClass().getResource("/TicTacToe/DVa/DVaU.png")),
            GenjiU=new ImageIcon(getClass().getResource("/TicTacToe/Genji/GenjiU.png")), HanzoU=new ImageIcon(getClass().getResource("/TicTacToe/Hanzo/HanzoU.png")),
            JunkratU=new ImageIcon(getClass().getResource("/TicTacToe/Junkrat/JunkratU.png")), LucioU=new ImageIcon(getClass().getResource("/TicTacToe/Lucio/LucioU.png")),
            McCreeU=new ImageIcon(getClass().getResource("/TicTacToe/McCree/McCreeU.png")), MeiU=new ImageIcon(getClass().getResource("/TicTacToe/Mei/MeiU.png")),
            MercyU=new ImageIcon(getClass().getResource("/TicTacToe/Mercy/MercyU.png")), PharahU=new ImageIcon(getClass().getResource("/TicTacToe/Pharah/PharahU.png")),
            ReaperU=new ImageIcon(getClass().getResource("/TicTacToe/Reaper/ReaperU.png")), ReinhardtU=new ImageIcon(getClass().getResource("/TicTacToe/Reinhardt/ReinhardtU.png")),
            RoadhogU=new ImageIcon(getClass().getResource("/TicTacToe/Roadhog/RoadhogU.png")), Soldier76U=new ImageIcon(getClass().getResource("/TicTacToe/Soldier76/Soldier76U.png")),
            SymmetraU=new ImageIcon(getClass().getResource("/TicTacToe/Symmetra/SymmetraU.png")), TorbjornU=new ImageIcon(getClass().getResource("/TicTacToe/Torbjorn/TorbjornU.png")),
            TracerU=new ImageIcon(getClass().getResource("/TicTacToe/Tracer/TracerU.png")), WidowmakerU=new ImageIcon(getClass().getResource("/TicTacToe/Widowmaker/WidowmakerU.png")),
            WinstonU=new ImageIcon(getClass().getResource("/TicTacToe/Winston/WinstonU.png")), ZaryaU=new ImageIcon(getClass().getResource("/TicTacToe/Zarya/ZaryaU.png")),
            ZenyattaU=new ImageIcon(getClass().getResource("/TicTacToe/Zenyatta/ZenyattaU.png"));
    
    ImageIcon Logo=new ImageIcon(getClass().getResource("/TicTacToe/Logo.png"));
    
    InputStream hero, bufferHero, tie, bufferTie, button, bufferButton;
    boolean mute = false; //is the program muted?
    
    ArrayList<String> killStreakName = new ArrayList(); //killStreak names (penta, godlike, first bllod)
    ArrayList<String> announcer = new ArrayList(); 
    ArrayList<String> tieAnnouncer = new ArrayList(); //announcer for ties
    
    
    Icon team1, team2; //Used to save team pantheon
    String team1Name, team2Name, team1Sound, team2Sound;
    
    boolean team1Turn = true; //is it team1's turn
    boolean singleCheck = false, doubleCheck = false, bothCheck = false; //checking for double victories.
    int doubleType, doubleLocation; //double victories
    
    /**
     * Creates new form ticTacToeGUI
     */
    public ticTacToeGUI() {
        initComponents();
        setSize(496,729);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //center the window
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setResizable(false);
        buttonList();
        gameStart();
        setFontsEtc();
    }
    
    private void setFontsEtc() { //Set custom Smite font and other 'extra' things.
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/TicTacToe/Fonts/TextFont.ttf")).deriveFont(Font.BOLD, 13);
            lblTie.setFont(font);
            lblTeam1.setFont(font);
            lblTeam2.setFont(font);
            lblInfo.setFont(font);
            
            Font fontRespawn = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/TicTacToe/Fonts/TextFont.ttf")).deriveFont(Font.PLAIN, 14);
            lblRespawn.setFont(fontRespawn);
            lblQuit.setFont(fontRespawn);
            
            Font fontScore = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/TicTacToe/Fonts/TextFont.ttf")).deriveFont(Font.BOLD, 14);
            txtTeam1Score.setFont(fontScore);
            txtTeam2Score.setFont(fontScore);
            txtTie.setFont(fontScore);
        } 
        catch(FontFormatException | IOException ex) { 
            System.out.println(ex.getMessage()); 
        } 
        
        txtTeam1Score.setBorder(null);
        txtTeam1Score.setBackground(new Color(255,255,255, 0));
        
        txtTeam2Score.setBorder(null);
        txtTeam2Score.setBackground(new Color(255,255,255, 0));
        
        txtTie.setBorder(null);
        txtTie.setBackground(new Color(255,255,255, 0));

    }
    
    private void buttonList() {
        boxes[0][0] = btn0; boxes[1][0] = btn1;boxes[2][0] = btn2;
        boxes[0][1] = btn3;boxes[1][1] = btn4;boxes[2][1] = btn5;
        boxes[0][2] = btn6;boxes[1][2] = btn7;boxes[2][2] = btn8;
    }
    
    private void gameStart() {
        Background();
        lists();
        teamRandom(1);
        teamRandom(2);
        rolloverIconSet();
        singleCheck = false;
        doubleCheck = false;
        if (team1Turn) {
            lblInfo.setText(team1Name+" has attacked "+team2Name+"!");
        }
        else {
            lblInfo.setText(team2Name+" has attacked "+team1Name+"!");
        }
    }
    
    
    public void playSound(String heroName, String soundName) {
        if (!mute) {
            switch (heroName) {
                case "Bastion":
                    playHero(heroName, soundName);
                    break;
                case "D.Va":
                    playHero("DVa", soundName);
                    break;
                case "Genji":
                    playHero(heroName, soundName);
                    break;
                case "Hanzo":
                    playHero(heroName, soundName);
                    break;
                case "Junkrat":
                    playHero(heroName, soundName);
                    break;
                case "Lúcio":
                    playHero("Lucio", soundName);
                    break;
                case "McCree":
                    playHero(heroName, soundName);
                    break;
                case "Mei":
                    playHero(heroName, soundName);
                    break;
                case "Mercy":
                    playHero(heroName, soundName);
                    break;
                case "Pharah":
                    playHero(heroName, soundName);
                    break;
                case "Reaper":
                    playHero(heroName, soundName);
                    break;
                case "Reinhardt":
                    playHero(heroName, soundName);
                    break;
                case "Roadhog":
                    playHero(heroName, soundName);
                    break;
                case "Soldier: 76":
                    playHero("Soldier76", soundName);
                    break;
                case "Symmetra":
                    playHero(heroName, soundName);
                    break;
                case "Torbjörn":
                    playHero("Torbjorn", soundName);
                    break;
                case "Tracer":
                    playHero(heroName, soundName);
                    break;
                case "Widowmaker":
                    playHero(heroName, soundName);
                    break;
                case "Winston":
                    playHero(heroName, soundName);
                    break;
                case "Zarya":
                    playHero(heroName, soundName);
                    break;
                case "Zenyatta":
                    playHero(heroName, soundName);
                    break;
                case "Announce":
                    playTie(soundName);
                    break;
                case "Tie":
                    playTie(soundName);
                    break;
            }
        }
    }
    
    private void playHero(String heroName, String soundName) { //Hero sounds
        hero = this.getClass().getResourceAsStream("/TicTacToe/"+heroName+"/"+heroName+soundName+".wav");
        bufferHero = new BufferedInputStream(hero);
        try {
            AudioInputStream audioInputStreamHero = AudioSystem.getAudioInputStream(bufferHero);
            Clip clipHero = AudioSystem.getClip();
            clipHero.open(audioInputStreamHero);
            clipHero.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playTie(String soundName) { //Tie sounds
        tie = getClass().getResourceAsStream("/TicTacToe/OtherSounds/"+soundName+".wav");
        bufferTie = new BufferedInputStream(tie);
        try {
            AudioInputStream audioInputStreamTie = AudioSystem.getAudioInputStream(bufferTie);
            Clip clipTie = AudioSystem.getClip();
            clipTie.open(audioInputStreamTie);
            clipTie.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playButton() { //Button Clicks
        if (!mute) {
            button = ticTacToeGUI.class.getResourceAsStream("/TicTacToe/OtherSounds/ButtonClick.wav");
            bufferButton = new BufferedInputStream(button);
            try {
                AudioInputStream audioInputStreamButton = AudioSystem.getAudioInputStream(bufferButton);
                Clip clipButton = AudioSystem.getClip();
                clipButton.open(audioInputStreamButton);
                clipButton.start();
            } catch(Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    private void lists() { //Initializes the lists
        Collections.addAll(pantheons, "Bastion", "D.Va", "Genji", "Hanzo", "Junkrat", "Lúcio", "McCree", "Mei", "Mercy", "Pharah", "Reaper", "Reinhardt", "Roadhog", "Soldier: 76", "Symmetra", "Torbjörn", "Tracer", "Widowmaker", "Winston", "Zarya", "Zenyatta");
        Collections.addAll(iconsBig, BastionU, DVaU, GenjiU, HanzoU, JunkratU, LucioU, McCreeU, MeiU, MercyU, PharahU,
                ReaperU, ReinhardtU, RoadhogU, Soldier76U, SymmetraU, TorbjornU, TracerU, WidowmakerU, WinstonU, ZaryaU, ZenyattaU);
        Collections.addAll(iconsSmall, BastionP, DVaP, GenjiP, HanzoP, JunkratP, LucioP, McCreeP, MeiP, MercyP, PharahP,
                ReaperP, ReinhardtP, RoadhogP, Soldier76P, SymmetraP, TorbjornP, TracerP, WidowmakerP, WinstonP, ZaryaP, ZenyattaP);
    }
    
    private void teamRandom(int teamNumber) { //Choose a random pantheon
        Random rnTeam = new Random();
        int RpantheonNum = rnTeam.nextInt(21);
        teamSet(teamNumber,RpantheonNum);
    }
    
    private void Background() { //select a map
        Random rnBG = new Random();
        int BGNum = rnBG.nextInt(5);
        switch (BGNum) {
            case 0:
                picBG.setIcon(Hollywood);
                playSound("Announce", "hollywood");
                break;
            case 1:
                picBG.setIcon(Dorado);
                playSound("Announce", "dorado");
                break;
            case 2:
                picBG.setIcon(Numbani);
                playSound("Announce", "numbani");
                break;
            case 3:
                picBG.setIcon(Hanamura);
                playSound("Announce", "hanamura");
                break;
            case 4:
                picBG.setIcon(Volskaya);
                playSound("Announce", "volskaya");
                break;
        }
    }
    
    private void teamSet(int teamNum, int pantheonNum) { //sets the teams icons and names
        if (teamNum == 1) {
            System.out.println(pantheonNum);
            team1 = iconsBig.get(pantheonNum);
            picTeam1Small.setIcon(iconsSmall.get(pantheonNum));
            lblTeam1.setText(pantheons.get(pantheonNum));
            team1Name = pantheons.get(pantheonNum);
            team1Sound = pantheons.get(pantheonNum);
        }
        else {
            System.out.println(pantheonNum + " Team 2");
            if (iconsBig.get(pantheonNum) != team1) {
                team2 = iconsBig.get(pantheonNum);
                picTeam2Small.setIcon(iconsSmall.get(pantheonNum));
                lblTeam2.setText(pantheons.get(pantheonNum));
                team2Name = pantheons.get(pantheonNum);
                team2Sound = pantheons.get(pantheonNum);
            }
            else {
                teamRandom(2); //rerolls team2 if already taken by team1
            }
        }
    }
    
    private void rolloverIconSet() { //sets the hover icons for the current team's turn
        if (team1Turn) {
            for (int y=0; y<3; y++) {
                for (int x=0; x<3; x++) {
                    boxes[x][y].setRolloverIcon(team1);
                    boxes[x][y].setPressedIcon(team1);
                }
            }
        }
        else {
            for (int y=0; y<3; y++) {
                for (int x=0; x<3; x++) {
                    boxes[x][y].setRolloverIcon(team2);
                    boxes[x][y].setPressedIcon(team2);
                }
            }
        }
    }
    
    private void placeLogo(int btnX, int btnY) { //places the icon
        if (team1Turn) {
            boxes[btnX][btnY].setDisabledIcon(team1);
            boxes[btnX][btnY].setEnabled(false);
            team1Turn=false; //next turn is team2
            winTeam=1;
            winCheck();
        }
        else {
            boxes[btnX][btnY].setDisabledIcon(team2);
            boxes[btnX][btnY].setEnabled(false);
            team1Turn=true; //next turn is team1
            winTeam=2;
            winCheck();
        }
        rolloverIconSet();
    }
    
    private void winCheck() { //Check for a line
        for (int i=0; i<3; i++) {
            if (boxes[0][i].getDisabledIcon() == boxes[1][i].getDisabledIcon() && boxes[0][i].getDisabledIcon() == boxes[2][i].getDisabledIcon() && !boxes[0][i].isEnabled() && !boxes[1][i].isEnabled() && !boxes[2][i].isEnabled()) { //horizontal checks
            Win( 0, i);
            return;
            }
        }

        for (int i=0; i<3; i++) {
            if (boxes[i][0].getDisabledIcon() == boxes[i][1].getDisabledIcon() && boxes[i][0].getDisabledIcon() == boxes[i][2].getDisabledIcon() && !boxes[i][0].isEnabled() && !boxes[i][1].isEnabled() && !boxes[i][2].isEnabled()) { //vertical checks
            Win( 1, i);
            return;
            }
        }

        if (boxes[0][0].getDisabledIcon() == boxes[1][1].getDisabledIcon() && boxes[0][0].getDisabledIcon() == boxes[2][2].getDisabledIcon() && !boxes[0][0].isEnabled() && !boxes[1][1].isEnabled() && !boxes[2][2].isEnabled()) { //diagonal checks
            Win( 2, 0);
            return;
        }
        else if (boxes[0][2].getDisabledIcon() == boxes[1][1].getDisabledIcon() && boxes[0][2].getDisabledIcon() == boxes[2][0].getDisabledIcon() && !boxes[0][2].isEnabled() && !boxes[1][1].isEnabled() && !boxes[2][0].isEnabled()) {
            Win( 2, 1);
            return;
        }

        if (!singleCheck && boxes[0][0].getDisabledIcon() != null && boxes[1][0].getDisabledIcon() != null && boxes[2][0].getDisabledIcon() != null && boxes[0][1].getDisabledIcon() != null && boxes[1][1].getDisabledIcon() != null && boxes[2][1].getDisabledIcon() != null && boxes[0][2].getDisabledIcon() != null && boxes[1][2].getDisabledIcon() != null && boxes[2][2].getDisabledIcon() != null) {
            tieGame();
        }
    }
    
    private void winDoubleCheck() { //Check for a double win
        bothCheck = true;
        for (int i=0; i<3; i++) {
            if (boxes[0][i].getDisabledIcon() == boxes[1][i].getDisabledIcon() && boxes[0][i].getDisabledIcon() == boxes[2][i].getDisabledIcon() && !boxes[0][i].isEnabled() && !boxes[1][i].isEnabled() && !boxes[2][i].isEnabled()) { //horizontal checks
                if (doubleType != 0 && doubleLocation != i) {
                bothCheck = false;
                doubleWin(0,i);
                break;
                }
            }
        }
        
        for (int i=0; i<3; i++) {
            if (boxes[i][0].getDisabledIcon() == boxes[i][1].getDisabledIcon() && boxes[i][0].getDisabledIcon() == boxes[i][2].getDisabledIcon() && !boxes[i][0].isEnabled() && !boxes[i][1].isEnabled() && !boxes[i][2].isEnabled()) { //vertical checks
                if (doubleType != 1 && doubleLocation != i) {
                    bothCheck = false;
                    doubleWin(1,i);
                    break;
                }
            }
        }
        
        if (boxes[0][0].getDisabledIcon() == boxes[1][1].getDisabledIcon() && boxes[0][0].getDisabledIcon() == boxes[2][2].getDisabledIcon() && !boxes[0][0].isEnabled() && !boxes[1][1].isEnabled() && !boxes[2][2].isEnabled()) { //diagonal checks
            if (doubleType != 2 && doubleLocation != 0) {
                bothCheck = false;
                doubleWin(2,0);
            }
        }
        else if (boxes[0][2].getDisabledIcon() == boxes[1][1].getDisabledIcon() && boxes[0][2].getDisabledIcon() == boxes[2][0].getDisabledIcon() && !boxes[0][2].isEnabled() && !boxes[1][1].isEnabled() && !boxes[2][0].isEnabled()) {
            if (doubleType != 2 && doubleLocation != 1) {
                bothCheck = false;
                doubleWin(2,1);
            }
        }
        if (bothCheck) {
            doubleCheck = true;
            Win(doubleType, doubleLocation);
        }
    }
    
    private void disableAll() { //Disable all the buttons
        for (int y=0; y<3; y++) {
            for (int x=0; x<3; x++) {
                    boxes[x][y].setEnabled(false);
            }
        }
    }
    
    private void resetBoxes() { //Reset the buttons
        for (int y=0; y<3; y++) {
            for (int x=0; x<3; x++) {
                    boxes[x][y].setEnabled(true);
                    boxes[x][y].setDisabledIcon(null);
            }
        }
    }
    
    private void teamUltimate(String heroName) {
        switch (heroName) {
                case "Bastion":
                    winText= heroName+" has activated his tank configuration, he wins! ";
                    break;
                case "D.Va":
                    winText= heroName+" has activated self-destruct sequence, she wins!";
                    break;
                case "Genji":
                    winText= heroName+" has unsheathed the Dragonblade, he wins!";
                    break;
                case "Hanzo":
                    winText= "RYUU GA WAGA TEKI WO KURAU! Hanzo wins!";
                    break;
                case "Junkrat":
                    winText= "FIRE IN THE HOLE! Junkrat wins!";
                    break;
                case "Lúcio":
                    winText= heroName+" activated the Sound Barrier, he wins!";
                    break;
                case "McCree":
                    winText= "It's high noon. McCree wins!";
                    break;
                case "Mei":
                    winText= heroName+" has unleashed a blizzard, she wins!";
                    break;
                case "Mercy":
                    winText= "Heroes never die! Mercy wins!";
                    break;
                case "Pharah":
                    winText= "Justice rains from above! Pharah wins!";
                    break;
                case "Reaper":
                    winText= heroName+" has unleashed Death Blossom! He wins!";
                    break;
                case "Reinhardt":
                    winText= "HAMMER DOWN! Reinhardt wins!";
                    break;
                case "Roadhog":
                    winText= "Roadhog went Whole Hog! He wins!";
                    break;
                case "Soldier: 76":
                    winText= "Soldier: 76 activated his tactical visor. He wins!";
                    break;
                case "Symmetra":
                    winText= heroName+" has activated her teleporter. She wins!";
                    break;
                case "Torbjörn":
                    winText= heroName+" has unleashed the MOLTEN CORE! He wins!";
                    break;
                case "Tracer":
                    winText= heroName+" used her Pulse Bomb! She wins!";
                    break;
                case "Widowmaker":
                    winText= "You can't escape Widowmaker's sights. She wins!";
                    break;
                case "Winston":
                    winText= "PRIMAL PUNCH! Winston wins!";
                    break;
                case "Zarya":
                    winText= heroName+" actiavted the Graviton Surge! She wins!";
                    break;
                case "Zenyatta":
                    winText= heroName+" has experienced tranquility. He wins!";
                    break;
            }
    }
    
    private void Win(int winType, int location) { //The current team wins
        if (!singleCheck) {
            doubleType = winType;
            doubleLocation = location;
            if (!doubleCheck) {
                winDoubleCheck();
            }
            else {
                disableAll(); //end the round.

                switch(winTeam) {
                    case 1:
                        team1Score +=1;
                        txtTeam1Score.setText(String.valueOf(team1Score)); 
                        winAnnouncer = team1Sound;
                        switch(team1Score) { //set the killstreak
                            case 1:
                                winText = team1Name+" has won the round!";
                                winSound = "1";
                                break;
                            case 2:
                                teamUltimate(team1Name);
                                winSound = "2";
                                break;
                        }
                        break;
                    case 2:
                        team2Score +=1;
                        txtTeam2Score.setText(String.valueOf(team2Score));
                        winAnnouncer = team2Sound;
                        switch(team2Score) {
                            case 1:
                                winText = team2Name+" has won the round!";
                                winSound = "1";
                                break;
                            case 2:
                                teamUltimate(team2Name);
                                winSound = "2";
                                break;
                        }
                        break;
                }



                lblInfo.setText(winText); //set the killstreak icons
                switch(winType) {
                    case 0: //horizontal
                        for (int i=0; i<3; i++) {
                            boxes[i][location].setDisabledIcon(Logo);
                        }
                        break;
                    case 1:
                        for (int i=0; i<3; i++) {
                            boxes[location][i].setDisabledIcon(Logo);
                        }
                        break;
                    case 2:
                        if (location == 0) {
                            boxes[0][0].setDisabledIcon(Logo);
                            boxes[1][1].setDisabledIcon(Logo);
                            boxes[2][2].setDisabledIcon(Logo);
                        }
                        else {
                            boxes[0][2].setDisabledIcon(Logo);
                            boxes[1][1].setDisabledIcon(Logo);
                            boxes[2][0].setDisabledIcon(Logo);
                        }
                }
                System.out.println(winAnnouncer+winSound);
                playSound(winAnnouncer, winSound);
                
                
                if (team1Score == 2) { //if the team won the whole game
                    Victory();
                }
                else if (team2Score == 2) {
                    Victory();
                }
            }
        }
    }
    
    private void doubleWin(int winType, int location) { //if a double line occurs
        disableAll(); //end the round.

        switch(winTeam) {
            case 1:
                team1Score +=1;
                txtTeam1Score.setText(String.valueOf(team1Score));
                winAnnouncer = team1Sound;
                switch(team1Score) {
                    case 1:
                        winText = team1Name+" has won the round!";
                        winSound = "1";
                        break;
                    case 2:
                        teamUltimate(team1Name);
                        winSound = "2";
                        break;
                }
                break;
            case 2:
                team2Score +=1;
                txtTeam2Score.setText(String.valueOf(team2Score));
                winAnnouncer = team2Sound;
                switch(team2Score) {
                    case 1:
                        winText = team2Name+" has won the round!";
                        winSound = "1";
                        break;
                    case 2:
                        teamUltimate(team2Name);
                        winSound = "2";
                        break;
                }
                break;
        }



        lblInfo.setText(winText);
        switch(winType) {
            case 0: //horizontal
                for (int i=0; i<3; i++) {
                    boxes[i][location].setDisabledIcon(Logo);
                }
                break;
            case 1:
                for (int i=0; i<3; i++) {
                    boxes[location][i].setDisabledIcon(Logo);
                }
                break;
            case 2:
                if (location == 0) {
                    boxes[0][0].setDisabledIcon(Logo);
                    boxes[1][1].setDisabledIcon(Logo);
                    boxes[2][2].setDisabledIcon(Logo);
                }
                else {
                    boxes[0][2].setDisabledIcon(Logo);
                    boxes[1][1].setDisabledIcon(Logo);
                    boxes[2][0].setDisabledIcon(Logo);
                }
        }
        switch(doubleType) {
            case 0: //horizontal
                for (int i=0; i<3; i++) {
                    boxes[i][doubleLocation].setDisabledIcon(Logo);
                }
                break;
            case 1:
                for (int i=0; i<3; i++) {
                    boxes[doubleLocation][i].setDisabledIcon(Logo);
                }
                break;
            case 2:
                if (doubleLocation == 0) {
                    boxes[0][0].setDisabledIcon(Logo);
                    boxes[1][1].setDisabledIcon(Logo);
                    boxes[2][2].setDisabledIcon(Logo);
                }
                else {
                    boxes[0][2].setDisabledIcon(Logo);
                    boxes[1][1].setDisabledIcon(Logo);
                    boxes[2][0].setDisabledIcon(Logo);
                }
        }
        
        playSound(winAnnouncer, winSound);
        
        if (team1Score == 2) { //does the player win?
            Victory();
        }
        else if (team2Score == 2) {
            Victory();
        }
    }
    
    private void tieGame() { //tie game/fire giant
        disableAll();
        tieScore += 1; //increase fire giant score
        txtTie.setText(String.valueOf(tieScore));
        
        lblInfo.setText("You have both lost!"); 
        playSound("Tie", "tie"); 
    }
    
    private void Victory() { //the winiing team
        lblInfo.setText(winText);
        lblRespawn.setText("New Game");
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        txtTeam2Score = new javax.swing.JTextField();
        lblTie = new javax.swing.JLabel();
        txtTie = new javax.swing.JTextField();
        lblRespawn = new javax.swing.JLabel();
        btnRematch = new javax.swing.JButton();
        txtTeam1Score = new javax.swing.JTextField();
        lblQuit = new javax.swing.JLabel();
        btnQuit = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();
        picTeam2Small = new javax.swing.JLabel();
        picTeam1Small = new javax.swing.JLabel();
        lblTeam2 = new javax.swing.JLabel();
        lblTeam1 = new javax.swing.JLabel();
        tBtnMute = new javax.swing.JToggleButton();
        picBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Overwatch Battles");
        setIconImage(new ImageIcon(getClass().getResource("Logo.png")).getImage());
        setResizable(false);
        setSize(new java.awt.Dimension(490, 635));
        getContentPane().setLayout(null);

        btn0.setBorderPainted(false);
        btn0.setContentAreaFilled(false);
        btn0.setFocusable(false);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        getContentPane().add(btn0);
        btn0.setBounds(10, 180, 150, 150);

        btn1.setBorderPainted(false);
        btn1.setContentAreaFilled(false);
        btn1.setFocusable(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1);
        btn1.setBounds(170, 180, 150, 150);

        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        btn2.setFocusable(false);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn2);
        btn2.setBounds(330, 180, 150, 150);

        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.setFocusable(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn3);
        btn3.setBounds(10, 340, 150, 150);

        btn4.setBorderPainted(false);
        btn4.setContentAreaFilled(false);
        btn4.setFocusable(false);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn4);
        btn4.setBounds(170, 340, 150, 150);

        btn5.setBorderPainted(false);
        btn5.setContentAreaFilled(false);
        btn5.setFocusable(false);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        getContentPane().add(btn5);
        btn5.setBounds(330, 340, 150, 150);

        btn6.setBorderPainted(false);
        btn6.setContentAreaFilled(false);
        btn6.setFocusable(false);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        getContentPane().add(btn6);
        btn6.setBounds(10, 500, 150, 150);

        btn7.setBorderPainted(false);
        btn7.setContentAreaFilled(false);
        btn7.setFocusable(false);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        getContentPane().add(btn7);
        btn7.setBounds(170, 500, 150, 150);

        btn8.setBorderPainted(false);
        btn8.setContentAreaFilled(false);
        btn8.setFocusable(false);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        getContentPane().add(btn8);
        btn8.setBounds(330, 500, 150, 150);

        txtTeam2Score.setEditable(false);
        txtTeam2Score.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTeam2Score.setForeground(new java.awt.Color(255, 255, 255));
        txtTeam2Score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTeam2Score.setText("0");
        txtTeam2Score.setFocusable(false);
        txtTeam2Score.setOpaque(false);
        getContentPane().add(txtTeam2Score);
        txtTeam2Score.setBounds(359, 111, 24, 24);

        lblTie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTie.setForeground(new java.awt.Color(250, 160, 46));
        lblTie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTie.setText("Draw");
        getContentPane().add(lblTie);
        lblTie.setBounds(191, 123, 108, 20);

        txtTie.setEditable(false);
        txtTie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTie.setForeground(new java.awt.Color(88, 79, 74));
        txtTie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTie.setText("0");
        txtTie.setFocusable(false);
        txtTie.setOpaque(false);
        getContentPane().add(txtTie);
        txtTie.setBounds(235, 101, 24, 24);

        lblRespawn.setForeground(new java.awt.Color(255, 255, 255));
        lblRespawn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRespawn.setText("Respawn");
        getContentPane().add(lblRespawn);
        lblRespawn.setBounds(40, 660, 90, 30);

        btnRematch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/button.png"))); // NOI18N
        btnRematch.setBorderPainted(false);
        btnRematch.setContentAreaFilled(false);
        btnRematch.setFocusable(false);
        btnRematch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/buttonHover.png"))); // NOI18N
        btnRematch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRematchActionPerformed(evt);
            }
        });
        getContentPane().add(btnRematch);
        btnRematch.setBounds(40, 660, 90, 30);

        txtTeam1Score.setEditable(false);
        txtTeam1Score.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTeam1Score.setForeground(new java.awt.Color(255, 255, 255));
        txtTeam1Score.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTeam1Score.setText("0");
        txtTeam1Score.setFocusable(false);
        txtTeam1Score.setOpaque(false);
        getContentPane().add(txtTeam1Score);
        txtTeam1Score.setBounds(111, 111, 20, 24);

        lblQuit.setForeground(new java.awt.Color(255, 255, 255));
        lblQuit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuit.setText("Quit");
        getContentPane().add(lblQuit);
        lblQuit.setBounds(360, 660, 90, 30);

        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/button.png"))); // NOI18N
        btnQuit.setBorderPainted(false);
        btnQuit.setContentAreaFilled(false);
        btnQuit.setFocusable(false);
        btnQuit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/buttonHover.png"))); // NOI18N
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit);
        btnQuit.setBounds(360, 660, 90, 30);

        lblInfo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(88, 79, 74));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setToolTipText("");
        getContentPane().add(lblInfo);
        lblInfo.setBounds(10, 153, 470, 16);
        getContentPane().add(picTeam2Small);
        picTeam2Small.setBounds(386, 10, 88, 100);
        getContentPane().add(picTeam1Small);
        picTeam1Small.setBounds(16, 10, 88, 100);

        lblTeam2.setForeground(new java.awt.Color(250, 160, 46));
        lblTeam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTeam2);
        lblTeam2.setBounds(388, 113, 80, 20);

        lblTeam1.setBackground(new java.awt.Color(255, 255, 255));
        lblTeam1.setForeground(new java.awt.Color(250, 160, 46));
        lblTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeam1.setToolTipText("");
        getContentPane().add(lblTeam1);
        lblTeam1.setBounds(22, 113, 80, 20);

        tBtnMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/Unmute.png"))); // NOI18N
        tBtnMute.setFocusable(false);
        tBtnMute.setRolloverEnabled(false);
        tBtnMute.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/Mute.png"))); // NOI18N
        tBtnMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnMuteActionPerformed(evt);
            }
        });
        getContentPane().add(tBtnMute);
        tBtnMute.setBounds(470, 680, 20, 20);

        picBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TicTacToe/Hanamura.png"))); // NOI18N
        getContentPane().add(picBG);
        picBG.setBounds(0, 0, 490, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        placeLogo(0,0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        placeLogo(1,0);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        placeLogo(2,0);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        placeLogo(0,1);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        placeLogo(1,1);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        placeLogo(2,1);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        placeLogo(0,2);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        placeLogo(1,2);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        placeLogo(2,2);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btnRematchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRematchActionPerformed
        playButton();
        if (lblRespawn.getText().equals("Respawn")) { //new round
            lists();
            rolloverIconSet();
            singleCheck = false;
            doubleCheck = false;
            if (team1Turn) {
                lblInfo.setText(team1Name+" has attacked "+team2Name+"!");
            }
            else {
                lblInfo.setText(team2Name+" has attacked "+team1Name+"!");
            }
            
            resetBoxes();
        }
        else {
            team1Score = 0; team2Score = 0; //new game
            txtTeam1Score.setText(String.valueOf(team1Score)); txtTeam2Score.setText(String.valueOf(team2Score));
            lblRespawn.setText("Respawn");
            
            resetBoxes();
            team1Turn = true;
            gameStart();
            rolloverIconSet();
        }
    }//GEN-LAST:event_btnRematchActionPerformed

    private void tBtnMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBtnMuteActionPerformed
        if (!tBtnMute.isSelected()) { //is the game muted
           mute =false;
        }
        else {
            mute = true;
        }
        playButton();
    }//GEN-LAST:event_tBtnMuteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ticTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ticTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ticTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ticTacToeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ticTacToeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnRematch;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblQuit;
    private javax.swing.JLabel lblRespawn;
    private javax.swing.JLabel lblTeam1;
    private javax.swing.JLabel lblTeam2;
    private javax.swing.JLabel lblTie;
    private javax.swing.JLabel picBG;
    private javax.swing.JLabel picTeam1Small;
    private javax.swing.JLabel picTeam2Small;
    private javax.swing.JToggleButton tBtnMute;
    private javax.swing.JTextField txtTeam1Score;
    private javax.swing.JTextField txtTeam2Score;
    private javax.swing.JTextField txtTie;
    // End of variables declaration//GEN-END:variables
}
