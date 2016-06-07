package Connect4;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Connect 4
 * This program was made by Jordan Ellis on April 18, 2016
 */
public class connect4GUI extends javax.swing.JFrame {

    JLabel[][] board = new JLabel[7][7];
    ArrayList<JButton> hoverButton = new ArrayList();
    
    int redScore=0, yellowScore=0;
    
    boolean redTurn; //is it red's turn
    
    ImageIcon Red = new ImageIcon(getClass().getResource("/Connect4/images/Red.png"));
    ImageIcon RedH = new ImageIcon(getClass().getResource("/Connect4/images/RedH.png"));
    ImageIcon RedC = new ImageIcon(getClass().getResource("/Connect4/images/RedC.png"));
    ImageIcon Yellow = new ImageIcon(getClass().getResource("/Connect4/images/Yellow.png"));
    ImageIcon YellowH = new ImageIcon(getClass().getResource("/Connect4/images/YellowH.png"));
    ImageIcon YellowC = new ImageIcon(getClass().getResource("/Connect4/images/YellowC.png"));
    
    Timer quitTimer, newRoundTimer;
    
    Icon winTest; //which icon to test for wins with. (red or yellow)
    int checkCountH, checkCountV, checkCount1D, checkCount2D; //keep track of how many in a row.
    ArrayList<JLabel> winPieceH = new ArrayList(); //track which pieces are winning pieces
    ArrayList<JLabel> winPieceV = new ArrayList();
    ArrayList<JLabel> winPiece1D = new ArrayList();
    ArrayList<JLabel> winPiece2D = new ArrayList();
    ArrayList<ArrayList<JLabel>> winPieceLoop = new ArrayList();
    Icon connectPiece; //the winning piece's green colour icon
    
    JLabel currentPiece; //(temp) the last placed Piece
    
    Clip clipSound; //sounds variables
    InputStream Sound, bufferSound;
    boolean mute = false;
    int rndNum; //used to pick one of the 2 sound clips for Homer & Burns
    
    /**
     * Creates new form connect4GUI
     */
    public connect4GUI() {
        initComponents();
        
        setSize(844,909);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //center the window
        this.setLocation(dim.width/2-this.getSize().width/2, 0);
        this.getContentPane().setBackground(Color.BLACK);
        
        try {
        Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/Connect4/Font/Simpsonfont.ttf")).deriveFont(Font.PLAIN, 13);
        txtRedScore.setFont(font);
        txtYellowScore.setFont(font);
        btnNewRound.setFont(font);
        btnQuit.setFont(font);
        } 
        catch(FontFormatException | IOException ex) { 
            System.out.println(ex.getMessage()); 
        }
        
        setBoard(); //add the coordinates to the 2D array
        setList();
        firstTurn(); //randomize the first turn
        setHover();
        
    }

    private void setBoard() { //add the board pieces to the 2D array
        board[0][0] = p00;
        board[1][0] = p10;
        board[2][0] = p20;
        board[3][0] = p30;
        board[4][0] = p40;
        board[5][0] = p50;
        board[6][0] = p60;
        board[0][1] = p01;
        board[1][1] = p11;
        board[2][1] = p21;
        board[3][1] = p31;
        board[4][1] = p41;
        board[5][1] = p51;
        board[6][1] = p61;
        board[0][2] = p02;
        board[1][2] = p12;
        board[2][2] = p22;
        board[3][2] = p32;
        board[4][2] = p42;
        board[5][2] = p52;
        board[6][2] = p62;
        board[0][3] = p03;
        board[1][3] = p13;
        board[2][3] = p23;
        board[3][3] = p33;
        board[4][3] = p43;
        board[5][3] = p53;
        board[6][3] = p63;
        board[0][4] = p04;
        board[1][4] = p14;
        board[2][4] = p24;
        board[3][4] = p34;
        board[4][4] = p44;
        board[5][4] = p54;
        board[6][4] = p64;
        board[0][5] = p05;
        board[1][5] = p15;
        board[2][5] = p25;
        board[3][5] = p35;
        board[4][5] = p45;
        board[5][5] = p55;
        board[6][5] = p65;
        board[0][6] = p06;
        board[1][6] = p16;
        board[2][6] = p26;
        board[3][6] = p36;
        board[4][6] = p46;
        board[5][6] = p56;
        board[6][6] = p66;
        

        
    }
    
