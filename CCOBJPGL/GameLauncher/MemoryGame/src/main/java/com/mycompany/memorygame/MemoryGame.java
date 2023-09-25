/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.memorygame;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;


public class MemoryGame extends javax.swing.JFrame {
    private ImageIcon[] cardImages;
    private JButton firstClickedButton = null;
    private JButton secondClickedButton = null;
    private int clickCount = 0;
    private boolean allowButtonClicks = true;
    private int matchedPairsCount = 0;    
    private int moveCount = 0;
    private long startTime;
    private Timer timer;
    

    public MemoryGame() {
        initComponents();
        pnlGrid.setLayout(new java.awt.GridLayout(4, 4));
        lblMove.setText("Moves: 0");
        lblTimer.setText("Time: 00:00");        
        
        loadCardImages();
        shuffleCardImages();
    }
    
    private void startTimer() {
        startTime = System.currentTimeMillis();
        timer = new Timer(1000, e -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            long seconds = elapsedTime / 1000;
            long minutes = seconds / 60;
            seconds %= 60;
            lblTimer.setText(String.format("Time: %02d:%02d", minutes, seconds));
        });
        timer.setInitialDelay(0);
        timer.start();
    }
    
    private void startNewGame() {
        // Reset the matched pairs count, move count, and timer label
        matchedPairsCount = 0;
        moveCount = 0;
        lblMove.setText("Moves: 0");
        lblTimer.setText("Time: 00:00");

        // Reset the card images and shuffle them
        loadCardImages();
        shuffleCardImages();
        setAllCardsHidden();
    }
    
    private void setAllCardsHidden() {
        for (int i = 1; i <= 16; i++) {
            JButton button = getButtonByIndex(i);
            button.setIcon(null);
            button.setEnabled(true);
        }
        moveCount = 0;
        btnStart.setEnabled(true);
    }
    
    
    
    
    private void loadCardImages() {
        cardImages = new ImageIcon[16];
        for (int i = 0; i < 8; i++) {
            try {
                cardImages[i] = new ImageIcon(ImageIO.read(new FileInputStream("/home/felixdev/NetBeansProjects/MemoryGame/icons/card" + (i + 1) + ".png")));
                cardImages[i + 8] = cardImages[i]; // Duplicate the image for the matching pair
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading image: icons/card" + (i + 1) + ".png");
            }
        }
    }
    
    private void shuffleCardImages() {
        List<ImageIcon> imageList = Arrays.stream(cardImages).collect(Collectors.toList());
        Collections.shuffle(imageList);
        cardImages = imageList.toArray(new ImageIcon[0]);
    }    
    

    private void revealCard(int buttonIndex) {
        JButton button = getButtonByIndex(buttonIndex);
        button.setIcon(cardImages[buttonIndex - 1]);
    }   
    
    private JButton getButtonByIndex(int index) {
        switch (index) {
            case 1:
                return btn1;
            case 2:
                return btn2;
            case 3:
                return btn3;
            case 4:
                return btn4;
            case 5:
                return btn5;
            case 6:
                return btn6;
            case 7:
                return btn7;
            case 8:
                return btn8;
            case 9:
                return btn9;
            case 10:
                return btn10;
            case 11:
                return btn11;
            case 12:
                return btn12;
            case 13:
                return btn13;
            case 14:
                return btn14;
            case 15:
                return btn15;
            case 16:
                return btn16;
            default:
                throw new IllegalArgumentException("Invalid button index: " + index);
        }
    }
    
    private void handleButtonClick(int buttonIndex) {
        if (!allowButtonClicks) {
            return; // If button clicks are not allowed, do nothing
        }

        JButton clickedButton = getButtonByIndex(buttonIndex);
        if (clickedButton.getIcon() != null || firstClickedButton == clickedButton) {
            return; // If the button has an icon or is already clicked, do nothing
        }

        revealCard(buttonIndex);
        clickCount++;

        if (clickCount == 1) {
            firstClickedButton = clickedButton;
        } else if (clickCount == 2) {
            secondClickedButton = clickedButton;
            checkForMatch();
            moveCount++;
            lblMove.setText(String.format("Moves: %d", moveCount));
        }
    }

    
    private void setAllButtonsEnabled(boolean enabled) {
        for (int i = 1; i <= 16; i++) {
            getButtonByIndex(i).setEnabled(enabled);
        }
    }    
    
    private void checkForMatch() {
        if (firstClickedButton.getIcon().equals(secondClickedButton.getIcon())) {
            // The images match, so reset the click counter and the clicked buttons
            firstClickedButton = null;
            secondClickedButton = null;
            clickCount = 0;
            matchedPairsCount++;

            if (matchedPairsCount == 8) {
                showYouWonMessage();
            }
        } else {
            // The images don't match, so hide them after a short delay
            allowButtonClicks = false;
            Timer timer = new Timer(1000, e -> {
                hideCard(firstClickedButton);
                hideCard(secondClickedButton);
                firstClickedButton = null;
                secondClickedButton = null;
                clickCount = 0;
                allowButtonClicks = true;
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private void hideCard(JButton button) {
        button.setIcon(null);
    }
    
    private void showYouWonMessage() {
        timer.stop();
        JOptionPane.showMessageDialog(this, String.format("Congratulations! You have matched all pairs in %d moves and %s!", moveCount, lblTimer.getText()), "You Won", JOptionPane.INFORMATION_MESSAGE);
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGrid = new javax.swing.JPanel();
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
        lblMove = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlGrid.setBackground(new java.awt.Color(51, 51, 51));
        pnlGrid.setVerifyInputWhenFocusTarget(false);
        pnlGrid.setLayout(new java.awt.GridLayout(1, 0));

        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn1);

        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn2);

        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn3);

        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn4);

        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn5);

        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn6);

        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn7);

        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn8);

        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn9);

        btn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn10ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn10);

        btn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn11ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn11);

        btn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn12ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn12);

        btn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn13ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn13);

        btn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn14ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn14);

        btn15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn15ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn15);

        btn16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn16ActionPerformed(evt);
            }
        });
        pnlGrid.add(btn16);

        lblMove.setText("Moves: 0");

        lblTimer.setText("Time: 00:00");

        btnStart.setText("START");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMove)
                    .addComponent(lblTimer))
                .addContainerGap(399, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGap(207, 207, 207))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblMove)
                .addGap(12, 12, 12)
                .addComponent(lblTimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnStart)
                .addGap(18, 18, 18)
                .addComponent(pnlGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        pnlGrid.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        handleButtonClick(1);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        handleButtonClick(2);
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        handleButtonClick(3);

    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        handleButtonClick(4);

    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        handleButtonClick(5);

    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        handleButtonClick(6);

    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        handleButtonClick(7);
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        handleButtonClick(8);
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        handleButtonClick(9);
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn10ActionPerformed
        handleButtonClick(10);
    }//GEN-LAST:event_btn10ActionPerformed

    private void btn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn11ActionPerformed
        handleButtonClick(11);
    }//GEN-LAST:event_btn11ActionPerformed

    private void btn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn12ActionPerformed
        handleButtonClick(12);
    }//GEN-LAST:event_btn12ActionPerformed

    private void btn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn13ActionPerformed
        handleButtonClick(13);
    }//GEN-LAST:event_btn13ActionPerformed

    private void btn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn14ActionPerformed
        handleButtonClick(14);
    }//GEN-LAST:event_btn14ActionPerformed

    private void btn15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn15ActionPerformed
        handleButtonClick(15);
    }//GEN-LAST:event_btn15ActionPerformed

    private void btn16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn16ActionPerformed
        handleButtonClick(16);
    }//GEN-LAST:event_btn16ActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        startNewGame();
        startTimer();
        btnStart.setEnabled(false);
    }//GEN-LAST:event_btnStartActionPerformed

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
            java.util.logging.Logger.getLogger(MemoryGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemoryGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemoryGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemoryGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemoryGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn11;
    private javax.swing.JButton btn12;
    private javax.swing.JButton btn13;
    private javax.swing.JButton btn14;
    private javax.swing.JButton btn15;
    private javax.swing.JButton btn16;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel lblMove;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JPanel pnlGrid;
    // End of variables declaration//GEN-END:variables
}
