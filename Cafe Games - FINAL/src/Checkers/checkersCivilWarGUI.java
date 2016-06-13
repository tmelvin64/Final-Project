package Checkers;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Checkers
 * This program was made by Jordan E
 */
public class checkersCivilWarGUI extends javax.swing.JFrame {

    Timer quitTimer, rematchTimer; //used for the "Are you Sure?" on the quit and rematch buttons
    
    JButton[][] space = new JButton[8][8];
    ArrayList<JButton> blackJump = new ArrayList();
    ArrayList<JButton> redJump = new ArrayList();
    int blackCount = 0, redCount = 0, blackTally = 0, redTally = 0;
    
    ImageIcon Red = new ImageIcon(getClass().getResource("/Checkers/images/Red.png"));
    ImageIcon RedS = new ImageIcon(getClass().getResource("/Checkers/images/RedS.png"));
    ImageIcon RedKing = new ImageIcon(getClass().getResource("/Checkers/images/RedKing.png"));
    ImageIcon RedKingS = new ImageIcon(getClass().getResource("/Checkers/images/RedKingS.png"));
    ImageIcon Black = new ImageIcon(getClass().getResource("/Checkers/images/Black.png"));
    ImageIcon BlackS = new ImageIcon(getClass().getResource("/Checkers/images/BlackS.png"));
    ImageIcon BlackKing = new ImageIcon(getClass().getResource("/Checkers/images/BlackKing.png"));
    ImageIcon BlackKingS = new ImageIcon(getClass().getResource("/Checkers/images/BlackKingS.png"));
    
    ImageIcon nullRedS = new ImageIcon(getClass().getResource("/Checkers/images/RedEmptyS.png"));
    ImageIcon nullBlackS = new ImageIcon(getClass().getResource("/Checkers/images/BlackEmptyS.png"));
    
    ImageIcon RedBoard = new ImageIcon(getClass().getResource("/Checkers/images/CheckersBoardRed.png"));
    ImageIcon BlackBoard = new ImageIcon(getClass().getResource("/Checkers/images/CheckersBoardBlack.png"));
    
    boolean selectedCheck = false;
    JButton selectedPiece; //the currently selected piece
    ArrayList<JButton> selectedMove = new ArrayList();
    
    JButton selectedMulti;
    boolean multiCheck = false;
    
    InputStream tada, bufferTada;
    Clip clipMusic, clipTada;
    boolean mute = false;
    
    /**
     * Creates new form checkersGUI
     */
    public checkersCivilWarGUI() {
        initComponents();
        setSize(902,949);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //center the window
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        setBoardArray();
        playTada("Beginning");
        playMusic();
        
        blackMoveCheck();
        
    }

    private void setBoardArray() {
        space[1][0] = p10;
        space[3][0] = p30;
        space[5][0] = p50;
        space[7][0] = p70;
        space[0][1] = p01;
        space[2][1] = p21;
        space[4][1] = p41;
        space[6][1] = p61;
        space[1][2] = p12;
        space[3][2] = p32;
        space[5][2] = p52;
        space[7][2] = p72;
        space[0][3] = p03;
        space[2][3] = p23;
        space[4][3] = p43;
        space[6][3] = p63;
        space[1][4] = p14;
        space[3][4] = p34;
        space[5][4] = p54;
        space[7][4] = p74;
        space[0][5] = p05;
        space[2][5] = p25;
        space[4][5] = p45;
        space[6][5] = p65;
        space[1][6] = p16;
        space[3][6] = p36;
        space[5][6] = p56;
        space[7][6] = p76;
        space[0][7] = p07;
        space[2][7] = p27;
        space[4][7] = p47;
        space[6][7] = p67;
        
    }
    