    private void setList() { //add the buttons to an arraylist
        Collections.addAll(hoverButton, hover0, hover1, hover2, hover3, hover4, hover5, hover6);
    }
    
    private void firstTurn() {
        Random RFT = new Random();
        int FT = RFT.nextInt(2);
        if (FT == 0) {
            redTurn = true;
        }
        else {
            redTurn = false;
        }
        System.out.println(redTurn);
    }
    
    private void setHover() { //set the icons for the buttons
            for (int i=0; i<7; i++) {
                if (redTurn) {
                    hoverButton.get(i).setIcon(RedH);
                    hoverButton.get(i).setRolloverIcon(Red);
                    hoverButton.get(i).setPressedIcon(Red);
                }
                else {
                    hoverButton.get(i).setIcon(YellowH);
                    hoverButton.get(i).setRolloverIcon(Yellow);
                    hoverButton.get(i).setPressedIcon(Yellow);
                }
            }
    }
    
    private void placePiece(int column) {
        for (int i=6; i>-1; i--) {
            if (board[column][i].getIcon() == null) { //check for an empty space to place a piece
                currentPiece = board[column][i];
                
                if (redTurn) { //see which turn it is
                    board[column][i].setIcon(Red);
                    redTurn = false;
                    winTest = Red;
                }
                else {
                    board[column][i].setIcon(Yellow);
                    redTurn = true;
                    winTest = Yellow;
                }
                setHover(); //set the hover button icons
                checkWin(column, i);
                break;
            }
        }
        
        if (board[column][0].getIcon() != null) {
            hoverButton.get(column).setEnabled(false);
            hoverButton.get(column).setVisible(false);
        }
    }
    
