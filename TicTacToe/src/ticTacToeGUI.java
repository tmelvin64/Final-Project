
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
 * SMITE Pantheon Battles
 * This program was made by Jordan (DXHHH101) on April 2, 2016
 * as practice with 2D arrays.
 */
public class ticTacToeGUI extends javax.swing.JFrame {

    JButton[][] boxes = new JButton[3][3];
    int team1Score=0, team2Score=0, tieScore=0;
    int winTeam, winKillStreak; //track who the winning team is/what killstreak accolade.
    
    ArrayList<String> pantheons = new ArrayList();
    ArrayList<Icon> iconsBig = new ArrayList();
    ArrayList<Icon> iconsSmall = new ArrayList();
    ArrayList<Icon> killStreak = new ArrayList();
    String winText, winSound, winAnnouncer;
    
    ImageIcon BGChaos=new ImageIcon(getClass().getResource("BGChaos.png")), BGOrder=new ImageIcon(getClass().getResource("BGOrder.png")); //Initialize pictures
    
    ImageIcon Chinese=new ImageIcon(getClass().getResource("Chinese.png")), ChineseSmall=new ImageIcon(getClass().getResource("ChineseSmall.png")),
            Egyptian=new ImageIcon(getClass().getResource("Egyptian.png")), EgyptianSmall=new ImageIcon(getClass().getResource("EgyptianSmall.png")),
            Greek=new ImageIcon(getClass().getResource("Greek.png")), GreekSmall=new ImageIcon(getClass().getResource("GreekSmall.png")),
            Hindu=new ImageIcon(getClass().getResource("Hindu.png")), HinduSmall=new ImageIcon(getClass().getResource("HinduSmall.png")),
            Japanese=new ImageIcon(getClass().getResource("Japanese.png")), JapaneseSmall=new ImageIcon(getClass().getResource("JapaneseSmall.png")),
            Mayan=new ImageIcon(getClass().getResource("Mayan.png")), MayanSmall=new ImageIcon(getClass().getResource("MayanSmall.png")),
            Norse=new ImageIcon(getClass().getResource("Norse.png")), NorseSmall=new ImageIcon(getClass().getResource("NorseSmall.png")),
            Roman=new ImageIcon(getClass().getResource("Roman.png")), RomanSmall=new ImageIcon(getClass().getResource("RomanSmall.png"));
    
    ImageIcon FirstBlood=new ImageIcon(getClass().getResource("FirstBlood.png")), PlayerKill=new ImageIcon(getClass().getResource("PlayerKill.png")),
            DoubleKill=new ImageIcon(getClass().getResource("DoubleKill.png")), TripleKill=new ImageIcon(getClass().getResource("TripleKill.png")),
            QuadraKill=new ImageIcon(getClass().getResource("QuadraKill.png")), PentaKill=new ImageIcon(getClass().getResource("PentaKill.png")),
            Godlike=new ImageIcon(getClass().getResource("Godlike.png")); 
    
