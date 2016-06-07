package TriviaGame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
/**
 *
 * @author Nick A
 */
public class triviaGUI extends javax.swing.JFrame {

    
    int numQ=30; //The number of questions
    String userAnswer;//What the user selects as their answer
    int score; //The user's position in the scoreArray
    String s; //This string hold the players name and score when they are done playing
        String[] scoreArray={"0","10","25","50","100","200","400","500","1000","2000",
        "5000","10 000","15 000","20 000","25 000","50 000","75 000","100 000",
        "150 000","200 000","250 000","300 000","350 000","400 000","500 000",
        "600 000","700 000","800 000","900 000","999 999","1 000 000"};

    String[][] question= new String[numQ][7];//The array that holds the questions
    int currentQuestion; //The user's current questions 
    ArrayList <Integer> order = new ArrayList();//The arraylist reorders the questions, to be random

    String name; //The user's name
    /**
     * Creates new form triviaGUI
     */
    public triviaGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scoreboard = new javax.swing.JTextArea();
        Title = new javax.swing.JLabel();
        aButton = new javax.swing.JButton();
        bButton = new javax.swing.JButton();
        cButton = new javax.swing.JButton();
        dButton = new javax.swing.JButton();
        answerText = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        nameText = new javax.swing.JTextField();
        startButton = new javax.swing.JButton();
        questionText = new javax.swing.JTextField();
        outputText = new javax.swing.JTextField();
        nextButton = new javax.swing.JButton();
        scoreboardLabel = new javax.swing.JLabel();
        allScoreLabel = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Millionaire");
        setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
        setResizable(false);

        jPanel1.setLayout(null);

        scoreLabel.setBackground(new java.awt.Color(204, 204, 204));
        scoreLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("Score: ");
        jPanel1.add(scoreLabel);
        scoreLabel.setBounds(1400, 450, 140, 29);