    private void checkWin(int X, int Y) {
        winPieceH.clear(); //clear the winning pieces lists
        winPieceV.clear();
        winPiece1D.clear();
        winPiece2D.clear();
        checkCountH = 1; checkCountV = 1; checkCount1D = 1; checkCount2D = 1; //set the counting trackers
        
        //count horizontal pieces
        for (int H1=X; H1-1>-1; H1--) {
            if (board[H1-1][Y].getIcon() == winTest) { //check to the left of the placed piece.
                checkCountH += 1; //how many valid pieces are in a row horizontally
                winPieceH.add(board[H1-1][Y]);
            }
            else {
                break;
            }  
        }
        for (int H2=X; H2+1<7; H2++) {
            if (board[H2+1][Y].getIcon() == winTest) { //check to the right of the placed piece.
                checkCountH += 1; //how many valid pieces are in a row horizontally
                winPieceH.add(board[H2+1][Y]);
            }
            else {
                break;
            }
        }
        
        //count vertical pieces
        for (int V1=Y; V1-1>-1; V1--) {
            if (board[X][V1-1].getIcon() == winTest) { //check vertically of the placed piece.
                checkCountV += 1; //how many valid pieces are in a row vertically
                winPieceV.add(board[X][V1-1]);
            }
            else {
                break;
            }
        }
        for (int V2 = Y; V2+1<7; V2++) {
            if (board[X][V2+1].getIcon() == winTest) { //check vertically of the placed piece.
                checkCountV += 1; //how many valid pieces are in a row vertically
                winPieceV.add(board[X][V2+1]);
            }
            else {
                break;
            }
        }
        
        //count diagonal pieces (diagonal 1)
        for (int x1D1=X, y1D1=Y; x1D1-1>-1 && y1D1-1>-1; x1D1--, y1D1--) {
            if (board[x1D1-1][y1D1-1].getIcon() == winTest) { //check diagonally of the placed piece.
                checkCount1D += 1; //how many valid pieces are in a row diagonally 
                winPiece1D.add(board[x1D1-1][y1D1-1]);
            }
            else {
                break;
            }
        }
        for (int x1D2=X, y1D2=Y; x1D2+1<7 && y1D2+1<7; x1D2++, y1D2++) {
            if (board[x1D2+1][y1D2+1].getIcon() == winTest) { //check diagonally of the placed piece.
                checkCount1D += 1; //how many valid pieces are in a row diagonally
                winPiece1D.add(board[x1D2+1][y1D2+1]);
            }
            else {
                break;
            }
        }
            
            //count diagonal pieces (diagonal 2)
        for (int x2D1=X, y2D1=Y; x2D1-1>-1 && y2D1+1<7; x2D1--, y2D1++) {
            if (board[x2D1-1][y2D1+1].getIcon() == winTest) { //check diagonally left of the placed piece.
                checkCount2D += 1; //how many valid pieces are in a row diagonally 
                winPiece2D.add(board[x2D1-1][y2D1+1]);
            }
            else {
                break;
            }
        }
        for (int x2D2=X, y2D2=Y; x2D2+1<7 && y2D2-1>-1; x2D2++, y2D2--) {
            if (board[x2D2+1][y2D2-1].getIcon() == winTest) { //check diagonally of the placed piece.
                checkCount2D += 1; //how many valid pieces are in a row diagonally
                winPiece2D.add(board[x2D2+1][y2D2-1]);
            }
            else {
                break;
            }
        }
        
        
        //Check for a victory
        if (checkCountH >= 4 || checkCountV >= 4 || checkCount1D >= 4 || checkCount2D >= 4) {
            winRound();
        }
    }
    
    
    private void winRound() {
        setRndNum();
        System.out.println(rndNum);
        if (!redTurn) { //red victory
            redScore += 1;
            txtRedScore.setText(String.valueOf(redScore)); //count the score
            connectPiece = RedC;
            playSound("Burns", rndNum);
        }
        else { //yellow victory
            yellowScore += 1;
            txtYellowScore.setText(String.valueOf(yellowScore));
            connectPiece = YellowC;
            playSound("Homer", rndNum);
        }
        
        for (int i = 0; i<7; i++) {
            hoverButton.get(i).setEnabled(false); //disable the buttons
            hoverButton.get(i).setVisible(false);
        }
        winPieceLoop.clear();
        if (checkCountH >=4) {
            winPieceLoop.add(winPieceH); //add all of the "winning pieces" to an array for looping
        }

        if (checkCountV >=4) {
            winPieceLoop.add(winPieceV);
        }

        if (checkCount1D >=4) {
            winPieceLoop.add(winPiece1D);
        }

        if (checkCount2D >=4) {
            winPieceLoop.add(winPiece2D);
        }
        
        currentPiece.setIcon(connectPiece);
        for (int i=0; i<winPieceLoop.size(); i++) {
            for (int j=0; j<winPieceLoop.get(i).size(); j++) {
                winPieceLoop.get(i).get(j).setIcon(connectPiece); //set all of the winning icons
            }
        }
    }
    
    private void setRndNum() {
        Random rnGen = new Random();
        rndNum = rnGen.nextInt(2);
    }
    
