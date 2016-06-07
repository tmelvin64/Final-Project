package MatchingGame;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;


/**
 *
 * This program was created by Jordan Ellis on March 30, 2016 as practice with
 * arrays.
 */
public class matchGUI extends javax.swing.JFrame {


    
    ArrayList<JButton> cards = new ArrayList(); //button (card) array
    ArrayList<Icon> icons = new ArrayList(); //icon array
    ArrayList<Icon> locations = new ArrayList(); //randomized card/icon order

    boolean flipCheck = false; //tracks 1st or second card flip
    int pairsLeft = 12, score = 0, flipNum, cardTrack = 0; //tracking variable
    
    Timer myTimer;

    ImageIcon Car = new ImageIcon(getClass().getResource("/MatchingGame/Images/Car.png")); //icons
    ImageIcon Dog = new ImageIcon(getClass().getResource("/MatchingGame/Images/Dog.png"));
    ImageIcon Egon = new ImageIcon(getClass().getResource("/MatchingGame/Images/Egon.png"));
    ImageIcon Marshmallow = new ImageIcon(getClass().getResource("/MatchingGame/Images/Marshmallow.png"));
    ImageIcon Painting = new ImageIcon(getClass().getResource("/MatchingGame/Images/Painting.png"));
    ImageIcon ProtonPack = new ImageIcon(getClass().getResource("/MatchingGame/Images/ProtonPack.png"));
    ImageIcon Ray = new ImageIcon(getClass().getResource("/MatchingGame/Images/Ray.png"));
    ImageIcon Slimer = new ImageIcon(getClass().getResource("/MatchingGame/Images/Slimer.png"));
    ImageIcon Trap = new ImageIcon(getClass().getResource("/MatchingGame/Images/Trap.png"));
    ImageIcon Twinkie = new ImageIcon(getClass().getResource("/MatchingGame/Images/Twinkie.png"));
    ImageIcon Venkman = new ImageIcon(getClass().getResource("/MatchingGame/Images/Venkman.png"));
    ImageIcon Zeddemore = new ImageIcon(getClass().getResource("/MatchingGame/Images/Zeddemore.png"));
    
    InputStream Music, bufferMusic, Sound, bufferSound;
    Clip clipMusic, clipSound;
    boolean mute; //is the game muted?

    private void lists() { //initializes the button/card and the icon lists.
        Collections.addAll(icons, Car, Car, Dog, Dog, Egon, Egon, Marshmallow, Marshmallow, Painting, Painting,
                ProtonPack, ProtonPack, Ray, Ray, Slimer, Slimer, Trap, Trap, Twinkie, Twinkie, Venkman, Venkman, Zeddemore, Zeddemore);

        Collections.addAll(cards, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11,
                btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23);
    }

    private void shuffle() { //randomizes the icons to the cards/buttons
        lists();

        Random rnI = new Random();
        for (int i = 0; i < 24; i++) {
            int iNum = rnI.nextInt(icons.size());
            Collections.addAll(locations, icons.get(iNum));
            cards.get(i).setDisabledIcon(icons.get(iNum)); //sets the icons
            //cards.get(i).setIcon(icons.get(iNum));
            //System.out.println(icons.get(iNum));
            icons.remove(iNum);
        }
    }
    
    private void matchCheck(int cardNum) {
        if (cardTrack < 2) {
            System.out.println(flipCheck);
            if (!flipCheck) {
                flipCheck = true;
                // Card number flipped
                flipNum = cardNum;

                System.out.println("First Card Clicked " + cardNum);
                cards.get(cardNum).setEnabled(false); //disable the first card
                cardTrack += 1;
            } 
            else {
                System.out.println("Second Card Clicked " + cardNum);
                // Disable the 2nd card
                cards.get(cardNum).setEnabled(false);
                cardTrack += 1;
                
                if (locations.get(cardNum) == locations.get(flipNum)) { //correct match
                    flipCheck = false;
                    cardTrack = 0;
                    pairsLeft -= 1;
                    
                    if (clipSound.isOpen()) {
                        clipSound.stop();
                        clipSound.flush();
                    }
                    
                    playMatchSound(cardNum);
                    System.out.println(locations.get(cardNum));
                }
                else {
                    myTimer = new Timer(1100, new ActionListener() { //incorrect match
                    public void actionPerformed(ActionEvent e) {
                        
                        if (locations.get(cardNum) != locations.get(flipNum)) {
                            cards.get(cardNum).setEnabled(true);
                            cards.get(flipNum).setEnabled(true);
                            flipCheck = false;
                        }
                        cardTrack = 0;
                        myTimer.stop();
                    }
                    });
                    myTimer.setRepeats(false);
                    myTimer.start();
                }
            }
        }
    }
    