    InputStream bart, bufferBart, hindu, bufferHindu, drybear, bufferDrybear, inuki, bufferInuki, tie, bufferTie, button, bufferButton;
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
        announcerRandom();
    }
    
    private void setFontsEtc() { //Set custom Smite font and other 'extra' things.
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/Fonts/SmiteFont.ttf")).deriveFont(Font.BOLD, 13);
            lblTie.setFont(font);
            lblTeam1.setFont(font);
            lblTeam2.setFont(font);
            lblInfo.setFont(font);
            
            Font fontRespawn = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/Fonts/SmiteFont.ttf")).deriveFont(Font.BOLD, 11);
            lblRespawn.setFont(fontRespawn);
            lblQuit.setFont(fontRespawn);
            
            Font fontScore = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/Fonts/SmiteFont.ttf")).deriveFont(Font.BOLD, 14);
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
            lblInfo.setText("The "+team1Name+" gods have attacked the "+team2Name+" gods!");
        }
        else {
            lblInfo.setText("The "+team2Name+" gods have attacked the "+team1Name+" gods!");
        }
    }
    
    
    public void playSound(String announcer, String soundName) {
        if (!mute) {
            switch (announcer) {
                case "Bart":
                    playBart(soundName);
                    break;
                case "Hindu":
                    playHindu(soundName);
                    break;
                case "Inuki":
                    playInuki(soundName);
                    break;
                case "Drybear":
                    playDrybear(soundName);
                    break;
                case "Tie":
                    playTie(soundName);
                    break;
            }
        }
    }
    
    private void playBart(String soundName) { //Bart announcer pack
        bart = this.getClass().getResourceAsStream("/Bart/"+soundName+".wav");
        bufferBart = new BufferedInputStream(bart);
        try {
            AudioInputStream audioInputStreamBart = AudioSystem.getAudioInputStream(bufferBart);
            Clip clipBart = AudioSystem.getClip();
            clipBart.open(audioInputStreamBart);
            clipBart.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playHindu(String soundName) { //Hinduman
        hindu = this.getClass().getResourceAsStream("/Hindu/"+soundName+".wav");
        bufferHindu = new BufferedInputStream(hindu);
        try {
            AudioInputStream audioInputStreamHindu = AudioSystem.getAudioInputStream(bufferHindu);
            Clip clipHindu = AudioSystem.getClip();
            clipHindu.open(audioInputStreamHindu);
            clipHindu.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playInuki(String soundName) { //Inuki
        inuki = this.getClass().getResourceAsStream("/Inuki/"+soundName+".wav");
        bufferInuki = new BufferedInputStream(inuki);
        try {
            AudioInputStream audioInputStreamInuki = AudioSystem.getAudioInputStream(bufferInuki);
            Clip clipInuki = AudioSystem.getClip();
            clipInuki.open(audioInputStreamInuki);
            clipInuki.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playDrybear(String soundName) { //Drybear
        drybear = this.getClass().getResourceAsStream("/Drybear/"+soundName+".wav");
        bufferDrybear = new BufferedInputStream(drybear);
        try {
            AudioInputStream audioInputStreamDrybear = AudioSystem.getAudioInputStream(bufferDrybear);
            Clip clipDrybear = AudioSystem.getClip();
            clipDrybear.open(audioInputStreamDrybear);
            clipDrybear.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playTie(String announcerName) { //Tie sounds
        tie = getClass().getResourceAsStream("/OtherSounds/"+announcerName+".wav");
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
            button = ticTacToeGUI.class.getResourceAsStream("/OtherSounds/ButtonClick.wav");
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
        Collections.addAll(pantheons, "Chinese", "Egyptian", "Greek", "Hindu", "Japanese", "Mayan", "Norse", "Roman");
        Collections.addAll(iconsBig, Chinese, Egyptian, Greek, Hindu, Japanese, Mayan, Norse, Roman);
        Collections.addAll(iconsSmall, ChineseSmall, EgyptianSmall, GreekSmall, HinduSmall, JapaneseSmall, MayanSmall, NorseSmall, RomanSmall);
        Collections.addAll(killStreak, FirstBlood, PlayerKill, DoubleKill, TripleKill, QuadraKill, PentaKill, Godlike);
        
        Collections.addAll(killStreakName, "First", "Kill", "Double", "Triple", "Quadra", "Penta", "Godlike");
    }
    
    private void teamRandom(int teamNumber) { //Choose a random pantheon
        Random rnTeam = new Random();
        int RpantheonNum = rnTeam.nextInt(8);
        teamSet(teamNumber,RpantheonNum);
    }
    
    private void announcerRandom() { //selects a random announcer for each team
        Collections.addAll(announcer, "Bart", "Hindu", "Drybear", "Inuki");
        Random rnAnnouncer = new Random();
        Random rnAnnouncer2 = new Random();
        int announcerNum = rnAnnouncer.nextInt(4);
        team1Sound = announcer.get(announcerNum);
        int announcerNum2 = rnAnnouncer2.nextInt(4);
        while (announcerNum2 == announcerNum) {
            announcerNum2 = rnAnnouncer2.nextInt(4);
        }
        team2Sound = announcer.get(announcerNum2);
        System.out.println(team1Sound+" "+team2Sound);
    }
    
    private void Background() { //select either the Chaos or Order background
        Random rnBG = new Random();
        int BGNum = rnBG.nextInt(2);
        if (BGNum == 0) {
            picBG.setIcon(BGOrder);
        }
        else {
            picBG.setIcon(BGChaos);
        }
    }
    
    private void teamSet(int teamNum, int pantheonNum) { //sets the teams icons and names
        if (teamNum == 1) {
            team1 = iconsBig.get(pantheonNum);
            picTeam1Small.setIcon(iconsSmall.get(pantheonNum));
            lblTeam1.setText(pantheons.get(pantheonNum));
            team1Name = pantheons.get(pantheonNum);
        }
        else {
            if (iconsBig.get(pantheonNum) != team1) {
                team2 = iconsBig.get(pantheonNum);
                picTeam2Small.setIcon(iconsSmall.get(pantheonNum));
                lblTeam2.setText(pantheons.get(pantheonNum));
                team2Name = pantheons.get(pantheonNum);
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
                                if (team2Score == 0) {
                                    winKillStreak = 0;
                                    winText = "Team "+winTeam+" has drawn first blood!";
                                    winSound = "First";
                                }
                                else {
                                    winKillStreak = 1;
                                    winText = "Team "+winTeam+" has slain an enemy!";
                                    winSound = "Kill";
                                }
                                break;
                            case 2:
                                winKillStreak = 2;
                                winText = "Team "+winTeam+" has scored a double-kill!";
                                winSound = "Double";
                                break;
                            case 3:
                                winKillStreak = 3;
                                winText = "Team "+winTeam+" has gotten a triple-kill!";
                                winSound = "Triple";
                                break;
                            case 4:
                                winKillStreak = 4;
                                winText = "Team "+winTeam+" has a quadra-kill, looking for one more!";
                                winSound = "Quadra";
                                break;
                            case 5:
                                winKillStreak = 5;
                                winText = "PENTA KILL! Team "+winTeam+" has scored a DEICIDE!";
                                winSound = "Penta";
                                break;
                        }
                        break;
                    case 2:
                        team2Score +=1;
                        txtTeam2Score.setText(String.valueOf(team2Score));
                        winAnnouncer = team2Sound;
                        switch(team2Score) {
                            case 1:
                                if (team1Score == 0) {
                                    winKillStreak = 0;
                                    winText = "Team "+winTeam+" has drawn first blood!";
                                    winSound = "First";
                                }
                                else {
                                    winKillStreak = 1;
                                    winText = "Team "+winTeam+" has slain an enemy!";
                                    winSound = "Kill";
                                }
                                break;
                            case 2:
                                winKillStreak = 2;
                                winText = "Team "+winTeam+" has scored a double-kill!";
                                winSound = "Double";
                                break;
                            case 3:
                                winKillStreak = 3;
                                winText = "Team "+winTeam+" has gotten a triple-kill!";
                                winSound = "Triple";
                                break;
                            case 4:
                                winKillStreak = 4;
                                winText = "Team "+winTeam+" has a quadra-kill, looking for one more!";
                                winSound = "Quadra";
                                break;
                            case 5:
                                winKillStreak = 5;
                                winText = "PENTA KILL! Team "+winTeam+" has scored a DEICIDE!";
                                winSound = "Penta";
                                break;
                        }
                        break;
                }



                lblInfo.setText(winText); //set the killstreak icons
                switch(winType) {
                    case 0: //horizontal
                        for (int i=0; i<3; i++) {
                            boxes[i][location].setDisabledIcon(killStreak.get(winKillStreak));
                        }
                        break;
                    case 1:
                        for (int i=0; i<3; i++) {
                            boxes[location][i].setDisabledIcon(killStreak.get(winKillStreak));
                        }
                        break;
                    case 2:
                        if (location == 0) {
                            boxes[0][0].setDisabledIcon(killStreak.get(winKillStreak));
                            boxes[1][1].setDisabledIcon(killStreak.get(winKillStreak));
                            boxes[2][2].setDisabledIcon(killStreak.get(winKillStreak));
                        }
                        else {
                            boxes[0][2].setDisabledIcon(killStreak.get(winKillStreak));
                            boxes[1][1].setDisabledIcon(killStreak.get(winKillStreak));
                            boxes[2][0].setDisabledIcon(killStreak.get(winKillStreak));
                        }
                }
                System.out.println(winAnnouncer+winSound);
                playSound(winAnnouncer, winSound);
                
                
                if (team1Score == 5) { //if the team won the whole game
                    Victory();
                }
                else if (team2Score == 5) {
                    Victory();
                }
            }
        }
    }
    
    private void doubleWin(int winType, int location) { //if a double line occurs
        disableAll(); //end the round.

        switch(winTeam) {
            case 1:
                team1Score +=2;
                txtTeam1Score.setText(String.valueOf(team1Score));
                winAnnouncer = team1Sound;
                switch(team1Score) {
                    case 1:
                        if (team2Score == 0) {
                            winKillStreak = 0;
                            winText = "Team "+winTeam+" has drawn first blood!";
                            winSound = "First";
                        }
                        else {
                            winKillStreak = 1;
                            winText = "Team "+winTeam+" has slain an enemy!";
                            winSound = "Kill";
                        }
                        break;
                    case 2:
                        winKillStreak = 2;
                        winText = "Team "+winTeam+" has scored a double-kill!";
                        winSound = "Double";
                        break;
                    case 3:
                        winKillStreak = 3;
                        winText = "Team "+winTeam+" has gotten a triple-kill!";
                        winSound = "Triple";
                        break;
                    case 4:
                        winKillStreak = 4;
                        winText = "Team "+winTeam+" has a quadra-kill, looking for one more!";
                        winSound = "Quadra";
                        break;
                    case 5:
                        winKillStreak = 5;
                        winText = "PENTA KILL! Team "+winTeam+" has scored a DEICIDE!";
                        winSound = "Penta";
                        break;
                    case 6:
                        winKillStreak = 6;
                        winText = "GODLIKE! Team "+winTeam+" has scored a DEICIDE!";
                        winSound = "Godlike";
                        break;
                }
                break;
            case 2:
                team2Score +=2;
                txtTeam2Score.setText(String.valueOf(team2Score));
                winAnnouncer = team2Sound;
                switch(team2Score) {
                    case 1:
                        if (team1Score == 0) {
                            winKillStreak = 0;
                            winText = "Team "+winTeam+" has drawn first blood!";
                            winSound = "First";
                        }
                        else {
                            winKillStreak = 1;
                            winText = "Team "+winTeam+" has slain an enemy!";
                            winSound = "Kill";
                        }
                        break;
                    case 2:
                        winKillStreak = 2;
                        winText = "Team "+winTeam+" has scored a double-kill!";
                        winSound = "Double";
                        break;
                    case 3:
                        winKillStreak = 3;
                        winText = "Team "+winTeam+" has gotten a triple-kill!";
                        winSound = "Triple";
                        break;
                    case 4:
                        winKillStreak = 4;
                        winText = "Team "+winTeam+" has a quadra-kill, looking for one more!";
                        winSound = "Quadra";
                        break;
                    case 5:
                        winKillStreak = 5;
                        winText = "PENTA KILL! Team "+winTeam+" has scored a DEICIDE!";
                        winSound = "Penta";
                        break;
                    case 6:
                        winKillStreak = 6;
                        winText = "GODLIKE! Team "+winTeam+" has scored a DEICIDE!";
                        winSound = "Godlike";
                        break;
                }
                break;
        }



        lblInfo.setText(winText);
        switch(winType) {
            case 0: //horizontal
                for (int i=0; i<3; i++) {
                    boxes[i][location].setDisabledIcon(killStreak.get(winKillStreak));
                }
                break;
            case 1:
                for (int i=0; i<3; i++) {
                    boxes[location][i].setDisabledIcon(killStreak.get(winKillStreak));
                }
                break;
            case 2:
                if (location == 0) {
                    boxes[0][0].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[1][1].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[2][2].setDisabledIcon(killStreak.get(winKillStreak));
                }
                else {
                    boxes[0][2].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[1][1].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[2][0].setDisabledIcon(killStreak.get(winKillStreak));
                }
        }
        switch(doubleType) {
            case 0: //horizontal
                for (int i=0; i<3; i++) {
                    boxes[i][doubleLocation].setDisabledIcon(killStreak.get(winKillStreak));
                }
                break;
            case 1:
                for (int i=0; i<3; i++) {
                    boxes[doubleLocation][i].setDisabledIcon(killStreak.get(winKillStreak));
                }
                break;
            case 2:
                if (doubleLocation == 0) {
                    boxes[0][0].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[1][1].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[2][2].setDisabledIcon(killStreak.get(winKillStreak));
                }
                else {
                    boxes[0][2].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[1][1].setDisabledIcon(killStreak.get(winKillStreak));
                    boxes[2][0].setDisabledIcon(killStreak.get(winKillStreak));
                }
        }
        
        playSound(winAnnouncer, winSound);
        
        if (team1Score > 4) { //win or godlike
            Victory();
        }
        else if (team2Score > 4) {
            Victory();
        }
    }
    
    private void tieGame() { //tie game/fire giant
        disableAll();
        tieScore += 1; //increase fire giant score
        txtTie.setText(String.valueOf(tieScore));
        
        lblInfo.setText("You have both been slain by the Fire Giant!"); 
        Collections.addAll(tieAnnouncer, "Nox", "Khepri", "Mez", "Swagni"); //play random 'you have been slain' line
        Random rnTie = new Random();
        int tieNum = rnTie.nextInt(4);
        System.out.println(tieAnnouncer.get(tieNum));
        playSound("Tie", tieAnnouncer.get(tieNum));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMITE Pantheon Battles");
        setIconImage(new ImageIcon(getClass().getResource("SmitePantheonBattles.png")).getImage());
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
        lblTie.setForeground(new java.awt.Color(255, 102, 0));
        lblTie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTie.setText("Fire Giant");
        getContentPane().add(lblTie);
        lblTie.setBounds(191, 123, 108, 20);

        txtTie.setEditable(false);
        txtTie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTie.setForeground(new java.awt.Color(255, 102, 0));
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

        btnRematch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Respawn.png"))); // NOI18N
        btnRematch.setBorderPainted(false);
        btnRematch.setContentAreaFilled(false);
        btnRematch.setFocusable(false);
        btnRematch.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/RespawnHover.png"))); // NOI18N
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

        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Quit.png"))); // NOI18N
        btnQuit.setBorderPainted(false);
        btnQuit.setContentAreaFilled(false);
        btnQuit.setFocusable(false);
        btnQuit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/QuitHover.png"))); // NOI18N
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit);
        btnQuit.setBounds(360, 660, 90, 30);

        lblInfo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(51, 51, 51));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setToolTipText("");
        getContentPane().add(lblInfo);
        lblInfo.setBounds(10, 153, 470, 16);
        getContentPane().add(picTeam2Small);
        picTeam2Small.setBounds(390, 20, 80, 80);
        getContentPane().add(picTeam1Small);
        picTeam1Small.setBounds(20, 20, 80, 80);

        lblTeam2.setForeground(new java.awt.Color(51, 51, 51));
        lblTeam2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTeam2);
        lblTeam2.setBounds(388, 113, 80, 20);

        lblTeam1.setBackground(new java.awt.Color(255, 255, 255));
        lblTeam1.setForeground(new java.awt.Color(51, 51, 51));
        lblTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeam1.setToolTipText("");
        getContentPane().add(lblTeam1);
        lblTeam1.setBounds(22, 113, 80, 20);

        tBtnMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Unmute.png"))); // NOI18N
        tBtnMute.setFocusable(false);
        tBtnMute.setRolloverEnabled(false);
        tBtnMute.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Mute.png"))); // NOI18N
        tBtnMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnMuteActionPerformed(evt);
            }
        });
        getContentPane().add(tBtnMute);
        tBtnMute.setBounds(470, 680, 20, 20);

        picBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BGOrder.png"))); // NOI18N
        getContentPane().add(picBG);
        picBG.setBounds(0, 0, 490, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        System.exit(0);
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
            gameStart();
            resetBoxes();
        }
        else {
            team1Score = 0; team2Score = 0; //new game
            txtTeam1Score.setText(String.valueOf(team1Score)); txtTeam2Score.setText(String.valueOf(team2Score));
            lblRespawn.setText("Respawn");
            
            gameStart();
            resetBoxes();
            announcerRandom();
            team1Turn = true;
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