    private void playSound(String soundName, int soundNumber) { //sound FX
        if (!mute) {
            System.out.println("/Connect4/Sounds/"+soundName+Integer.toString(rndNum)+".wav");
            Sound = this.getClass().getResourceAsStream("/Connect4/Sounds/"+soundName+Integer.toString(rndNum)+".wav");
            bufferSound = new BufferedInputStream(Sound);
            try {
                AudioInputStream audioInputStreamTada = AudioSystem.getAudioInputStream(bufferSound);
                clipSound = AudioSystem.getClip();
                clipSound.open(audioInputStreamTada);
                clipSound.start();
                System.out.println(clipSound.isActive());
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
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

        hover0 = new javax.swing.JButton();
        hover1 = new javax.swing.JButton();
        hover2 = new javax.swing.JButton();
        hover3 = new javax.swing.JButton();
        hover4 = new javax.swing.JButton();
        hover5 = new javax.swing.JButton();
        hover6 = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        btnNewRound = new javax.swing.JButton();
        txtYellowScore = new javax.swing.JTextField();
        iconYellow = new javax.swing.JLabel();
        iconRed = new javax.swing.JLabel();
        txtRedScore = new javax.swing.JTextField();
        tBtnMute = new javax.swing.JToggleButton();
        imgBoard = new javax.swing.JLabel();
        p00 = new javax.swing.JLabel();
        p10 = new javax.swing.JLabel();
        p20 = new javax.swing.JLabel();
        p30 = new javax.swing.JLabel();
        p40 = new javax.swing.JLabel();
        p50 = new javax.swing.JLabel();
        p60 = new javax.swing.JLabel();
        p01 = new javax.swing.JLabel();
        p11 = new javax.swing.JLabel();
        p21 = new javax.swing.JLabel();
        p31 = new javax.swing.JLabel();
        p41 = new javax.swing.JLabel();
        p51 = new javax.swing.JLabel();
        p61 = new javax.swing.JLabel();
        p02 = new javax.swing.JLabel();
        p12 = new javax.swing.JLabel();
        p22 = new javax.swing.JLabel();
        p32 = new javax.swing.JLabel();
        p42 = new javax.swing.JLabel();
        p52 = new javax.swing.JLabel();
        p62 = new javax.swing.JLabel();
        p03 = new javax.swing.JLabel();
        p13 = new javax.swing.JLabel();
        p23 = new javax.swing.JLabel();
        p33 = new javax.swing.JLabel();
        p43 = new javax.swing.JLabel();
        p53 = new javax.swing.JLabel();
        p63 = new javax.swing.JLabel();
        p04 = new javax.swing.JLabel();
        p14 = new javax.swing.JLabel();
        p24 = new javax.swing.JLabel();
        p34 = new javax.swing.JLabel();
        p44 = new javax.swing.JLabel();
        p54 = new javax.swing.JLabel();
        p64 = new javax.swing.JLabel();
        p05 = new javax.swing.JLabel();
        p15 = new javax.swing.JLabel();
        p25 = new javax.swing.JLabel();
        p35 = new javax.swing.JLabel();
        p45 = new javax.swing.JLabel();
        p55 = new javax.swing.JLabel();
        p65 = new javax.swing.JLabel();
        p06 = new javax.swing.JLabel();
        p16 = new javax.swing.JLabel();
        p26 = new javax.swing.JLabel();
        p36 = new javax.swing.JLabel();
        p46 = new javax.swing.JLabel();
        p56 = new javax.swing.JLabel();
        p66 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Connect 4: Simpsons");
        setBackground(new java.awt.Color(0, 0, 0));
        setIconImage(new ImageIcon(getClass().getResource("/Connect4/images/YellowSmall.png")).getImage());
        setMaximizedBounds(new java.awt.Rectangle(0, 0, 829, 859));
        getContentPane().setLayout(null);

        hover0.setContentAreaFilled(false);
        hover0.setFocusable(false);
        hover0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover0ActionPerformed(evt);
            }
        });
        getContentPane().add(hover0);
        hover0.setBounds(31, 71, 85, 85);