    private void gameStart() {
        for (int i=0; i<cards.size(); i++) {
            cards.get(i).setEnabled(true); //reset all the cards
        }
        locations.clear();
        shuffle(); //re-randomize cards
        flipCheck = false;
    }

    /**
     * Creates new form matchGUI
     */
    public matchGUI() {
        initComponents();
        getContentPane().setBackground(new Color(51,153,255));
        playMusic();
        gameStart();
        setSize(571,979);
        playSound("BelieveYou");
    }

    private void playMatchSound(int cardNum) {
        if (locations.get(cardNum) == Dog) {
            playSound("Dog");
        }
        else if (locations.get(cardNum) == Car) {
            playSound("Car");
        }
        else if (locations.get(cardNum) == Egon) {
            playSound("Egon");
        }
        else if (locations.get(cardNum) == Marshmallow) {
            playSound("Marshmallow");
        }
        else if (locations.get(cardNum) == Painting) {
            playSound("Painting");
        }
        else if (locations.get(cardNum) == ProtonPack) {
            playSound("ProtonPack");
        }
        else if (locations.get(cardNum) == Ray) {
            playSound("Ray");
        }
        else if (locations.get(cardNum) == Slimer) {
            playSound("Slimer");
        }
        else if (locations.get(cardNum) == Trap) {
            playSound("Trap");
        }
        else if (locations.get(cardNum) == Twinkie) {
            playSound("Twinkie");
        }
        else if (locations.get(cardNum) == Venkman) {
            playSound("Venkman");
        }
        else if (locations.get(cardNum) == Zeddemore) {
            playSound("Zeddemore");
        }
    }
    