    private void blackMoveCheck() {
        blackJump.clear();
        blackCount = 0;
        blackTally = 0;
        
        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                if (space[x][y] != null) {
                    space[x][y].setEnabled(false);
                    
                    if (space[x][y].getIcon() == Black || space[x][y].getIcon() == BlackKing) {
                        blackCount++;
                        if (x-1 >= 0 && y-1 >=0) {
                            if (space[x-1][y-1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                blackTally++;
                            }
                            else if(space[x-1][y-1].getIcon() == Red || space[x-1][y-1].getIcon() == RedKing) {
                                if (x-2 >=0 && y-2 >= 0) {
                                    if (space[x-2][y-2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        blackTally++;
                                        blackJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                        
                        if (x+1 < 8 && y-1 >=0) {
                            if (space[x+1][y-1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                blackTally++;
                            }
                            else if(space[x+1][y-1].getIcon() == Red || space[x+1][y-1].getIcon() == RedKing) {
                                if (x+2 < 8 && y-2 >= 0) {
                                    if (space[x+2][y-2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        blackTally++;
                                        blackJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                    }
                    
                    if (space[x][y].getIcon() == BlackKing) {
                        if (x+1 < 8 && y+1 < 8) {
                            if (space[x+1][y+1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                blackTally++;
                            }
                            else if(space[x+1][y+1].getIcon() == Red || space[x+1][y+1].getIcon() == RedKing) {
                                if (x+2 < 8 && y+2 < 8) {
                                    if (space[x+2][y+2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        blackTally++;
                                        blackJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                        
                        if (x-1 >= 0 && y+1 < 8) {
                            if (space[x-1][y+1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                            }
                            else if(space[x-1][y+1].getIcon() == Red || space[x-1][y+1].getIcon() == RedKing) {
                                if (x-2 >= 0 && y+2 < 8) {
                                    if (space[x-2][y+2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        blackTally++;
                                        blackJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                    }
                    
                    
                }
            }
        }
        imgBoard.setIcon(BlackBoard);
        lblInfo.setText("Captain America's turn.");
        
        if (!blackJump.isEmpty()) {
            lblInfo.setText("Captain America's turn. A capture must be made!");
            for (int y=0; y<8; y++) {
                for (int x=0; x<8; x++) {
                    if (space[x][y] != null) {
                        space[x][y].setEnabled(false);
                    }
                }
            }
            
            for (int i=0; i<blackJump.size(); i++) {
                blackJump.get(i).setEnabled(true);
            }
        }
        
        if (blackCount == 0 || blackTally == 0) {
            redVictory();
        }
        
    }
    
    private void redMoveCheck() {
        redJump.clear();
        redCount = 0;
        
        for (int y=0; y<8; y++) {
            for (int x=0; x<8; x++) {
                if (space[x][y] != null) {
                    space[x][y].setEnabled(false);
                    
                    if (space[x][y].getIcon() == Red || space[x][y].getIcon() == RedKing) {
                        redCount++;
                        if (x+1 < 8 && y+1 < 8) {
                            if (space[x+1][y+1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                redTally++;
                            }
                            else if(space[x+1][y+1].getIcon() == Black || space[x+1][y+1].getIcon() == BlackKing) {
                                if (x+2 < 8 && y+2 < 8) {
                                    if (space[x+2][y+2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        redTally++;
                                        redJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                        
                        if (x-1 >= 0 && y+1 < 8) {
                            if (space[x-1][y+1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                redTally++;
                            }
                            else if(space[x-1][y+1].getIcon() == Black || space[x-1][y+1].getIcon() == BlackKing) {
                                if (x-2 >= 0 && y+2 < 8) {
                                    if (space[x-2][y+2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        redTally++;
                                        redJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                    }
                    
                    if (space[x][y].getIcon() == RedKing) {
                        if (x-1 >= 0 && y-1 >= 0) {
                            if (space[x-1][y-1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                redTally++;
                            }
                            else if(space[x-1][y-1].getIcon() == Black || space[x-1][y-1].getIcon() == BlackKing) {
                                if (x-2 >= 0 && y-2 >= 0) {
                                    if (space[x-2][y-2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        redTally++;
                                        redJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                        
                        if (x+1 < 8 && y-1 >= 0) {
                            if (space[x+1][y-1].getIcon() == null) {
                                space[x][y].setEnabled(true);
                                redTally++;
                            }
                            else if(space[x+1][y-1].getIcon() == Black || space[x+1][y-1].getIcon() == BlackKing) {
                                if (x+2 < 8 && y-2 >= 0) {
                                    if (space[x+2][y-2].getIcon() == null) {
                                        space[x][y].setEnabled(true);
                                        redTally++;
                                        redJump.add(space[x][y]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        imgBoard.setIcon(RedBoard);
        lblInfo.setText("Iron Man's turn.");
        if (!redJump.isEmpty()) {
            lblInfo.setText("Iron Man's turn. A capture must be made!");
            for (int y=0; y<8; y++) {
                for (int x=0; x<8; x++) {
                    if (space[x][y] != null) {
                        space[x][y].setEnabled(false);
                    }
                }
            }
            
            for (int i=0; i<redJump.size(); i++) {
                redJump.get(i).setEnabled(true);
            }
        }
        
        if (redCount == 0 || redTally == 0) {
            blackVictory();
        }
    }
    
    private void buttonClick(int x, int y) {
        for (int l=0; l<8; l++) { //disable all the buttons
            for (int m=0; m<8; m++) {
                if (space[m][l] != null) {
                    space[m][l].setEnabled(false);
                }
            }
        }
        
        if (space[x][y].getIcon() == Black && space[x][y] == selectedMulti || space[x][y].getIcon() == BlackKing && space[x][y] == selectedMulti) {
            
            if (!selectedCheck) {
                selectedCheck = true;
                
                selectedPiece = space[x][y];
                space[x][y].setEnabled(true);
                if (space[x][y].getIcon() == Black) {
                    space[x][y].setIcon(BlackS);
                }
                else {
                    space[x][y].setIcon(BlackKingS);
                }
                
                selectedMove.clear();
                
                if (x-2 >= 0 && y-2 >= 0) {
                    if (space[x-1][y-1].getIcon() == Red || space[x-1][y-1].getIcon() == RedKing) {
                        if (space[x-2][y-2].getIcon() == null) {
                            space[x-2][y-2].setEnabled(true);
                            space[x-2][y-2].setIcon(nullBlackS);
                            selectedMove.add(space[x-2][y-2]);
                        }
                    }
                }

                if (x+2 < 8 && y-2 >= 0) {
                    if (space[x+1][y-1].getIcon() == Red || space[x+1][y-1].getIcon() == RedKing) {
                        if (space[x+2][y-2].getIcon() == null) {
                            space[x+2][y-2].setEnabled(true);
                            space[x+2][y-2].setIcon(nullBlackS);
                            selectedMove.add(space[x+2][y-2]);
                        }
                    }
                }
                
                if (space[x][y].getIcon() == BlackKingS) {
                    if (x+2 < 8 && y+2 < 8) {
                        if (space[x+1][y+1].getIcon() == Red || space[x+1][y+1].getIcon() == RedKing) {
                            if (space[x+2][y+2].getIcon() == null) {
                                space[x+2][y+2].setEnabled(true);
                                space[x+2][y+2].setIcon(nullBlackS);
                                selectedMove.add(space[x+2][y+2]);
                            }
                        }
                    }

                    if (x-2 >= 0 && y+2 < 8) {
                        if (space[x-1][y+1].getIcon() == Red || space[x+1][y-1].getIcon() == RedKing) {
                            if (space[x-2][y+2].getIcon() == null) {
                                space[x-2][y+2].setEnabled(true);
                                space[x-2][y+2].setIcon(nullBlackS);
                                selectedMove.add(space[x-2][y+2]);
                            }
                        }
                    }
                }
                
            }
            else {
                selectedCheck = false;
                if (space[x][y].getIcon() == BlackS) {
                    space[x][y].setIcon(Black);
                }
                else {
                    space[x][y].setIcon(BlackKing);
                }
                
                for (int i = 0; i < selectedMove.size(); i++) {
                    selectedMove.get(i).setIcon(null);
                }
                selectedMulti = null;
                blackMultiCheck(x,y);
            }
        }
        else if (space[x][y].getIcon() == Black || space[x][y].getIcon() == BlackS || space[x][y].getIcon() == BlackKing || space[x][y].getIcon() == BlackKingS) {
            if (blackJump.isEmpty()) {
                if (!selectedCheck) {
                    selectedCheck = true; //a piece has been selected to move

                    selectedPiece = space[x][y];

                    space[x][y].setEnabled(true);
                    if (space[x][y].getIcon() == Black) {
                        space[x][y].setIcon(BlackS);
                    }
                    else {
                        space[x][y].setIcon(BlackKingS);
                    }

                    selectedMove.clear();
                    if (x-1 >= 0 && y-1 >=0) {
                        if (space[x-1][y-1].getIcon() == null) {
                            space[x-1][y-1].setEnabled(true);
                            space[x-1][y-1].setIcon(nullBlackS);
                            selectedMove.add(space[x-1][y-1]);
                        }
                    }

                    if (x+1 < 8 && y-1 >=0) {
                        if (space[x+1][y-1].getIcon() == null) {
                            space[x+1][y-1].setEnabled(true);
                            space[x+1][y-1].setIcon(nullBlackS);
                            selectedMove.add(space[x+1][y-1]);
                        }
                    }
                    
                    if (space[x][y].getIcon() == BlackKingS) {
                        if (x+1 < 8 && y+1 < 8) {
                            if (space[x+1][y+1].getIcon() == null) {
                                space[x+1][y+1].setEnabled(true);
                                space[x+1][y+1].setIcon(nullBlackS);
                                selectedMove.add(space[x+1][y+1]);
                            }
                        }

                        if (x-1 >= 0 && y+1 < 8) {
                            if (space[x-1][y+1].getIcon() == null) {
                                space[x-1][y+1].setEnabled(true);
                                space[x-1][y+1].setIcon(nullBlackS);
                                selectedMove.add(space[x-1][y+1]);
                            }
                        }
                    }
                }
                else {
                    selectedCheck = false;
                    if (space[x][y].getIcon() == BlackS) {
                        space[x][y].setIcon(Black);
                    }
                    else {
                        space[x][y].setIcon(BlackKing);
                    } 
                        
                    for (int i = 0; i < selectedMove.size(); i++) {
                        selectedMove.get(i).setIcon(null);
                    }
                    selectedMulti = null;
                    blackMoveCheck();
                }
            }
            else { //black forced jump
                if (!selectedCheck) {
                    selectedCheck = true;
                    
                    selectedPiece = space[x][y];
                    
                    space[x][y].setEnabled(true);
                    if (space[x][y].getIcon() == Black) {
                        space[x][y].setIcon(BlackS);
                    }
                    else {
                        space[x][y].setIcon(BlackKingS);
                    }
                    
                    selectedMove.clear();
                    if (x-1 >= 0 && y-1 >= 0) {
                        if (space[x-1][y-1].getIcon() == Red || space[x-1][y-1].getIcon() == RedKing) {
                            if (x-2 >= 0 && y-2 >= 0) {
                                if (space[x-2][y-2].getIcon() == null) {
                                    space[x-2][y-2].setEnabled(true);
                                    space[x-2][y-2].setIcon(nullBlackS);
                                    selectedMove.add(space[x-2][y-2]);
                                }
                            }
                        }
                    }

                    if (x+1 < 8 && y-1 >= 0) {
                        if (space[x+1][y-1].getIcon() == Red || space[x+1][y-1].getIcon() == RedKing) {
                            if (x+2 < 8 && y-2 >= 0) {
                                if (space[x+2][y-2].getIcon() == null) {
                                    space[x+2][y-2].setEnabled(true);
                                    space[x+2][y-2].setIcon(nullBlackS);
                                    selectedMove.add(space[x+2][y-2]);
                                }
                            }
                        }
                    }
                    
                    if (space[x][y].getIcon() == BlackKingS) {
                        if (x+1 < 8 && y+1 < 8) {
                            if (space[x+1][y+1].getIcon() == Red || space[x+1][y+1].getIcon() == RedKing) {
                                if (x+2 < 8 && y+2 < 8) {
                                    if (space[x+2][y+2].getIcon() == null) {
                                        space[x+2][y+2].setEnabled(true);
                                        space[x+2][y+2].setIcon(nullBlackS);
                                        selectedMove.add(space[x+2][y+2]);
                                    }
                                }
                            }
                        }

                        if (x-1 >= 0 && y+1 < 8) {
                            if (space[x-1][y+1].getIcon() == Red || space[x-1][y+1].getIcon() == RedKing) {
                                if (x-2 >= 0 && y+2 < 8) {
                                    if (space[x-2][y+2].getIcon() == null) {
                                        space[x-2][y+2].setEnabled(true);
                                        space[x-2][y+2].setIcon(nullBlackS);
                                        selectedMove.add(space[x-2][y+2]);
                                    }
                                }
                            }
                        }
                    }
                }
                else {
                    selectedCheck = false;
                    if (space[x][y].getIcon() == BlackS) {
                        space[x][y].setIcon(Black);
                    }
                    else {
                        space[x][y].setIcon(BlackKing);
                    }
                    
                    for (int i = 0; i < selectedMove.size(); i++) {
                        selectedMove.get(i).setIcon(null);
                    }
                    selectedMulti = null;
                    blackMoveCheck();
                }
            }
        }
        else if (space[x][y].getIcon() == Red && space[x][y] == selectedMulti || space[x][y].getIcon() == RedKing && space[x][y] == selectedMulti) {
            
            if (!selectedCheck) {
                selectedCheck = true;
                
                selectedPiece = space[x][y];
                space[x][y].setEnabled(true);
                if (space[x][y].getIcon() == Red) {
                    space[x][y].setIcon(RedS);
                }
                else {
                    space[x][y].setIcon(RedKingS);
                }
                
                selectedMove.clear();
                
                if (x+2 < 8 && y+2 < 8) {
                    if (space[x+1][y+1].getIcon() == Black || space[x+1][y+1].getIcon() == BlackKing) {
                        if (space[x+2][y+2].getIcon() == null) {
                            space[x+2][y+2].setEnabled(true);
                            space[x+2][y+2].setIcon(nullRedS);
                            selectedMove.add(space[x+2][y+2]);
                        }
                    }
                }

                if (x-2 >= 0 && y+2 < 8) {
                    if (space[x-1][y+1].getIcon() == Black || space[x-1][y+1].getIcon() == BlackKing) {
                        if (space[x-2][y+2].getIcon() == null) {
                            space[x-2][y+2].setEnabled(true);
                            space[x-2][y+2].setIcon(nullRedS);
                            selectedMove.add(space[x-2][y+2]);
                        }
                    }
                }
                
                if (space[x][y].getIcon() == RedKingS) {
                    if (x-2 >= 0 && y-2 >= 0) {
                        if (space[x-1][y-1].getIcon() == Black || space[x-1][y-1].getIcon() == BlackKing) {
                            if (space[x-2][y-2].getIcon() == null) {
                                space[x-2][y-2].setEnabled(true);
                                space[x-2][y-2].setIcon(nullRedS);
                                selectedMove.add(space[x-2][y-2]);
                            }
                        }
                    }

                    if (x+2 < 8 && y-2 >= 0) {
                        if (space[x+1][y-1].getIcon() == Black || space[x-1][y+1].getIcon() == BlackKing) {
                            if (space[x+2][y-2].getIcon() == null) {
                                space[x+2][y-2].setEnabled(true);
                                space[x+2][y-2].setIcon(nullRedS);
                                selectedMove.add(space[x+2][y-2]);
                            }
                        }
                    }
                }
                
            }
            else {
                selectedCheck = false;
                if (space[x][y].getIcon() == RedS) {
                    space[x][y].setIcon(Red);
                }
                else {
                    space[x][y].setIcon(RedKing);
                }
                
                for (int i = 0; i < selectedMove.size(); i++) {
                    selectedMove.get(i).setIcon(null);
                }
                selectedMulti = null;
                redMultiCheck(x,y);
            }
        }
        else if (space[x][y].getIcon() == Red || space[x][y].getIcon() == RedS || space[x][y].getIcon() == RedKing || space[x][y].getIcon() == RedKingS) { //red turn
            if (redJump.isEmpty()) {
                if (!selectedCheck) {
                    selectedCheck = true; //a piece has been selected to move

                    selectedPiece = space[x][y];

                    space[x][y].setEnabled(true);
                    if (space[x][y].getIcon() == Red) {
                        space[x][y].setIcon(RedS);
                    }
                    else {
                        space[x][y].setIcon(RedKingS);
                    }

                    selectedMove.clear();
                    if (x+1 < 8 && y+1 < 8) {
                        if (space[x+1][y+1].getIcon() == null) {
                            space[x+1][y+1].setEnabled(true);
                            space[x+1][y+1].setIcon(nullRedS);
                            selectedMove.add(space[x+1][y+1]);
                        }
                    }

                    if (x-1 >= 0 && y+1 < 8) {
                        if (space[x-1][y+1].getIcon() == null) {
                            space[x-1][y+1].setEnabled(true);
                            space[x-1][y+1].setIcon(nullRedS);
                            selectedMove.add(space[x-1][y+1]);
                        }
                    }
                    
                    if (space[x][y].getIcon() == RedKingS) {
                        if (x-1 >= 0 && y-1 >= 0) {
                            if (space[x-1][y-1].getIcon() == null) {
                                space[x-1][y-1].setEnabled(true);
                                space[x-1][y-1].setIcon(nullRedS);
                                selectedMove.add(space[x-1][y-1]);
                            }
                        }

                        if (x+1 < 8 && y-1 >= 0) {
                            if (space[x+1][y-1].getIcon() == null) {
                                space[x+1][y-1].setEnabled(true);
                                space[x+1][y-1].setIcon(nullRedS);
                                selectedMove.add(space[x+1][y-1]);
                            }
                        }
                    }
                }
                else {
                    selectedCheck = false;
                    if (space[x][y].getIcon() == RedS){
                        space[x][y].setIcon(Red);
                    }
                    else {
                        space[x][y].setIcon(RedKing);
                    }
                    
                    for (int i = 0; i < selectedMove.size(); i++) {
                        selectedMove.get(i).setIcon(null);
                    }
                    selectedMulti = null;
                    redMoveCheck();
                }
            }
            else { //red forced jump
                if (!selectedCheck) {
                    selectedCheck = true;
                    
                    selectedPiece = space[x][y];
                    
                    space[x][y].setEnabled(true);
                    if (space[x][y].getIcon() == Red) {
                        space[x][y].setIcon(RedS);
                    }
                    else {
                        space[x][y].setIcon(RedKingS);
                    }
                    
                    selectedMove.clear();
                    
                    if (x+1 < 8 && y+1 < 8) {
                        if (space[x+1][y+1].getIcon() == Black || space[x+1][y+1].getIcon() == BlackKing) {
                            if (x+2 < 8 && y+2 <8) {
                                if (space[x+2][y+2].getIcon() == null) {
                                    space[x+2][y+2].setEnabled(true);
                                    space[x+2][y+2].setIcon(nullRedS);
                                    selectedMove.add(space[x+2][y+2]);
                                }
                            }
                        }
                    }

                    if (x-1 >= 0 && y+1 < 8) {
                        if (space[x-1][y+1].getIcon() == Black || space[x-1][y+1].getIcon() == BlackKing) {
                            if (x-2 >= 0 && y+2 <8) {
                                if (space[x-2][y+2].getIcon() == null) {
                                    space[x-2][y+2].setEnabled(true);
                                    space[x-2][y+2].setIcon(nullRedS);
                                    selectedMove.add(space[x-2][y+2]);
                                }
                            }
                        }
                    }
                    
                    if (space[x][y].getIcon() == RedKingS) {
                        if (x-1 >= 0 && y-1 >= 0) {
                            if (space[x-1][y-1].getIcon() == Black || space[x-1][y-1].getIcon() == BlackKing) {
                                if (x-2 >= 0 && y-2 >= 0) {
                                    if (space[x-2][y-2].getIcon() == null) {
                                        space[x-2][y-2].setEnabled(true);
                                        space[x-2][y-2].setIcon(nullRedS);
                                        selectedMove.add(space[x-2][y-2]);
                                    }
                                }
                            }
                        }

                        if (x+1 < 8 && y-1 >= 0) {
                            if (space[x+1][y-1].getIcon() == Black || space[x+1][y-1].getIcon() == BlackKing) {
                                if (x+2 < 8 && y-2 >= 0) {
                                    if (space[x+2][y-2].getIcon() == null) {
                                        space[x+2][y-2].setEnabled(true);
                                        space[x+2][y-2].setIcon(nullRedS);
                                        selectedMove.add(space[x+2][y-2]);
                                    }
                                }
                            }
                        }
                    }
                    
                }
                else {
                    selectedCheck = false;
                    if (space[x][y].getIcon() == RedS) {
                        space[x][y].setIcon(Red);
                    }
                    else {
                        space[x][y].setIcon(RedKing);
                    }
                    
                    for (int i = 0; i < selectedMove.size(); i++) {
                        selectedMove.get(i).setIcon(null);
                    }
                    selectedMulti = null;
                    redMoveCheck();
                }
            }
        }
        else { //a blank space
            if (selectedPiece.getIcon() == BlackS || selectedPiece.getIcon() == BlackKingS) { //black's move
                selectedCheck = false;
                
                for (int i = 0; i < selectedMove.size(); i++) {
                    selectedMove.get(i).setIcon(null);
                }
                
                if (selectedPiece.getIcon() == BlackS) {
                    space[x][y].setIcon(Black);
                    space[x][y].setDisabledIcon(Black);
                }
                else {
                    space[x][y].setIcon(BlackKing);
                    space[x][y].setDisabledIcon(BlackKing);
                }
                
                selectedPiece.setIcon(null);
                selectedPiece.setDisabledIcon(null);
                
                if (!blackJump.isEmpty()) { //remove the jumped piece, if jumped
                    if (x+2 < 8 && y+2 < 8) {
                        if (space[x+2][y+2] == selectedPiece) {
                            space[x+1][y+1].setIcon(null);
                            space[x+1][y+1].setDisabledIcon(null);
                        }
                    }
                    
                    if (x-2 >= 0 && y+2 <8) {
                        if (space[x-2][y+2] == selectedPiece) {
                            space[x-1][y+1].setIcon(null);
                            space[x-1][y+1].setDisabledIcon(null);
                        }
                    }
                    
                    if (space[x][y].getIcon() == BlackKing) {
                        if (x-2 >= 0 && y-2 >= 0) {
                            if (space[x-2][y-2] == selectedPiece) {
                                space[x-1][y-1].setIcon(null);
                                space[x-1][y-1].setDisabledIcon(null);
                            }
                        }

                        if (x+2 < 8 && y-2 >= 0) {
                            if (space[x+2][y-2] == selectedPiece) {
                                space[x+1][y-1].setIcon(null);
                                space[x+1][y-1].setDisabledIcon(null);
                            }
                        }
                    }
                    
                    blackMultiCheck(x,y);
                    if (multiCheck) {
                        return;
                        
                    }
                }
                
                if (y - 1 == -1) {
                    space[x][y].setIcon(BlackKing);
                    space[x][y].setDisabledIcon(BlackKing);
                }
                selectedMulti = null;
                redMoveCheck();
            }
            
            if (selectedPiece.getIcon() == RedS || selectedPiece.getIcon() == RedKingS) { //red's move
                selectedCheck = false;
                
                for (int i = 0; i < selectedMove.size(); i++) {
                    selectedMove.get(i).setIcon(null);
                }
                
                
                if (selectedPiece.getIcon() == RedS) {
                    space[x][y].setIcon(Red);
                    space[x][y].setDisabledIcon(Red);
                }
                else {
                    space[x][y].setIcon(RedKing);
                    space[x][y].setDisabledIcon(RedKing);
                }
                
                selectedPiece.setIcon(null);
                selectedPiece.setDisabledIcon(null);
                
                if (!redJump.isEmpty()) { //remove the jumped piece, if jumped
                    if (x+2 < 8 && y-2 >= 0) {
                        if (space[x+2][y-2] == selectedPiece) {
                            space[x+1][y-1].setIcon(null);
                            space[x+1][y-1].setDisabledIcon(null);
                        }
                    }
                    
                    if (x-2 >= 0 && y-2 >= 0) {
                        if (space[x-2][y-2] == selectedPiece) {
                            space[x-1][y-1].setIcon(null);
                            space[x-1][y-1].setDisabledIcon(null);
                        }
                    }
                    
                    if (space[x][y].getIcon() == RedKing) {
                        if (x+2 < 8 && y+2 < 8) {
                            if (space[x+2][y+2] == selectedPiece) {
                                space[x+1][y+1].setIcon(null);
                                space[x+1][y+1].setDisabledIcon(null);
                            }
                        }

                        if (x-2 >= 0 && y+2 < 8) {
                            if (space[x-2][y+2] == selectedPiece) {
                                space[x-1][y+1].setIcon(null);
                                space[x-1][y+1].setDisabledIcon(null);
                            }
                        }
                    }
                    
                    redMultiCheck(x,y);
                    if (multiCheck) {
                        return;
                        
                    }
                }
                
                if (y + 1 == 8) {
                    space[x][y].setIcon(RedKing);
                    space[x][y].setDisabledIcon(RedKing);
                }
                selectedMulti = null;
                blackMoveCheck();
            }
        }
    }
    
    private void blackMultiCheck(int x, int y) {
        multiCheck = false;
        if (x-2 >= 0 && y-2 >= 0) {
            if (space[x-1][y-1].getIcon() == Red || space[x-1][y-1].getIcon() == RedKing) {
                if (space[x-2][y-2].getIcon() == null) {
                    space[x][y].setEnabled(true);
                    multiCheck = true;
                    selectedMulti = space[x][y];
                }
            }
        }
        
        if (x+2 < 8 && y-2 >= 0) {
            if (space[x+1][y-1].getIcon() == Red || space[x+1][y-1].getIcon() == RedKing) {
                if (space[x+2][y-2].getIcon() == null) {
                    space[x][y].setEnabled(true);
                    multiCheck = true;
                    selectedMulti = space[x][y];
                }
            }
        }
        
        if (space[x][y].getIcon() == BlackKing) {
            if (x+2 < 8 && y+2 < 8) {
                if (space[x+1][y+1].getIcon() == Red || space[x+1][y+1].getIcon() == RedKing) {
                    if (space[x+2][y+2].getIcon() == null) {
                        space[x][y].setEnabled(true);
                        multiCheck = true;
                        selectedMulti = space[x][y];
                    }
                }
            }

            if (x-2 >= 0 && y+2 < 8) {
                if (space[x-1][y+1].getIcon() == Red || space[x-1][y+1].getIcon() == RedKing) {
                    if (space[x-2][y+2].getIcon() == null) {
                        space[x][y].setEnabled(true);
                        multiCheck = true;
                        selectedMulti = space[x][y];
                    }
                }
            }
        }
    }
    
    private void redMultiCheck(int x, int y) {
        multiCheck = false;
        if (x+2 < 8  && y+2 < 8) {
            if (space[x+1][y+1].getIcon() == Black) {
                if (space[x+2][y+2].getIcon() == null) {
                    space[x][y].setEnabled(true);
                    multiCheck = true;
                    selectedMulti = space[x][y];
                }
            }
        }
        
        if (x-2 >= 0 && y+2 < 8) {
            if (space[x-1][y+1].getIcon() == Black) {
                if (space[x-2][y+2].getIcon() == null) {
                    space[x][y].setEnabled(true);
                    multiCheck = true;
                    selectedMulti = space[x][y];
                }
            }
        }
        
        if (space[x][y].getIcon() == RedKing) {
            if (x-2 >= 0 && y-2 >= 0) {
                if (space[x-1][y-1].getIcon() == Black || space[x-1][y-1].getIcon() == BlackKing) {
                    if (space[x-2][y-2].getIcon() == null) {
                        space[x][y].setEnabled(true);
                        multiCheck = true;
                        selectedMulti = space[x][y];
                    }
                }
            }

            if (x+2 < 8 && y-2 >= 0) {
                if (space[x+1][y-1].getIcon() == Black || space[x-1][y+1].getIcon() == BlackKing) {
                    if (space[x+2][y-2].getIcon() == null) {
                        space[x][y].setEnabled(true);
                        multiCheck = true;
                        selectedMulti = space[x][y];
                    }
                }
            }
        }
    }
    
    private void redVictory() {
        disableAll();
        imgBoard.setIcon(RedBoard);
        lblInfo.setText("The Lunar Republic is out of moves, the Solar Empire wins!");
        playTada("Tada");
    }
    
    private void blackVictory() {
        disableAll();
        imgBoard.setIcon(BlackBoard);
        lblInfo.setText("The Solar Empire is out of moves, the Lunar Republic wins!");
        playTada("Tada");
    }
    
    private void disableAll() {
        for (int y=0; y < 8; y++) {
            for (int x=0; x <8; x++) {
                if (space[x][y] != null) {
                    space[x][y].setEnabled(false);
                }
            }
        }
    }
    
    private void playTada(String soundName) { //sound FX
        if (!mute) {
            tada = this.getClass().getResourceAsStream("/Checkers/sounds/"+soundName+".wav");
            bufferTada = new BufferedInputStream(tada);
            try {
                AudioInputStream audioInputStreamTada = AudioSystem.getAudioInputStream(bufferTada);
                clipTada = AudioSystem.getClip();
                clipTada.open(audioInputStreamTada);
                clipTada.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
            }
        }
    }
    
    private void playMusic() { //sound FX
        tada = this.getClass().getResourceAsStream("/Checkers/sounds/Music.wav");
        bufferTada = new BufferedInputStream(tada);
        try {
            AudioInputStream audioInputStreamTada = AudioSystem.getAudioInputStream(bufferTada);
            clipMusic = AudioSystem.getClip();
            clipMusic.open(audioInputStreamTada);
            clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void setBoardPieces() {
        for (int y=0; y < 8; y++) {
            for (int x=0; x < 8; x++) {
                if (space[x][y] != null) {
                    if (y == 0 || y == 1 || y == 2) {
                        space[x][y].setIcon(Red);
                        space[x][y].setDisabledIcon(Red);
                    }
                    else if (y == 3 || y == 4) {
                        space[x][y].setIcon(null);
                        space[x][y].setDisabledIcon(null);
                    }
                    else {
                        space[x][y].setIcon(Black);
                        space[x][y].setDisabledIcon(Black);
                    }
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p74 = new javax.swing.JButton();
        p70 = new javax.swing.JButton();
        p30 = new javax.swing.JButton();
        p10 = new javax.swing.JButton();
        p50 = new javax.swing.JButton();
        p61 = new javax.swing.JButton();
        p21 = new javax.swing.JButton();
        p01 = new javax.swing.JButton();
        p41 = new javax.swing.JButton();
        p72 = new javax.swing.JButton();
        p32 = new javax.swing.JButton();
        p12 = new javax.swing.JButton();
        p52 = new javax.swing.JButton();
        p63 = new javax.swing.JButton();
        p23 = new javax.swing.JButton();
        p03 = new javax.swing.JButton();
        p43 = new javax.swing.JButton();
        p34 = new javax.swing.JButton();
        p14 = new javax.swing.JButton();
        p54 = new javax.swing.JButton();
        p65 = new javax.swing.JButton();
        p25 = new javax.swing.JButton();
        p05 = new javax.swing.JButton();
        p45 = new javax.swing.JButton();
        p76 = new javax.swing.JButton();
        p36 = new javax.swing.JButton();
        p16 = new javax.swing.JButton();
        p56 = new javax.swing.JButton();
        p67 = new javax.swing.JButton();
        p27 = new javax.swing.JButton();
        p07 = new javax.swing.JButton();
        p47 = new javax.swing.JButton();
        btnRematch = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        tBtnMute = new javax.swing.JToggleButton();
        lblInfo = new javax.swing.JLabel();
        imgBoard = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Checkers: Civil War");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("/Checkers/images/CheckersIcon.png")).getImage());
        setLocationByPlatform(true);
        setResizable(false);
        getContentPane().setLayout(null);

        p74.setBorderPainted(false);
        p74.setContentAreaFilled(false);
        p74.setFocusable(false);
        p74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p74ActionPerformed(evt);
            }
        });
        getContentPane().add(p74);
        p74.setBounds(784, 473, 112, 112);

        p70.setIcon(Red);
        p70.setBorderPainted(false);
        p70.setContentAreaFilled(false);
        p70.setDisabledIcon(Red);
        p70.setFocusable(false);
        p70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p70ActionPerformed(evt);
            }
        });
        getContentPane().add(p70);
        p70.setBounds(784, 25, 112, 112);

        p30.setIcon(Red);
        p30.setBorderPainted(false);
        p30.setContentAreaFilled(false);
        p30.setDisabledIcon(Red);
        p30.setFocusable(false);
        p30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p30ActionPerformed(evt);
            }
        });
        getContentPane().add(p30);
        p30.setBounds(336, 25, 112, 112);

        p10.setIcon(Red);
        p10.setBorderPainted(false);
        p10.setContentAreaFilled(false);
        p10.setDisabledIcon(Red);
        p10.setFocusable(false);
        p10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p10ActionPerformed(evt);
            }
        });
        getContentPane().add(p10);
        p10.setBounds(112, 25, 112, 112);

        p50.setIcon(Red);
        p50.setBorderPainted(false);
        p50.setContentAreaFilled(false);
        p50.setDisabledIcon(Red);
        p50.setFocusable(false);
        p50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p50ActionPerformed(evt);
            }
        });
        getContentPane().add(p50);
        p50.setBounds(560, 25, 112, 112);

        p61.setIcon(Red);
        p61.setBorderPainted(false);
        p61.setContentAreaFilled(false);
        p61.setDisabledIcon(Red);
        p61.setFocusable(false);
        p61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p61ActionPerformed(evt);
            }
        });
        getContentPane().add(p61);
        p61.setBounds(672, 137, 112, 112);

        p21.setIcon(Red);
        p21.setBorderPainted(false);
        p21.setContentAreaFilled(false);
        p21.setDisabledIcon(Red);
        p21.setFocusable(false);
        p21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p21ActionPerformed(evt);
            }
        });
        getContentPane().add(p21);
        p21.setBounds(224, 137, 112, 112);

        p01.setIcon(Red);
        p01.setBorderPainted(false);
        p01.setContentAreaFilled(false);
        p01.setDisabledIcon(Red);
        p01.setFocusable(false);
        p01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p01ActionPerformed(evt);
            }
        });
        getContentPane().add(p01);
        p01.setBounds(0, 137, 112, 112);

        p41.setIcon(Red);
        p41.setBorderPainted(false);
        p41.setContentAreaFilled(false);
        p41.setDisabledIcon(Red);
        p41.setFocusable(false);
        p41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p41ActionPerformed(evt);
            }
        });
        getContentPane().add(p41);
        p41.setBounds(448, 137, 112, 112);

        p72.setIcon(Red);
        p72.setBorderPainted(false);
        p72.setContentAreaFilled(false);
        p72.setDisabledIcon(Red);
        p72.setFocusable(false);
        p72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p72ActionPerformed(evt);
            }
        });
        getContentPane().add(p72);
        p72.setBounds(784, 249, 112, 112);

        p32.setIcon(Red);
        p32.setBorderPainted(false);
        p32.setContentAreaFilled(false);
        p32.setDisabledIcon(Red);
        p32.setFocusable(false);
        p32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p32ActionPerformed(evt);
            }
        });
        getContentPane().add(p32);
        p32.setBounds(336, 249, 112, 112);

        p12.setIcon(Red);
        p12.setBorderPainted(false);
        p12.setContentAreaFilled(false);
        p12.setDisabledIcon(Red);
        p12.setFocusable(false);
        p12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p12ActionPerformed(evt);
            }
        });
        getContentPane().add(p12);
        p12.setBounds(112, 249, 112, 112);

        p52.setIcon(Red);
        p52.setBorderPainted(false);
        p52.setContentAreaFilled(false);
        p52.setDisabledIcon(Red);
        p52.setFocusable(false);
        p52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p52ActionPerformed(evt);
            }
        });
        getContentPane().add(p52);
        p52.setBounds(560, 249, 112, 112);

        p63.setBorderPainted(false);
        p63.setContentAreaFilled(false);
        p63.setFocusable(false);
        p63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p63ActionPerformed(evt);
            }
        });
        getContentPane().add(p63);
        p63.setBounds(672, 361, 112, 112);

        p23.setBorderPainted(false);
        p23.setContentAreaFilled(false);
        p23.setFocusable(false);
        p23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p23ActionPerformed(evt);
            }
        });
        getContentPane().add(p23);
        p23.setBounds(224, 361, 112, 112);

        p03.setBorderPainted(false);
        p03.setContentAreaFilled(false);
        p03.setFocusable(false);
        p03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p03ActionPerformed(evt);
            }
        });
        getContentPane().add(p03);
        p03.setBounds(0, 361, 112, 112);

        p43.setBorderPainted(false);
        p43.setContentAreaFilled(false);
        p43.setFocusable(false);
        p43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p43ActionPerformed(evt);
            }
        });
        getContentPane().add(p43);
        p43.setBounds(448, 361, 112, 112);

        p34.setBorderPainted(false);
        p34.setContentAreaFilled(false);
        p34.setFocusable(false);
        p34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p34ActionPerformed(evt);
            }
        });
        getContentPane().add(p34);
        p34.setBounds(336, 473, 112, 112);

        p14.setBorderPainted(false);
        p14.setContentAreaFilled(false);
        p14.setFocusable(false);
        p14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p14ActionPerformed(evt);
            }
        });
        getContentPane().add(p14);
        p14.setBounds(112, 473, 112, 112);

        p54.setBorderPainted(false);
        p54.setContentAreaFilled(false);
        p54.setFocusable(false);
        p54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p54ActionPerformed(evt);
            }
        });
        getContentPane().add(p54);
        p54.setBounds(560, 473, 112, 112);

        p65.setIcon(Black);
        p65.setBorderPainted(false);
        p65.setContentAreaFilled(false);
        p65.setDisabledIcon(Black);
        p65.setFocusable(false);
        p65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p65ActionPerformed(evt);
            }
        });
        getContentPane().add(p65);
        p65.setBounds(672, 585, 112, 112);

        p25.setIcon(Black);
        p25.setBorderPainted(false);
        p25.setContentAreaFilled(false);
        p25.setDisabledIcon(Black);
        p25.setFocusable(false);
        p25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p25ActionPerformed(evt);
            }
        });
        getContentPane().add(p25);
        p25.setBounds(224, 585, 112, 112);

        p05.setIcon(Black);
        p05.setBorderPainted(false);
        p05.setContentAreaFilled(false);
        p05.setDisabledIcon(Black);
        p05.setFocusable(false);
        p05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p05ActionPerformed(evt);
            }
        });
        getContentPane().add(p05);
        p05.setBounds(0, 585, 112, 112);

        p45.setIcon(Black);
        p45.setBorderPainted(false);
        p45.setContentAreaFilled(false);
        p45.setDisabledIcon(Black);
        p45.setFocusable(false);
        p45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p45ActionPerformed(evt);
            }
        });
        getContentPane().add(p45);
        p45.setBounds(448, 585, 112, 112);

        p76.setIcon(Black);
        p76.setBorderPainted(false);
        p76.setContentAreaFilled(false);
        p76.setDisabledIcon(Black);
        p76.setFocusable(false);
        p76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p76ActionPerformed(evt);
            }
        });
        getContentPane().add(p76);
        p76.setBounds(784, 697, 112, 112);

        p36.setIcon(Black);
        p36.setBorderPainted(false);
        p36.setContentAreaFilled(false);
        p36.setDisabledIcon(Black);
        p36.setFocusable(false);
        p36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p36ActionPerformed(evt);
            }
        });
        getContentPane().add(p36);
        p36.setBounds(336, 697, 112, 112);

        p16.setIcon(Black);
        p16.setBorderPainted(false);
        p16.setContentAreaFilled(false);
        p16.setDisabledIcon(Black);
        p16.setFocusable(false);
        p16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p16ActionPerformed(evt);
            }
        });
        getContentPane().add(p16);
        p16.setBounds(112, 697, 112, 112);

        p56.setIcon(Black);
        p56.setBorderPainted(false);
        p56.setContentAreaFilled(false);
        p56.setDisabledIcon(Black);
        p56.setFocusable(false);
        p56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p56ActionPerformed(evt);
            }
        });
        getContentPane().add(p56);
        p56.setBounds(560, 697, 112, 112);

        p67.setIcon(Black);
        p67.setBorderPainted(false);
        p67.setContentAreaFilled(false);
        p67.setDisabledIcon(Black);
        p67.setFocusable(false);
        p67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p67ActionPerformed(evt);
            }
        });
        getContentPane().add(p67);
        p67.setBounds(672, 809, 112, 112);

        p27.setIcon(Black);
        p27.setBorderPainted(false);
        p27.setContentAreaFilled(false);
        p27.setDisabledIcon(Black);
        p27.setFocusable(false);
        p27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p27ActionPerformed(evt);
            }
        });
        getContentPane().add(p27);
        p27.setBounds(224, 809, 112, 112);

        p07.setIcon(Black);
        p07.setBorderPainted(false);
        p07.setContentAreaFilled(false);
        p07.setDisabledIcon(Black);
        p07.setFocusable(false);
        p07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p07ActionPerformed(evt);
            }
        });
        getContentPane().add(p07);
        p07.setBounds(0, 809, 112, 112);

        p47.setIcon(Black);
        p47.setBorderPainted(false);
        p47.setContentAreaFilled(false);
        p47.setDisabledIcon(Black);
        p47.setFocusable(false);
        p47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p47ActionPerformed(evt);
            }
        });
        getContentPane().add(p47);
        p47.setBounds(448, 809, 112, 112);

        btnRematch.setBackground(new java.awt.Color(0, 0, 0));
        btnRematch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRematch.setForeground(new java.awt.Color(255, 255, 255));
        btnRematch.setText("Rematch");
        btnRematch.setFocusable(false);
        btnRematch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRematchActionPerformed(evt);
            }
        });
        getContentPane().add(btnRematch);
        btnRematch.setBounds(0, 0, 112, 25);

        btnQuit.setBackground(new java.awt.Color(126, 0, 0));
        btnQuit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnQuit.setForeground(new java.awt.Color(255, 255, 255));
        btnQuit.setText("Quit");
        btnQuit.setFocusable(false);
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit);
        btnQuit.setBounds(784, 0, 112, 25);

        tBtnMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Checkers/images/Unmute.png"))); // NOI18N
        tBtnMute.setFocusable(false);
        tBtnMute.setRolloverEnabled(false);
        tBtnMute.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Checkers/images/Mute.png"))); // NOI18N
        tBtnMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnMuteActionPerformed(evt);
            }
        });
        getContentPane().add(tBtnMute);
        tBtnMute.setBounds(110, 3, 20, 20);

        lblInfo.setFont(new java.awt.Font("Tempus Sans ITC", 1, 20)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(170, 0, 0));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblInfo);
        lblInfo.setBounds(0, 0, 896, 25);

        imgBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Checkers/images/CheckersBoardBlack.png"))); // NOI18N
        getContentPane().add(imgBoard);
        imgBoard.setBounds(0, 0, 896, 921);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void p10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p10ActionPerformed
        buttonClick(1,0);
    }//GEN-LAST:event_p10ActionPerformed

    private void p30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p30ActionPerformed
        buttonClick(3,0);
    }//GEN-LAST:event_p30ActionPerformed

    private void p50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p50ActionPerformed
        buttonClick(5,0);
    }//GEN-LAST:event_p50ActionPerformed

    private void p70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p70ActionPerformed
        buttonClick(7,0);
    }//GEN-LAST:event_p70ActionPerformed

    private void p01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p01ActionPerformed
        buttonClick(0,1);
    }//GEN-LAST:event_p01ActionPerformed

    private void p21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p21ActionPerformed
        buttonClick(2,1);
    }//GEN-LAST:event_p21ActionPerformed

    private void p41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p41ActionPerformed
        buttonClick(4,1);
    }//GEN-LAST:event_p41ActionPerformed

    private void p61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p61ActionPerformed
        buttonClick(6,1);
    }//GEN-LAST:event_p61ActionPerformed

    private void p12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p12ActionPerformed
        buttonClick(1,2);
    }//GEN-LAST:event_p12ActionPerformed

    private void p32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p32ActionPerformed
        buttonClick(3,2);
    }//GEN-LAST:event_p32ActionPerformed

    private void p52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p52ActionPerformed
        buttonClick(5,2);
    }//GEN-LAST:event_p52ActionPerformed

    private void p72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p72ActionPerformed
        buttonClick(7,2);
    }//GEN-LAST:event_p72ActionPerformed

    private void p03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p03ActionPerformed
        buttonClick(0,3);
    }//GEN-LAST:event_p03ActionPerformed

    private void p23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p23ActionPerformed
        buttonClick(2,3);
    }//GEN-LAST:event_p23ActionPerformed

    private void p43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p43ActionPerformed
        buttonClick(4,3);
    }//GEN-LAST:event_p43ActionPerformed

    private void p63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p63ActionPerformed
        buttonClick(6,3);
    }//GEN-LAST:event_p63ActionPerformed

    private void p74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p74ActionPerformed
        buttonClick(7,4);
    }//GEN-LAST:event_p74ActionPerformed

    private void p14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p14ActionPerformed
        buttonClick(1,4);
    }//GEN-LAST:event_p14ActionPerformed

    private void p34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p34ActionPerformed
        buttonClick(3,4);
    }//GEN-LAST:event_p34ActionPerformed

    private void p54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p54ActionPerformed
        buttonClick(5,4);
    }//GEN-LAST:event_p54ActionPerformed

    private void p05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p05ActionPerformed
        buttonClick(0,5);
    }//GEN-LAST:event_p05ActionPerformed

    private void p25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p25ActionPerformed
        buttonClick(2,5);
    }//GEN-LAST:event_p25ActionPerformed

    private void p45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p45ActionPerformed
        buttonClick(4,5);
    }//GEN-LAST:event_p45ActionPerformed

    private void p65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p65ActionPerformed
        buttonClick(6,5);
    }//GEN-LAST:event_p65ActionPerformed

    private void p16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p16ActionPerformed
        buttonClick(1,6);
    }//GEN-LAST:event_p16ActionPerformed

    private void p36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p36ActionPerformed
        buttonClick(3,6);
    }//GEN-LAST:event_p36ActionPerformed

    private void p56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p56ActionPerformed
        buttonClick(5,6);
    }//GEN-LAST:event_p56ActionPerformed

    private void p76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p76ActionPerformed
        buttonClick(7,6);
    }//GEN-LAST:event_p76ActionPerformed

    private void p07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p07ActionPerformed
        buttonClick(0,7);
    }//GEN-LAST:event_p07ActionPerformed

    private void p27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p27ActionPerformed
        buttonClick(2,7);
    }//GEN-LAST:event_p27ActionPerformed

    private void p47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p47ActionPerformed
        buttonClick(4,7);
    }//GEN-LAST:event_p47ActionPerformed

    private void p67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p67ActionPerformed
        buttonClick(6,7);
    }//GEN-LAST:event_p67ActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        if (btnQuit.getText().equals("Are you Sure?")) {
            clipMusic.stop();
            clipMusic.flush();
            clipTada.stop();
            clipTada.flush();
            this.dispose();
        }
        else {
            quitTimer = new Timer(2000, new ActionListener() { //incorrect match
                        public void actionPerformed(ActionEvent e) {
                            btnQuit.setText("Quit");
                            quitTimer.stop();
                        }
                        });
                        quitTimer.setRepeats(false);
                        quitTimer.start();
                        btnQuit.setText("Are you Sure?");
        }
    }//GEN-LAST:event_btnQuitActionPerformed

    public void windowClosing(WindowEvent e) {
        clipMusic.stop();
        clipMusic.flush();
        clipTada.stop();
        clipTada.flush();
        System.out.println("hi");
    }
    
    private void btnRematchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRematchActionPerformed
        if (btnRematch.getText().equals("Are you Sure?")) {
            rematchTimer.stop();
            btnRematch.setText("Rematch");
            setBoardPieces();
            blackMoveCheck();
        }
        else {
            rematchTimer = new Timer(2000, new ActionListener() { //incorrect match
                        public void actionPerformed(ActionEvent e) {
                            btnRematch.setText("Rematch");
                            rematchTimer.stop();
                        }
                        });
                        rematchTimer.setRepeats(false);
                        rematchTimer.start();
                        btnRematch.setText("Are you Sure?");
        }
    }//GEN-LAST:event_btnRematchActionPerformed

    private void tBtnMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBtnMuteActionPerformed
        if (!tBtnMute.isSelected()) { //is the game muted
            mute =false;
            playMusic();
        }
        else {
            mute = true;
            clipMusic.stop();
            clipMusic.flush();
            clipTada.stop();
            clipTada.flush();
        }
        
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
            java.util.logging.Logger.getLogger(checkersCivilWarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkersCivilWarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkersCivilWarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkersCivilWarGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new checkersCivilWarGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnRematch;
    private javax.swing.JLabel imgBoard;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JButton p01;
    private javax.swing.JButton p03;
    private javax.swing.JButton p05;
    private javax.swing.JButton p07;
    private javax.swing.JButton p10;
    private javax.swing.JButton p12;
    private javax.swing.JButton p14;
    private javax.swing.JButton p16;
    private javax.swing.JButton p21;
    private javax.swing.JButton p23;
    private javax.swing.JButton p25;
    private javax.swing.JButton p27;
    private javax.swing.JButton p30;
    private javax.swing.JButton p32;
    private javax.swing.JButton p34;
    private javax.swing.JButton p36;
    private javax.swing.JButton p41;
    private javax.swing.JButton p43;
    private javax.swing.JButton p45;
    private javax.swing.JButton p47;
    private javax.swing.JButton p50;
    private javax.swing.JButton p52;
    private javax.swing.JButton p54;
    private javax.swing.JButton p56;
    private javax.swing.JButton p61;
    private javax.swing.JButton p63;
    private javax.swing.JButton p65;
    private javax.swing.JButton p67;
    private javax.swing.JButton p70;
    private javax.swing.JButton p72;
    private javax.swing.JButton p74;
    private javax.swing.JButton p76;
    private javax.swing.JToggleButton tBtnMute;
    // End of variables declaration//GEN-END:variables
}