        hover1.setContentAreaFilled(false);
        hover1.setFocusable(false);
        hover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover1ActionPerformed(evt);
            }
        });
        getContentPane().add(hover1);
        hover1.setBounds(145, 71, 85, 85);

        hover2.setContentAreaFilled(false);
        hover2.setFocusable(false);
        hover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover2ActionPerformed(evt);
            }
        });
        getContentPane().add(hover2);
        hover2.setBounds(258, 71, 85, 85);

        hover3.setContentAreaFilled(false);
        hover3.setFocusable(false);
        hover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover3ActionPerformed(evt);
            }
        });
        getContentPane().add(hover3);
        hover3.setBounds(372, 71, 85, 85);

        hover4.setContentAreaFilled(false);
        hover4.setFocusable(false);
        hover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover4ActionPerformed(evt);
            }
        });
        getContentPane().add(hover4);
        hover4.setBounds(485, 71, 85, 85);

        hover5.setContentAreaFilled(false);
        hover5.setFocusable(false);
        hover5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover5ActionPerformed(evt);
            }
        });
        getContentPane().add(hover5);
        hover5.setBounds(599, 71, 85, 85);

        hover6.setContentAreaFilled(false);
        hover6.setFocusable(false);
        hover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover6ActionPerformed(evt);
            }
        });
        getContentPane().add(hover6);
        hover6.setBounds(712, 71, 85, 85);

        btnQuit.setBackground(new java.awt.Color(255, 255, 0));
        btnQuit.setText("Quit");
        btnQuit.setFocusable(false);
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit);
        btnQuit.setBounds(743, 10, 70, 23);

        btnNewRound.setBackground(new java.awt.Color(255, 255, 0));
        btnNewRound.setText("New Round");
        btnNewRound.setFocusable(false);
        btnNewRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRoundActionPerformed(evt);
            }
        });
        getContentPane().add(btnNewRound);
        btnNewRound.setBounds(630, 10, 110, 23);

        txtYellowScore.setEditable(false);
        txtYellowScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtYellowScore.setText("0");
        txtYellowScore.setFocusable(false);
        getContentPane().add(txtYellowScore);
        txtYellowScore.setBounds(175, 10, 30, 25);

        iconYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Connect4/images/YellowSmall.png"))); // NOI18N
        getContentPane().add(iconYellow);
        iconYellow.setBounds(130, 5, 35, 35);

        iconRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Connect4/images/RedSmall.png"))); // NOI18N
        getContentPane().add(iconRed);
        iconRed.setBounds(5, 5, 35, 35);

        txtRedScore.setEditable(false);
        txtRedScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRedScore.setText("0");
        txtRedScore.setFocusable(false);
        getContentPane().add(txtRedScore);
        txtRedScore.setBounds(50, 10, 30, 25);

        tBtnMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Connect4/images/Unmute.png"))); // NOI18N
        tBtnMute.setFocusable(false);
        tBtnMute.setRolloverEnabled(false);
        tBtnMute.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Connect4/images/Mute.png"))); // NOI18N
        tBtnMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnMuteActionPerformed(evt);
            }
        });
        getContentPane().add(tBtnMute);
        tBtnMute.setBounds(610, 10, 20, 20);

        imgBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Connect4/images/Board.png"))); // NOI18N
        getContentPane().add(imgBoard);
        imgBoard.setBounds(0, 0, 829, 870);
        getContentPane().add(p00);
        p00.setBounds(31, 71, 85, 85);
        getContentPane().add(p10);
        p10.setBounds(145, 71, 85, 85);
        getContentPane().add(p20);
        p20.setBounds(258, 71, 85, 85);
        getContentPane().add(p30);
        p30.setBounds(372, 71, 85, 85);
        getContentPane().add(p40);
        p40.setBounds(485, 71, 85, 85);
        getContentPane().add(p50);
        p50.setBounds(599, 71, 85, 85);
        getContentPane().add(p60);
        p60.setBounds(712, 71, 85, 85);
        getContentPane().add(p01);
        p01.setBounds(31, 185, 85, 85);
        getContentPane().add(p11);
        p11.setBounds(145, 185, 85, 85);
        getContentPane().add(p21);
        p21.setBounds(258, 185, 85, 85);
        getContentPane().add(p31);
        p31.setBounds(372, 185, 85, 85);
        getContentPane().add(p41);
        p41.setBounds(485, 185, 85, 85);
        getContentPane().add(p51);
        p51.setBounds(599, 185, 85, 85);
        getContentPane().add(p61);
        p61.setBounds(712, 185, 85, 85);
        getContentPane().add(p02);
        p02.setBounds(31, 299, 85, 85);
        getContentPane().add(p12);
        p12.setBounds(145, 299, 85, 85);
        getContentPane().add(p22);
        p22.setBounds(258, 299, 85, 85);
        getContentPane().add(p32);
        p32.setBounds(372, 299, 85, 85);
        getContentPane().add(p42);
        p42.setBounds(485, 299, 85, 85);
        getContentPane().add(p52);
        p52.setBounds(599, 299, 85, 85);
        getContentPane().add(p62);
        p62.setBounds(712, 299, 85, 85);
        getContentPane().add(p03);
        p03.setBounds(31, 412, 85, 85);
        getContentPane().add(p13);
        p13.setBounds(145, 412, 85, 85);
        getContentPane().add(p23);
        p23.setBounds(258, 412, 85, 85);
        getContentPane().add(p33);
        p33.setBounds(372, 412, 85, 85);
        getContentPane().add(p43);
        p43.setBounds(485, 412, 85, 85);
        getContentPane().add(p53);
        p53.setBounds(599, 412, 85, 85);
        getContentPane().add(p63);
        p63.setBounds(712, 412, 85, 85);
        getContentPane().add(p04);
        p04.setBounds(31, 526, 85, 85);
        getContentPane().add(p14);
        p14.setBounds(145, 526, 85, 85);
        getContentPane().add(p24);
        p24.setBounds(258, 526, 85, 85);
        getContentPane().add(p34);
        p34.setBounds(372, 526, 85, 85);
        getContentPane().add(p44);
        p44.setBounds(485, 526, 85, 85);
        getContentPane().add(p54);
        p54.setBounds(599, 526, 85, 85);
        getContentPane().add(p64);
        p64.setBounds(712, 526, 85, 85);
        getContentPane().add(p05);
        p05.setBounds(31, 640, 85, 85);
        getContentPane().add(p15);
        p15.setBounds(145, 640, 85, 85);
        getContentPane().add(p25);
        p25.setBounds(258, 640, 85, 85);
        getContentPane().add(p35);
        p35.setBounds(372, 640, 85, 85);
        getContentPane().add(p45);
        p45.setBounds(485, 640, 85, 85);
        getContentPane().add(p55);
        p55.setBounds(599, 640, 85, 85);
        getContentPane().add(p65);
        p65.setBounds(712, 640, 85, 85);
        getContentPane().add(p06);
        p06.setBounds(31, 753, 85, 85);
        getContentPane().add(p16);
        p16.setBounds(145, 753, 85, 85);
        getContentPane().add(p26);
        p26.setBounds(258, 753, 85, 85);
        getContentPane().add(p36);
        p36.setBounds(372, 753, 85, 85);
        getContentPane().add(p46);
        p46.setBounds(485, 753, 85, 85);
        getContentPane().add(p56);
        p56.setBounds(599, 753, 85, 85);
        getContentPane().add(p66);
        p66.setBounds(712, 753, 85, 85);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hover0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover0ActionPerformed
        placePiece(0);
    }//GEN-LAST:event_hover0ActionPerformed

    private void hover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover1ActionPerformed
        placePiece(1);
    }//GEN-LAST:event_hover1ActionPerformed

    private void hover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover2ActionPerformed
        placePiece(2);
    }//GEN-LAST:event_hover2ActionPerformed

    private void hover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover3ActionPerformed
        placePiece(3);
    }//GEN-LAST:event_hover3ActionPerformed

    private void hover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover4ActionPerformed
        placePiece(4);
    }//GEN-LAST:event_hover4ActionPerformed

    private void hover5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover5ActionPerformed
        placePiece(5);
    }//GEN-LAST:event_hover5ActionPerformed

    private void hover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hover6ActionPerformed
        placePiece(6);
    }//GEN-LAST:event_hover6ActionPerformed

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        if (btnQuit.getText().equals("Quit?")) {
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
                        btnQuit.setText("Quit?");
        }
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnNewRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRoundActionPerformed
        if (btnNewRound.getText().equals("New Round?")) {
            btnNewRound.setText("New Round");
            for (int i=0; i<7; i++) {
                for (int j=0; j<7; j++) {
                    board[i][j].setIcon(null); //reset the images
                }
            }
            for (int i = 0; i<7; i++) {
                hoverButton.get(i).setEnabled(true); //reenable the buttons
                hoverButton.get(i).setVisible(true);
            }
        }
        else {
            newRoundTimer = new Timer(2000, new ActionListener() { //incorrect match
                        public void actionPerformed(ActionEvent e) {
                            btnNewRound.setText("New Round");
                            newRoundTimer.stop();
                        }
                        });
                        newRoundTimer.setRepeats(false);
                        newRoundTimer.start();
                        btnNewRound.setText("New Round?");
        }
    }//GEN-LAST:event_btnNewRoundActionPerformed

    private void tBtnMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBtnMuteActionPerformed
        if (!tBtnMute.isSelected()) { //is the game muted
            mute =false;
        }
        else {
            mute = true;
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
            java.util.logging.Logger.getLogger(connect4GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(connect4GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(connect4GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(connect4GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new connect4GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewRound;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton hover0;
    private javax.swing.JButton hover1;
    private javax.swing.JButton hover2;
    private javax.swing.JButton hover3;
    private javax.swing.JButton hover4;
    private javax.swing.JButton hover5;
    private javax.swing.JButton hover6;
    private javax.swing.JLabel iconRed;
    private javax.swing.JLabel iconYellow;
    private javax.swing.JLabel imgBoard;
    private javax.swing.JLabel p00;
    private javax.swing.JLabel p01;
    private javax.swing.JLabel p02;
    private javax.swing.JLabel p03;
    private javax.swing.JLabel p04;
    private javax.swing.JLabel p05;
    private javax.swing.JLabel p06;
    private javax.swing.JLabel p10;
    private javax.swing.JLabel p11;
    private javax.swing.JLabel p12;
    private javax.swing.JLabel p13;
    private javax.swing.JLabel p14;
    private javax.swing.JLabel p15;
    private javax.swing.JLabel p16;
    private javax.swing.JLabel p20;
    private javax.swing.JLabel p21;
    private javax.swing.JLabel p22;
    private javax.swing.JLabel p23;
    private javax.swing.JLabel p24;
    private javax.swing.JLabel p25;
    private javax.swing.JLabel p26;
    private javax.swing.JLabel p30;
    private javax.swing.JLabel p31;
    private javax.swing.JLabel p32;
    private javax.swing.JLabel p33;
    private javax.swing.JLabel p34;
    private javax.swing.JLabel p35;
    private javax.swing.JLabel p36;
    private javax.swing.JLabel p40;
    private javax.swing.JLabel p41;
    private javax.swing.JLabel p42;
    private javax.swing.JLabel p43;
    private javax.swing.JLabel p44;
    private javax.swing.JLabel p45;
    private javax.swing.JLabel p46;
    private javax.swing.JLabel p50;
    private javax.swing.JLabel p51;
    private javax.swing.JLabel p52;
    private javax.swing.JLabel p53;
    private javax.swing.JLabel p54;
    private javax.swing.JLabel p55;
    private javax.swing.JLabel p56;
    private javax.swing.JLabel p60;
    private javax.swing.JLabel p61;
    private javax.swing.JLabel p62;
    private javax.swing.JLabel p63;
    private javax.swing.JLabel p64;
    private javax.swing.JLabel p65;
    private javax.swing.JLabel p66;
    private javax.swing.JToggleButton tBtnMute;
    private javax.swing.JTextField txtRedScore;
    private javax.swing.JTextField txtYellowScore;
    // End of variables declaration//GEN-END:variables
}