    private void playMusic() { //Music Theme
        Music = this.getClass().getResourceAsStream("/MatchingGame/Sounds/Theme.wav");
        bufferMusic = new BufferedInputStream(Music);
        try {
            AudioInputStream audioInputStreamTada = AudioSystem.getAudioInputStream(bufferMusic);
            clipMusic = AudioSystem.getClip();
            clipMusic.open(audioInputStreamTada);
            clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void playSound(String soundName) { //sound FX
        if (!mute) {
            Sound = this.getClass().getResourceAsStream("/MatchingGame/Sounds/"+soundName+".wav");
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

        btn0 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn11 = new javax.swing.JButton();
        btn12 = new javax.swing.JButton();
        btn13 = new javax.swing.JButton();
        btn14 = new javax.swing.JButton();
        btn15 = new javax.swing.JButton();
        btn16 = new javax.swing.JButton();
        btn17 = new javax.swing.JButton();
        btn18 = new javax.swing.JButton();
        btn19 = new javax.swing.JButton();
        btn20 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        btn22 = new javax.swing.JButton();
        btn23 = new javax.swing.JButton();
        btnRestart = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        tBtnMute = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ghostbusters Matching");
        setIconImage(new ImageIcon(getClass().getResource("/MatchingGame/Images/Icon.png")).getImage());
        setResizable(false);
        getContentPane().setLayout(null);

        btn0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn0.setToolTipText("");
        btn0.setBorderPainted(false);
        btn0.setContentAreaFilled(false);
        btn0.setFocusable(false);
        btn0.setHideActionText(true);
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        getContentPane().add(btn0);
        btn0.setBounds(10, 108, 100, 100);

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn1.setToolTipText("");
        btn1.setBorderPainted(false);
        btn1.setContentAreaFilled(false);
        btn1.setFocusable(false);
        btn1.setHideActionText(true);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn1);
        btn1.setBounds(120, 108, 100, 100);

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn2.setToolTipText("");
        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);
        btn2.setFocusable(false);
        btn2.setHideActionText(true);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn2);
        btn2.setBounds(230, 108, 100, 100);

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn3.setToolTipText("");
        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.setFocusable(false);
        btn3.setHideActionText(true);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn3);
        btn3.setBounds(340, 108, 100, 100);

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn4.setToolTipText("");
        btn4.setBorderPainted(false);
        btn4.setContentAreaFilled(false);
        btn4.setFocusable(false);
        btn4.setHideActionText(true);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn4);
        btn4.setBounds(450, 108, 100, 100);

        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn5.setToolTipText("");
        btn5.setBorderPainted(false);
        btn5.setContentAreaFilled(false);
        btn5.setFocusable(false);
        btn5.setHideActionText(true);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        getContentPane().add(btn5);
        btn5.setBounds(10, 214, 100, 100);

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn6.setToolTipText("");
        btn6.setBorderPainted(false);
        btn6.setContentAreaFilled(false);
        btn6.setFocusable(false);
        btn6.setHideActionText(true);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        getContentPane().add(btn6);
        btn6.setBounds(120, 214, 100, 100);

        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn7.setToolTipText("");
        btn7.setBorderPainted(false);
        btn7.setContentAreaFilled(false);
        btn7.setFocusable(false);
        btn7.setHideActionText(true);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        getContentPane().add(btn7);
        btn7.setBounds(230, 214, 100, 100);

        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn8.setToolTipText("");
        btn8.setBorderPainted(false);
        btn8.setContentAreaFilled(false);
        btn8.setFocusable(false);
        btn8.setHideActionText(true);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        getContentPane().add(btn8);
        btn8.setBounds(340, 214, 100, 100);

        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn9.setToolTipText("");
        btn9.setBorderPainted(false);
        btn9.setContentAreaFilled(false);
        btn9.setFocusable(false);
        btn9.setHideActionText(true);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        getContentPane().add(btn9);
        btn9.setBounds(450, 214, 100, 100);

        btn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn10.setToolTipText("");
        btn10.setBorderPainted(false);
        btn10.setContentAreaFilled(false);
        btn10.setFocusable(false);
        btn10.setHideActionText(true);
        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        getContentPane().add(btn10);
        btn10.setBounds(10, 320, 100, 100);

        btn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn11.setToolTipText("");
        btn11.setBorderPainted(false);
        btn11.setContentAreaFilled(false);
        btn11.setFocusable(false);
        btn11.setHideActionText(true);
        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        getContentPane().add(btn11);
        btn11.setBounds(120, 320, 100, 100);

        btn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn12.setToolTipText("");
        btn12.setBorderPainted(false);
        btn12.setContentAreaFilled(false);
        btn12.setFocusable(false);
        btn12.setHideActionText(true);
        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        getContentPane().add(btn12);
        btn12.setBounds(340, 320, 100, 100);

        btn13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn13.setToolTipText("");
        btn13.setBorderPainted(false);
        btn13.setContentAreaFilled(false);
        btn13.setFocusable(false);
        btn13.setHideActionText(true);
        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });
        getContentPane().add(btn13);
        btn13.setBounds(450, 320, 100, 100);

        btn14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn14.setToolTipText("");
        btn14.setBorderPainted(false);
        btn14.setContentAreaFilled(false);
        btn14.setFocusable(false);
        btn14.setHideActionText(true);
        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });
        getContentPane().add(btn14);
        btn14.setBounds(10, 426, 100, 100);

        btn15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn15.setToolTipText("");
        btn15.setBorderPainted(false);
        btn15.setContentAreaFilled(false);
        btn15.setFocusable(false);
        btn15.setHideActionText(true);
        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });
        getContentPane().add(btn15);
        btn15.setBounds(120, 426, 100, 100);

        btn16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn16.setToolTipText("");
        btn16.setBorderPainted(false);
        btn16.setContentAreaFilled(false);
        btn16.setFocusable(false);
        btn16.setHideActionText(true);
        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });
        getContentPane().add(btn16);
        btn16.setBounds(230, 426, 100, 100);

        btn17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn17.setToolTipText("");
        btn17.setBorderPainted(false);
        btn17.setContentAreaFilled(false);
        btn17.setFocusable(false);
        btn17.setHideActionText(true);
        btn17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn17ActionPerformed(evt);
            }
        });
        getContentPane().add(btn17);
        btn17.setBounds(340, 426, 100, 100);

        btn18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn18.setToolTipText("");
        btn18.setBorderPainted(false);
        btn18.setContentAreaFilled(false);
        btn18.setFocusable(false);
        btn18.setHideActionText(true);
        btn18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn18ActionPerformed(evt);
            }
        });
        getContentPane().add(btn18);
        btn18.setBounds(450, 426, 100, 100);

        btn19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn19.setToolTipText("");
        btn19.setBorderPainted(false);
        btn19.setContentAreaFilled(false);
        btn19.setFocusable(false);
        btn19.setHideActionText(true);
        btn19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn19ActionPerformed(evt);
            }
        });
        getContentPane().add(btn19);
        btn19.setBounds(10, 532, 100, 100);

        btn20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn20.setToolTipText("");
        btn20.setBorderPainted(false);
        btn20.setContentAreaFilled(false);
        btn20.setFocusable(false);
        btn20.setHideActionText(true);
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20ActionPerformed(evt);
            }
        });
        getContentPane().add(btn20);
        btn20.setBounds(120, 532, 100, 100);

        btn21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn21.setToolTipText("");
        btn21.setBorderPainted(false);
        btn21.setContentAreaFilled(false);
        btn21.setFocusable(false);
        btn21.setHideActionText(true);
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21ActionPerformed(evt);
            }
        });
        getContentPane().add(btn21);
        btn21.setBounds(230, 532, 100, 100);

        btn22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn22.setToolTipText("");
        btn22.setBorderPainted(false);
        btn22.setContentAreaFilled(false);
        btn22.setFocusable(false);
        btn22.setHideActionText(true);
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });
        getContentPane().add(btn22);
        btn22.setBounds(340, 532, 100, 100);

        btn23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Card Back.png"))); // NOI18N
        btn23.setToolTipText("");
        btn23.setBorderPainted(false);
        btn23.setContentAreaFilled(false);
        btn23.setFocusable(false);
        btn23.setHideActionText(true);
        btn23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn23ActionPerformed(evt);
            }
        });
        getContentPane().add(btn23);
        btn23.setBounds(450, 532, 100, 100);

        btnRestart.setText("Restart");
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestart);
        btnRestart.setBounds(230, 320, 100, 50);

        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuit);
        btnQuit.setBounds(230, 370, 100, 50);

        tBtnMute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Unmute.png"))); // NOI18N
        tBtnMute.setFocusable(false);
        tBtnMute.setRolloverEnabled(false);
        tBtnMute.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/Mute.png"))); // NOI18N
        tBtnMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tBtnMuteActionPerformed(evt);
            }
        });
        getContentPane().add(tBtnMute);
        tBtnMute.setBounds(545, 930, 20, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MatchingGame/Images/GhostbustersBG.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 565, 950);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        clipMusic.stop();
        clipMusic.flush();
        if (clipSound.isOpen()) {
            clipSound.stop();
            clipSound.flush();
        }
        this.dispose();
    }//GEN-LAST:event_btnQuitActionPerformed

    
    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
        // lists();
        gameStart();
        cardTrack = 0;
    }//GEN-LAST:event_btnRestartActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        matchCheck(0);
    }//GEN-LAST:event_btn0ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        matchCheck(1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        matchCheck(2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        matchCheck(3);
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        matchCheck(4);
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        matchCheck(5);
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        matchCheck(6);
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        matchCheck(7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        matchCheck(8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        matchCheck(9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        matchCheck(10);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        matchCheck(11);
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        matchCheck(12);
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        matchCheck(13);
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        matchCheck(14);
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        matchCheck(15);
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        matchCheck(16);
    }//GEN-LAST:event_btn16ActionPerformed

    private void btn17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn17ActionPerformed
        matchCheck(17);
    }//GEN-LAST:event_btn17ActionPerformed

    private void btn18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn18ActionPerformed
        matchCheck(18);
    }//GEN-LAST:event_btn18ActionPerformed

    private void btn19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn19ActionPerformed
        matchCheck(19);
    }//GEN-LAST:event_btn19ActionPerformed

    private void btn20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20ActionPerformed
        matchCheck(20);
    }//GEN-LAST:event_btn20ActionPerformed

    private void btn21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21ActionPerformed
        matchCheck(21);
    }//GEN-LAST:event_btn21ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        matchCheck(22);
    }//GEN-LAST:event_btn22ActionPerformed

    private void btn23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn23ActionPerformed
        matchCheck(23);
    }//GEN-LAST:event_btn23ActionPerformed

    private void tBtnMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tBtnMuteActionPerformed
        if (!tBtnMute.isSelected()) { //is the game muted
            mute =false;
            playMusic();
        }
        else {
            mute = true;
            clipMusic.stop();
            clipMusic.flush();
            if (clipSound.isOpen()) {
                clipSound.stop();
                clipSound.flush();
            }
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
            java.util.logging.Logger.getLogger(matchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(matchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(matchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(matchGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new matchGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn17;
    private javax.swing.JButton btn18;
    private javax.swing.JButton btn19;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn23;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnRestart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToggleButton tBtnMute;
    // End of variables declaration//GEN-END:variables
}