        scoreboard.setEditable(false);
        scoreboard.setColumns(20);
        scoreboard.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        scoreboard.setRows(5);
        scoreboard.setFocusable(false);
        scoreboard.setOpaque(false);
        jScrollPane1.setViewportView(scoreboard);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 340, 290, 450);

        Title.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        Title.setForeground(new java.awt.Color(240, 240, 240));
        Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TriviaGame/logo.png"))); // NOI18N
        Title.setToolTipText("");
        jPanel1.add(Title);
        Title.setBounds(660, 0, 200, 210);

        aButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        aButton.setText("A");
        aButton.setBorder(null);
        aButton.setBorderPainted(false);
        aButton.setEnabled(false);
        aButton.setFocusable(false);
        aButton.setPreferredSize(new java.awt.Dimension(50, 50));
        aButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aButtonActionPerformed(evt);
            }
        });
        jPanel1.add(aButton);
        aButton.setBounds(330, 460, 460, 60);

        bButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        bButton.setText("B");
        bButton.setBorder(null);
        bButton.setBorderPainted(false);
        bButton.setEnabled(false);
        bButton.setFocusable(false);
        bButton.setPreferredSize(new java.awt.Dimension(50, 50));
        bButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bButtonActionPerformed(evt);
            }
        });
        jPanel1.add(bButton);
        bButton.setBounds(810, 460, 460, 60);

        cButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        cButton.setText("C");
        cButton.setBorder(null);
        cButton.setBorderPainted(false);
        cButton.setEnabled(false);
        cButton.setFocusable(false);
        cButton.setPreferredSize(new java.awt.Dimension(50, 50));
        cButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cButton);
        cButton.setBounds(330, 530, 460, 60);

        dButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        dButton.setText("D");
        dButton.setBorder(null);
        dButton.setBorderPainted(false);
        dButton.setEnabled(false);
        dButton.setFocusable(false);
        dButton.setPreferredSize(new java.awt.Dimension(50, 50));
        dButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dButtonActionPerformed(evt);
            }
        });
        jPanel1.add(dButton);
        dButton.setBounds(810, 530, 460, 60);

        answerText.setEditable(false);
        answerText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        answerText.setFocusable(false);
        jPanel1.add(answerText);
        answerText.setBounds(640, 600, 150, 30);

        submitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submitButton.setText("Submit");
        submitButton.setBorderPainted(false);
        submitButton.setEnabled(false);
        submitButton.setFocusable(false);
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(submitButton);
        submitButton.setBounds(810, 600, 100, 30);

        nameText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameText.setText("Name");
        jPanel1.add(nameText);
        nameText.setBounds(10, 10, 140, 50);

        startButton.setText("START");
        startButton.setBorder(null);
        startButton.setBorderPainted(false);
        startButton.setFocusable(false);
        startButton.setPreferredSize(new java.awt.Dimension(50, 50));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        jPanel1.add(startButton);
        startButton.setBounds(170, 10, 90, 50);

        questionText.setEditable(false);
        questionText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        questionText.setText("Question:");
        questionText.setFocusable(false);
        jPanel1.add(questionText);
        questionText.setBounds(330, 210, 940, 40);

        outputText.setEditable(false);
        outputText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        outputText.setText("Pick your name, and press 'START' to start/restart.      Good Luck!");
        outputText.setFocusable(false);
        jPanel1.add(outputText);
        outputText.setBounds(330, 650, 740, 40);

        nextButton.setText("Next");
        nextButton.setEnabled(false);
        nextButton.setFocusable(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        jPanel1.add(nextButton);
        nextButton.setBounds(1090, 650, 70, 40);

        scoreboardLabel.setBackground(new java.awt.Color(204, 204, 204));
        scoreboardLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        scoreboardLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreboardLabel.setText("Your Score:");
        jPanel1.add(scoreboardLabel);
        scoreboardLabel.setBounds(970, 20, 310, 29);

        allScoreLabel.setBackground(new java.awt.Color(204, 204, 204));
        allScoreLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        allScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        allScoreLabel.setText("Recent Scores:");
        jPanel1.add(allScoreLabel);
        allScoreLabel.setBounds(10, 280, 200, 29);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TriviaGame/wwtbam-background_02.jpg"))); // NOI18N
        Background.setOpaque(true);
        jPanel1.add(Background);
        Background.setBounds(0, 0, 1500, 790);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aButtonActionPerformed
        userAnswer = "a"; //Changes the user's currently selected answer.
        answerText.setText("Current Answer: "+userAnswer.toUpperCase());
        submitButton.setEnabled(true); //Allows the user to submit answers
    }//GEN-LAST:event_aButtonActionPerformed

    private void bButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bButtonActionPerformed
        userAnswer = "b"; //Changes the user's currently selected answer.
        answerText.setText("Current Answer: "+userAnswer.toUpperCase());
        submitButton.setEnabled(true); //Allows the user to submit answers
    }//GEN-LAST:event_bButtonActionPerformed

    private void cButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButtonActionPerformed
        userAnswer = "c";//Changes the user's currently selected answer.
        answerText.setText("Current Answer: "+userAnswer.toUpperCase());
        submitButton.setEnabled(true); //Allows the user to submit answers
    }//GEN-LAST:event_cButtonActionPerformed

    private void dButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dButtonActionPerformed
        userAnswer = "d";//Changes the user's currently selected answer.
        answerText.setText("Current Answer: "+userAnswer.toUpperCase());
        submitButton.setEnabled(true); //Allows the user to submit answers
    }//GEN-LAST:event_dButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        nextButton.setEnabled(true); //Allows the user to contine playing
        try {
            checkAnswer();//Checks the user answer to see if it is correct.
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(triviaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        currentQuestion=0;//Resets the questions that the user is on
        score=0; //Resets the players score
        scoreboardLabel.setText("Your score: " +scoreArray[score]);//Updates the scoreboard
        scoreLabel.setText("Score: "+ score);
        name=nameText.getText(); //Gets the user's name for the ens
        aButton.setEnabled(true);
        bButton.setEnabled(true);
        cButton.setEnabled(true);
        dButton.setEnabled(true);
        nameText.setEditable(false);
        nameText.setFocusable(false);
        answerText.setText(null);
        outputText.setText(null);
        startButton.setEnabled(false); //Disables this button until the end of the game.
        for (int i = 0; i <= 29; i++){//This will make a list of all of the questions
            order.add(i);
        }
        Collections.shuffle(order); //Shuffles the order of the questions

        askQuestion(order.get(currentQuestion)); //Asks the first question.
    }//GEN-LAST:event_startButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        currentQuestion++; //This int will decide which questions is asked, asks the next question.
        askQuestion(order.get(currentQuestion)); //Runs the method that will ask a the next question
        submitButton.setEnabled(true);//Enables the button to submit answers again
        outputText.setText(null);
        answerText.setText(null);
        submitButton.setEnabled(false);
    }//GEN-LAST:event_nextButtonActionPerformed

    public String questions(int x,int i){
   
   question[0][0]="What is '5' in roman numerals? ";//The question for question 0
   question[0][1]="A) V ";//Answer a for question 0
   question[0][2]="B) 5";//Answer b for question 0
   question[0][3]="C) IIIII";//Answer c for question 0
   question[0][4]="D) Fiv";//Answer d for question 0
   question[0][5]="a";//Corrrect answer for question 0 
   question[0][6]="Correct! The answer is V!"; //This message is printed if the user is correct
   
   question[1][0]="Who gave the US the Statue of Liberty?";//The question for question 1
   question[1][1]="A) Canada";//Answer a for question 1
   question[1][2]="B) The Netherlands";//Answer b for question 1
   question[1][3]="C) Nelson Mandela";//Answer c for question 1
   question[1][4]="D) France ";//Answer d for question 1
   question[1][5]="d";//Corrrect answer for question 1 
   question[1][6]="Correct! France gave the US the Statue of Liberty!"; //This message is printed if the user is correct
   
   question[2][0]="What is the fourth planet from the sun?";//The question for question 2
   question[2][1]="A) Earth";//Answer a for question 2
   question[2][2]="B) Jupiter";//Answer b for question 2
   question[2][3]="C) Mars";//Answer c for question 2
   question[2][4]="D) Saturn";//Answer d for question 2
   question[2][5]="c";//Corrrect answer for question  2 
   question[2][6]="Good Job! Mars is fourth from the sun, Earth is 3rd"; //This message is printed if the user is correct
   
   question[3][0]="How many neck bones does a giraffe have? ";//The question for question 3
   question[3][1]="A) 0";//Answer a for question 3
   question[3][2]="B) 7";//Answer b for question 3
   question[3][3]="C) 11";//Answer c for question 3
   question[3][4]="D) 15";//Answer d for question 3
   question[3][5]="b";//Corrrect answer for question 3
   question[3][6]="Correct! Did you know that Humans and giraffes have the same amount of neck vertebrae!"; //This message is printed if the user is correct
   
   question[4][0]="Where is the Taj Mahal located? ";//The question for question 4
   question[4][1]="A) India";//Answer a for question 4
   question[4][2]="B) Iran";//Answer b for question 4
   question[4][3]="C) South Africa ";//Answer c for question 4
   question[4][4]="D) Brazil ";//Answer d for question 4
   question[4][5]="a";//Corrrect answer for question 4 
   question[4][6]="Good Job! The Taj Mahal is located in India"; //This message is printed if the user is correct
   
   question[5][0]="What is the coloured part of the eye?";//The question for question 5
   question[5][1]="A) Cornea ";//Answer a for question 5
   question[5][2]="B) Pupil ";//Answer b for question 5
   question[5][3]="C) Iris ";//Answer c for question 5
   question[5][4]="D) Lens";//Answer d for question 5
   question[5][5]="c";//Corrrect answer for question 5
   question[5][6]="Correct! Eye see you know a lot."; //This message is printed if the user is correct
   
   question[6][0]="What is the largest ocean on earth? ";//The question for question 6
   question[6][1]="A) Atlantic  ";//Answer a for question 6
   question[6][2]="B) Indian ";//Answer b for question 6
   question[6][3]="C) Arctic ";//Answer c for question 6
   question[6][4]="D) Pacific ";//Answer d for question 6
   question[6][5]="d";//Corrrect answer for question 6 
   question[6][6]="Good job! Keep it up!"; //This message is printed if the user is correct
   
   question[7][0]="What is the powerhouse of the cell?";//The question for question 7
   question[7][1]="A) Cytoplasm ";//Answer a for question 7
   question[7][2]="B) Ribosomes ";//Answer b for question 7
   question[7][3]="C) Mitochondria ";//Answer c for question 7
   question[7][4]="D) Nucleus ";//Answer d for question 7
   question[7][5]="c";//Corrrect answer for question 7
   question[7][6]="You're a powerhouse at trivia"; //This message is printed if the user is correct
   
   question[8][0]="Which superhero owns Stark Industries?";//The question for question 8
   question[8][1]="A) Tony Bark  ";//Answer a for question 8
   question[8][2]="B) Iron Man ";//Answer b for question 8
   question[8][3]="C) Spiderman ";//Answer c for question 8
   question[8][4]="D) Superman ";//Answer d for question 8
   question[8][5]="b";//Corrrect answer for question 8
   question[8][6]="Your superpower is smarts! Almost done!"; //This message is printed if the user is correct
   
   question[9][0]="Which of the following animals floats in water?";
   question[9][1]="A) Cat";
   question[9][2]="B) Porcupine";
   question[9][3]="C) Hippo";
   question[9][4]="D) Pig";
   question[9][5]="b";
   question[9][6]="Yes! It's because their quills are hollow!";
   
   question[10][0]="What is the periodic symbol for 'Sodium'?";//The question
   question[10][1]="A) Na";//Answer a
   question[10][2]="B) S";//Answer b 
   question[10][3]="C) So";//Answer c
   question[10][4]="D) Sa";//Answer d 
   question[10][5]="a";//Corrrect answer 
   question[10][6]="YOU DID IT!!!! Good Job. Press Next to continue"; //This message is printed if the user is correct
   
   question[11][0]="What city is located in both Europe and Asia?";//The question
   question[11][1]="A) Rome";//Answer a 
   question[11][2]="B) Moscow";//Answer b 
   question[11][3]="C) Stockholm";//Answer c 
   question[11][4]="D) Istanbul";//Answer d 
   question[11][5]="d";//Corrrect answer 
   question[11][6]="YOU DID IT!!!! Good Job. Press Next to continue"; //This message is printed if the user is correct
   
   question[12][0]="What are the 3 most spoken languages in order?";//The question
   question[12][1]="A) Mandarin, English, French ";//Answer a 
   question[12][2]="B) Spanish, English, Mandarin ";//Answer b 
   question[12][3]="C) Mandarin, Spanish, English";//Answer c 
   question[12][4]="D) English, French, Spanish";//Answer d 
   question[12][5]="c";//Corrrect answer 
   question[12][6]="Correct! That was a tricky one, Good job!"; //This message is printed if the user is correct
   
   question[13][0]="What portion of the world lives in China?";//The question
   question[13][1]="A) One third";//Answer a 
   question[13][2]="B) One fifth ";//Answer b 
   question[13][3]="C) One eighth";//Answer c 
   question[13][4]="D) One tenth";//Answer d 
   question[13][5]="b";//Corrrect answer 
   question[13][6]="Right! Good job."; //This message is printed if the user is correct
   
   question[14][0]="Where did the 2000 olympics take place?";//The question
   question[14][1]="A) Salt Lake City, Utah";//Answer a 
   question[14][2]="B) Sydney, Australia ";//Answer b 
   question[14][3]="C) Nagano, Japan";//Answer c 
   question[14][4]="D) Moscow, Russia";//Answer d 
   question[14][5]="b";//Corrrect answer 
   question[14][6]="You're an olympian at this!"; //This message is printed if the user is correct
   
   question[15][0]="Where are the sleepers in a railroad station?";//The question
   question[15][1]="A) On the tracks";//Answer a 
   question[15][2]="B) Selling tickets ";//Answer b 
   question[15][3]="C) It's a room to sleep";//Answer c 
   question[15][4]="D) On the station floor";//Answer d 
   question[15][5]="a";//Corrrect answer 
   question[15][6]="CHOOO CHOOOO!"; //This message is printed if the user is correct
   
   question[16][0]="Where was the first stadium with a moving roof built?";//The question
   question[16][1]="A) Berlin Germany";//Answer a 
   question[16][2]="B) Sydney, Australia ";//Answer b 
   question[16][3]="C) Hong Kong, China";//Answer c 
   question[16][4]="D) Toronto, Canada";//Answer d 
   question[16][5]="d";//Corrrect answer 
   question[16][6]="You're moving, to a high score!"; //This message is printed if the user is correct
   
   question[17][0]="Which character does Ewan McGregor play in Star Wars?";//The question
   question[17][1]="A) Yoda";//Answer a 
   question[17][2]="B) Darth Vader";//Answer b 
   question[17][3]="C) Obi Wan Kenobi";//Answer c 
   question[17][4]="D) Jar Jar Binks";//Answer d 
   question[17][5]="c";//Corrrect answer 
   question[17][6]="You are the chosen one!"; //This message is printed if the user is correct
   
   question[18][0]="How far is the moon from earth?";//The question
   question[18][1]="A) 384 400 Km";//Answer a 
   question[18][2]="B) 965 000 Km ";//Answer b 
   question[18][3]="C) 679 600 Km";//Answer c 
   question[18][4]="D) 830 910 Km";//Answer d 
   question[18][5]="a";//Corrrect answer 
   question[18][6]="It's closer than you think. Like you to the high score"; //This message is printed if the user is correct
   
   question[19][0]="How many planets start with the letter 'M'?";//The question
   question[19][1]="A) 1";//Answer a 
   question[19][2]="B) 2";//Answer b 
   question[19][3]="C) 3";//Answer c 
   question[19][4]="D) 4";//Answer d 
   question[19][5]="b";//Corrrect answer 
   question[19][6]="Mars and Mercury both start with M"; //This message is printed if the user is correct
   
   question[20][0]="Which musical note comes after do, re, mi?";//The question
   question[20][1]="A) ma ";//Answer a 
   question[20][2]="B) da";//Answer b 
   question[20][3]="C) la";//Answer c 
   question[20][4]="D) fa";//Answer d 
   question[20][5]="d";//Corrrect answer 
   question[20][6]="You're a prodigy at this!"; //This message is printed if the user is correct
   
   question[21][0]="How many keys are on a grand piano?";//The question
   question[21][1]="A) 55";//Answer a 
   question[21][2]="B) 66";//Answer b 
   question[21][3]="C) 77";//Answer c 
   question[21][4]="D) 88";//Answer d 
   question[21][5]="d";//Corrrect answer 
   question[21][6]="You sure know your stuff!"; //This message is printed if the user is correct
  
   question[22][0]="What colour does litmus paper turn in an acidic liquid?";//The question
   question[22][1]="A) Red";//Answer a 
   question[22][2]="B) Blue";//Answer b 
   question[22][3]="C) Yellow";//Answer c 
   question[22][4]="D) Green";//Answer d 
   question[22][5]="a";//Corrrect answer 
   question[22][6]="Correct! You're a brainiac"; //This message is printed if the user is correct
   
   question[23][0]="What travels on fibre optic cables?";//The question
   question[23][1]="A) sound";//Answer a 
   question[23][2]="B) electricity";//Answer b 
   question[23][3]="C) light";//Answer c 
   question[23][4]="D) heat/cold";//Answer d 
   question[23][5]="c";//Corrrect answer 
   question[23][6]="Good job! You are really bright"; //This message is printed if the user is correct
   
   question[24][0]="What is the name of Canada's most northern community?";//The question
   question[24][1]="A) Icy, Nunuavut ";//Answer a 
   question[24][2]="B) Alert, Nunavut";//Answer b 
   question[24][3]="C) Elsa, Nunavut";//Answer c 
   question[24][4]="D) Eskimo, Nunavut";//Answer d 
   question[24][5]="b";//Corrrect answer 
   question[24][6]="ALERT, It's cold up there!"; //This message is printed if the user is correct
   
   question[25][0]="Which continent do chinchillas come from?";//The question
   question[25][1]="A) North America";//Answer a 
   question[25][2]="B) South America";//Answer b 
   question[25][3]="C) Africa";//Answer c 
   question[25][4]="D) Europe";//Answer d 
   question[25][5]="b";//Corrrect answer 
   question[25][6]="You're chinCHILL"; //This message is printed if the user is correct
 
   question[26][0]="What was the name of the first dog in space?";//The question
   question[26][1]="A) Laika ";//Answer a 
   question[26][2]="B) Lassie ";//Answer b 
   question[26][3]="C) Pluto";//Answer c 
   question[26][4]="D) Wolf";//Answer d 
   question[26][5]="a";//Corrrect answer 
   question[26][6]="You're a star at trivia"; //This message is printed if the user is correct
   
   question[27][0]="Which of these was an early model of bicycle?";//The question
   question[27][1]="A) Penny Farthing ";//Answer a 
   question[27][2]="B) Penny Black";//Answer b 
   question[27][3]="C) Penny Whistle";//Answer c 
   question[27][4]="D) Shiny Penny";//Answer d 
   question[27][5]="a";//Corrrect answer 
   question[27][6]="Correct, look them up, they look funny!"; //This message is printed if the user is correct
  
   question[28][0]="Which calendar month is named after a Roman ruler?";//The question
   question[28][1]="A) November";//Answer a 
   question[28][2]="B) March";//Answer b 
   question[28][3]="C) August";//Answer c 
   question[28][4]="D) October";//Answer d 
   question[28][5]="c";//Corrrect answer 
   question[28][6]="Looks like you are smart every month!"; //This message is printed if the user is correct
   
   question[29][0]="What is the capital of Norway?";//The question
   question[29][1]="A) Halmstad";//Answer a 
   question[29][2]="B) Hamburg";//Answer b 
   question[29][3]="C) Bern";//Answer c 
   question[29][4]="D) Oslo";//Answer d 
   question[29][5]="d";//Corrrect answer 
   question[29][6]="Good Job! Move on the the next one!"; //This message is printed if the user is correct
   
   String temp=question[x][i]; //This will get the desired string
   
   return temp;
   }

    public void askQuestion(int x){
        printQuestion(x);//Runs the method that will print the question/answers
        nextButton.setEnabled(false);
    }
    
    public void printQuestion(int currentQuestion){ 
    //This method will get and print the question and possible answers to the UI
        questionText.setText(questions(currentQuestion,0));
        aButton.setText(questions(currentQuestion,1));
        bButton.setText(questions(currentQuestion,2));
        cButton.setText(questions(currentQuestion,3));
        dButton.setText(questions(currentQuestion,4));  
    }
    
    public void checkAnswer() throws UnsupportedEncodingException{
        if(userAnswer.equals(questions(order.get(currentQuestion),5))){//The user answers correctly.
            score++;  //Adds to the users score.
            scoreboardLabel.setText("Your score: $" +scoreArray[score]);//Updates the scoreboard
            outputText.setText(questions(order.get(currentQuestion),6)); //Prints the question's flavourtext.
            nextButton.setEnabled(true);//The user can only hit the next button.
            submitButton.setEnabled(false);
           
            if(score==30){//If the user has won the game, the following happens.
                nextButton.setEnabled(false);//Disables the buttons to the user cannot continue.
                submitButton.setEnabled(false);
                outputText.setText("You won! Press 'START' to restart."); //Gives the user the option to restart
                scoreboardPrint();//Prints the user's score to the scoreboard
                startButton.setEnabled(true); //Enables the user to play again.
                nameText.setEditable(true);
                nameText.setFocusable(true);
            }
            
        }else{
            outputText.setText("Sorry, that was incorrect, you achieved a score of: $" +scoreArray[score]+ ". Press 'START' to play again/new player.");
            scoreboardPrint();
            nextButton.setEnabled(false); //Disables the buttons so the user cannot proceed, onlyrestart
            submitButton.setEnabled(false);
            aButton.setEnabled(false);
            bButton.setEnabled(false);
            cButton.setEnabled(false);
            dButton.setEnabled(false);
            startButton.setEnabled(true); //Enables the user to play again.
            order.clear();//Clears the order of the questions
            nameText.setEditable(true);
            nameText.setFocusable(true);
        }
    }

    public void scoreboardPrint(){
    s= name+": "+scoreArray[score];//This line just holds what is printed when a score is displayed
    scoreboard.append(s); //Prints the user's score and name to the scoreboard 
    scoreboard.append(System.lineSeparator());
    }
    
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
            java.util.logging.Logger.getLogger(triviaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(triviaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(triviaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(triviaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new triviaGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Title;
    private javax.swing.JButton aButton;
    private javax.swing.JLabel allScoreLabel;
    private javax.swing.JTextField answerText;
    private javax.swing.JButton bButton;
    private javax.swing.JButton cButton;
    private javax.swing.JButton dButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton nextButton;
    private javax.swing.JTextField outputText;
    private javax.swing.JTextField questionText;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JTextArea scoreboard;
    private javax.swing.JLabel scoreboardLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
