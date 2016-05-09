
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

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
    
    ImageIcon Red = new ImageIcon(getClass().getResource("/images/Red.png"));
    ImageIcon RedH = new ImageIcon(getClass().getResource("/images/RedH.png"));
    ImageIcon RedC = new ImageIcon(getClass().getResource("/images/RedC.png"));
    ImageIcon Yellow = new ImageIcon(getClass().getResource("/images/Yellow.png"));
    ImageIcon YellowH = new ImageIcon(getClass().getResource("/images/YellowH.png"));
    ImageIcon YellowC = new ImageIcon(getClass().getResource("/images/YellowC.png"));
    
    
    
    Icon winTest; //which icon to test for wins with. (red or yellow)
    int checkCountH, checkCountV, checkCount1D, checkCount2D; //keep track of how many in a row.
    ArrayList<JLabel> winPieceH = new ArrayList(); //track which pieces are winning pieces
    ArrayList<JLabel> winPieceV = new ArrayList();
    ArrayList<JLabel> winPiece1D = new ArrayList();
    ArrayList<JLabel> winPiece2D = new ArrayList();
    ArrayList<ArrayList<JLabel>> winPieceLoop = new ArrayList();
    Icon connectPiece; //the winning piece's green colour icon
    
    JLabel currentPiece; //(temp) the last placed Piece
    

    
    
    /**
     * Creates new form connect4GUI
     */
    public connect4GUI() {
        initComponents();
        
        setSize(844,999);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //center the window
        this.setLocation(dim.width/2-this.getSize().width/2, 0);
        
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
        int FT = RFT.nextInt(4);
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
        if (!redTurn) { //red victory
            redScore += 1;
            txtRedScore.setText(String.valueOf(redScore)); //count the score
            connectPiece = RedC;
        }
        else { //yellow victory
            yellowScore += 1;
            txtYellowScore.setText(String.valueOf(yellowScore));
            connectPiece = YellowC;
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
        lblTitle = new javax.swing.JLabel();
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
        btnQuit = new javax.swing.JButton();
        btnNewRound = new javax.swing.JButton();
        txtYellowScore = new javax.swing.JTextField();
        iconYellow = new javax.swing.JLabel();
        iconRed = new javax.swing.JLabel();
        txtRedScore = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connect 4");
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
        hover0.setBounds(31, 47, 85, 85);

        hover1.setContentAreaFilled(false);
        hover1.setFocusable(false);
        hover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover1ActionPerformed(evt);
            }
        });
        getContentPane().add(hover1);
        hover1.setBounds(145, 47, 85, 85);

        hover2.setContentAreaFilled(false);
        hover2.setFocusable(false);
        hover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover2ActionPerformed(evt);
            }
        });
        getContentPane().add(hover2);
        hover2.setBounds(258, 47, 85, 85);

        hover3.setContentAreaFilled(false);
        hover3.setFocusable(false);
        hover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover3ActionPerformed(evt);
            }
        });
        getContentPane().add(hover3);
        hover3.setBounds(372, 47, 85, 85);

        hover4.setContentAreaFilled(false);
        hover4.setFocusable(false);
        hover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover4ActionPerformed(evt);
            }
        });
        getContentPane().add(hover4);
        hover4.setBounds(485, 47, 85, 85);

        hover5.setContentAreaFilled(false);
        hover5.setFocusable(false);
        hover5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover5ActionPerformed(evt);
            }
        });
        getContentPane().add(hover5);
        hover5.setBounds(599, 47, 85, 85);

        hover6.setContentAreaFilled(false);
        hover6.setFocusable(false);
        hover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hover6ActionPerformed(evt);
            }
        });
        getContentPane().add(hover6);
        hover6.setBounds(712, 47, 85, 85);

        lblTitle.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Connect 4");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(339, 10, 150, 30);

        imgBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Board.png"))); // NOI18N
        getContentPane().add(imgBoard);
        imgBoard.setBounds(5, 135, 819, 819);
        getContentPane().add(p00);
        p00.setBounds(31, 161, 85, 85);
        getContentPane().add(p10);
        p10.setBounds(145, 161, 85, 85);
        getContentPane().add(p20);
        p20.setBounds(258, 161, 85, 85);
        getContentPane().add(p30);
        p30.setBounds(372, 161, 85, 85);
        getContentPane().add(p40);
        p40.setBounds(485, 161, 85, 85);
        getContentPane().add(p50);
        p50.setBounds(599, 161, 85, 85);
        getContentPane().add(p60);
        p60.setBounds(712, 161, 85, 85);
        getContentPane().add(p01);
        p01.setBounds(31, 275, 85, 85);
        getContentPane().add(p11);
        p11.setBounds(145, 275, 85, 85);
        getContentPane().add(p21);
        p21.setBounds(258, 275, 85, 85);
        getContentPane().add(p31);
        p31.setBounds(372, 275, 85, 85);
        getContentPane().add(p41);
        p41.setBounds(485, 275, 85, 85);
        getContentPane().add(p51);
        p51.setBounds(599, 275, 85, 85);
        getContentPane().add(p61);
        p61.setBounds(712, 275, 85, 85);
        getContentPane().add(p02);
        p02.setBounds(31, 389, 85, 85);
        getContentPane().add(p12);
        p12.setBounds(145, 389, 85, 85);
        getContentPane().add(p22);
        p22.setBounds(258, 389, 85, 85);
        getContentPane().add(p32);
        p32.setBounds(372, 389, 85, 85);
        getContentPane().add(p42);
        p42.setBounds(485, 389, 85, 85);
        getContentPane().add(p52);
        p52.setBounds(599, 389, 85, 85);
        getContentPane().add(p62);
        p62.setBounds(712, 389, 85, 85);
        getContentPane().add(p03);
        p03.setBounds(31, 502, 85, 85);
        getContentPane().add(p13);
        p13.setBounds(145, 502, 85, 85);
        getContentPane().add(p23);
        p23.setBounds(258, 502, 85, 85);
        getContentPane().add(p33);
        p33.setBounds(372, 502, 85, 85);
        getContentPane().add(p43);
        p43.setBounds(485, 502, 85, 85);
        getContentPane().add(p53);
        p53.setBounds(599, 502, 85, 85);
        getContentPane().add(p63);
        p63.setBounds(712, 502, 85, 85);
        getContentPane().add(p04);
        p04.setBounds(31, 616, 85, 85);
        getContentPane().add(p14);
        p14.setBounds(145, 616, 85, 85);
        getContentPane().add(p24);
        p24.setBounds(258, 616, 85, 85);
        getContentPane().add(p34);
        p34.setBounds(372, 616, 85, 85);
        getContentPane().add(p44);
        p44.setBounds(485, 616, 85, 85);
        getContentPane().add(p54);
        p54.setBounds(599, 616, 85, 85);
        getContentPane().add(p64);
        p64.setBounds(712, 616, 85, 85);
        getContentPane().add(p05);
        p05.setBounds(31, 730, 85, 85);
        getContentPane().add(p15);
        p15.setBounds(145, 730, 85, 85);
        getContentPane().add(p25);
        p25.setBounds(258, 730, 85, 85);
        getContentPane().add(p35);
        p35.setBounds(372, 730, 85, 85);
        getContentPane().add(p45);
        p45.setBounds(485, 730, 85, 85);
        getContentPane().add(p55);
        p55.setBounds(599, 730, 85, 85);
        getContentPane().add(p65);
        p65.setBounds(712, 730, 85, 85);
        getContentPane().add(p06);
        p06.setBounds(31, 843, 85, 85);
        getContentPane().add(p16);
        p16.setBounds(145, 843, 85, 85);
        getContentPane().add(p26);
        p26.setBounds(258, 843, 85, 85);
        getContentPane().add(p36);
        p36.setBounds(372, 843, 85, 85);
        getContentPane().add(p46);
        p46.setBounds(485, 843, 85, 85);
        getContentPane().add(p56);
        p56.setBounds(599, 843, 85, 85);
        getContentPane().add(p66);
        p66.setBounds(712, 843, 85, 85);

        btnQuit.setBackground(new java.awt.Color(51, 102, 0));
        btnQuit.setForeground(new java.awt.Color(255, 255, 255));
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit);
        btnQuit.setBounds(760, 10, 53, 23);

        btnNewRound.setBackground(new java.awt.Color(51, 102, 0));
        btnNewRound.setForeground(new java.awt.Color(255, 255, 255));
        btnNewRound.setText("New Round");
        btnNewRound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewRoundActionPerformed(evt);
            }
        });
        getContentPane().add(btnNewRound);
        btnNewRound.setBounds(660, 10, 95, 23);

        txtYellowScore.setEditable(false);
        txtYellowScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtYellowScore.setText("0");
        txtYellowScore.setFocusable(false);
        getContentPane().add(txtYellowScore);
        txtYellowScore.setBounds(175, 10, 30, 25);

        iconYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/YellowSmall.png"))); // NOI18N
        getContentPane().add(iconYellow);
        iconYellow.setBounds(130, 5, 35, 35);

        iconRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RedSmall.png"))); // NOI18N
        getContentPane().add(iconRed);
        iconRed.setBounds(5, 5, 35, 35);

        txtRedScore.setEditable(false);
        txtRedScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRedScore.setText("0");
        txtRedScore.setFocusable(false);
        getContentPane().add(txtRedScore);
        txtRedScore.setBounds(50, 10, 30, 25);

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
        System.exit(0);
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnNewRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewRoundActionPerformed
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                board[i][j].setIcon(null); //reset the images
            }
        }
        for (int i = 0; i<7; i++) {
            hoverButton.get(i).setEnabled(true); //reenable the buttons
            hoverButton.get(i).setVisible(true);
        } 
    }//GEN-LAST:event_btnNewRoundActionPerformed

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
    private javax.swing.JLabel lblTitle;
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
    private javax.swing.JTextField txtRedScore;
    private javax.swing.JTextField txtYellowScore;
    // End of variables declaration//GEN-END:variables
}
