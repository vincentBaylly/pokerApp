package pokerapp.window;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import pokerapp.deck.Flop;
import pokerapp.deck.Card;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Frame extends JFrame{
     
    private JMenuItem exitMenuItem;
    private JMenu fileMenu;
    private JButton playButton;
    private JLabel flopOut;
    private JLabel handNameOut;
    private JMenuBar menuBar;
    private JButton bestHandButton;
    private Flop flop;
    
    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        playButton = new JButton();
        bestHandButton = new JButton();
        flopOut = new JLabel();
        handNameOut = new JLabel();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        exitMenuItem = new JMenuItem();
  
        Container con = getContentPane();
        con.setBackground(new java.awt.Color(54, 167, 54));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        playButton.setText("Jouer");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        
        bestHandButton.setText("Meilleur Main");
        bestHandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                bestHandButtonActionPerformed(evt);
            }
        });

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(playButton)
                        .addGap(18, 18, 18)
                        .addComponent(bestHandButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(handNameOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
                            .addComponent(flopOut, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(flopOut, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(handNameOut, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton)
                    .addComponent(bestHandButton))
                .addGap(109, 109, 109))
        );
        
        pack();
    }                       
    
    /**
     * exit menu catch event
     * @param evt 
     */
    private void exitMenuItemActionPerformed(ActionEvent evt) {                                             
        System.exit(0);
    }                                            
    
    /**
     * run the Flop
     * @param evt 
     */
    private void playButtonActionPerformed(ActionEvent evt) {
        flop = new Flop();
        handNameOut.setText("");
        flopOut.setText("le Flop");
        List<Card> cards = flop.getFlop();
        ImageIcon imageIcon = new ImageIcon(append(cards));
        flopOut.setIcon(imageIcon);
        flopOut.setHorizontalAlignment(SwingConstants.RIGHT);
    }
    
    /**
     * run the best Hand 
     * @param evt 
     */
    private void bestHandButtonActionPerformed(ActionEvent evt) {                                         
        
        if(flop != null){

            handNameOut.setText("");
            handNameOut.setText(flop.getBestHand());
            handNameOut.setFont(new Font("Serif", Font.BOLD, 14));
            handNameOut.setHorizontalAlignment(SwingConstants.CENTER);
        }else{
            handNameOut.setText("Cliquez sur Jouer pour commencer");
        }

    }

    /**
     * add flop images to make one
     * @param cards
     * @return
     */
    public static Image append(List<Card> cards) {

        BufferedImage buf = null;
        
        if(cards.get(0) != null && cards.get(0).getImageIcon() != null){
            int hMax = cards.get(0).getImageIcon().getImage().getHeight(null);
            int wMax = 800;
            int w1 = 0;
            buf = new BufferedImage(wMax, hMax, BufferedImage.TYPE_INT_ARGB);
            for(Card card : cards){
                if(card != null && card.getImageIcon() != null) {

                    Graphics2D g2 = buf.createGraphics();
                    g2.drawImage(card.getImageIcon().getImage(), w1, 0, null);
                    w1 += card.getImageIcon().getImage().getHeight(null);

                }
            }
            
        }

        return buf;
    }

}