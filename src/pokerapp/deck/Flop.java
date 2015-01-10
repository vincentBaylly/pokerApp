package pokerapp.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pokerapp.IFlop;
import java.net.URL;
import javax.swing.ImageIcon;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flop implements IFlop{
    
    private static final Logger logger = Logger.getLogger(Flop.class.getName());
    private List<Card> collectionFlop = new ArrayList<Card>();
    //private BestHand bestHand = new BestHand();
    private CardComparator cardComparator = new CardComparator();
    private List<String> listValue = new ArrayList<String>();
    private List<String> listColor = new ArrayList<String>();

    /**
     * getting the Deck at the begin
     * @return String
     */
    public List<Card> getFlop(){
        
        String cardImgPath = "";
        
        Random randomGenerator = new Random();        
        
        //possible value
        listValue.add(TWO);
        listValue.add(THREE);
        listValue.add(FOUR);
        listValue.add(FIVE);
        listValue.add(SIX);
        listValue.add(SEVEN);
        listValue.add(EIGHT);
        listValue.add(NINE);
        listValue.add(TEN);
        listValue.add(JACK);
        listValue.add(QUEEN);
        listValue.add(KING);
        listValue.add(ACE);

        //possible color
        listColor.add(CLUB);
        listColor.add(DIAMOND);
        listColor.add(HEART);
        listColor.add(SPADE);
        
        //get the deck
        logger.log(Level.INFO, "le Flop");
        for(int i=0; i < 7; i++){

            Card card = new Card();
            card.setColor(listColor.get(randomGenerator.nextInt(4)));

            card.setValue(listValue.get(randomGenerator.nextInt(12)));
            
            if("10".equalsIgnoreCase(card.getValue())){
                cardImgPath = card.getColor().substring(0,2).toLowerCase() + card.getValue().substring(0,2).toLowerCase();
            }else{
                cardImgPath = card.getColor().substring(0,2).toLowerCase() + card.getValue().substring(0,1).toLowerCase();
            }

            ImageIcon imageIcon = createImageIcon("../resources/cards_png/" + cardImgPath + ".png",
                                         card.toString());
            card.setImageIcon(imageIcon);
            logger.log(Level.INFO, cardImgPath);
            collectionFlop.add(card);
            logger.log(Level.INFO, collectionFlop.get(i).toString());

        }
        
        return collectionFlop;
    }

    /**
     * getting the best hand in the deck
     * @return String
     */
    public String getBestHand(){
        
        String systemout = "<html>";
        boolean betterHand = false;
        boolean foak = false;
        boolean color = false;
        boolean full = false;
        boolean suite = false;
        Card firstCard = null;
        int nbCardSuiv;
        Card lastCard = null;

        //sorting
        Collections.sort(collectionFlop, cardComparator);

        //number of occurrence of a card
        for(String possibleValue : listValue){

        	//two times
            if(CardComparator.frequencyValue(collectionFlop, possibleValue) == 2){
                logger.log(Level.INFO, "One Pair {0} frequence {1}", new Object[]{possibleValue, CardComparator.frequencyValue(collectionFlop, possibleValue)});
                systemout += "Paire de " + possibleValue + "<br>";

                betterHand = true;
            //three times
            }else if(CardComparator.frequencyValue(collectionFlop, possibleValue) == 3){
                logger.log(Level.INFO, "Three of a kind {0} frequence {1}", new Object[]{possibleValue, CardComparator.frequencyValue(collectionFlop, possibleValue)});
                systemout += "Brelan de " + possibleValue + "*<br>";
                betterHand = true;

            //four times
            }else if(CardComparator.frequencyValue(collectionFlop, possibleValue) == 4){
                logger.log(Level.INFO, "Four of a kind {0} frequence {1}", new Object[]{possibleValue, CardComparator.frequencyValue(collectionFlop, possibleValue)});
                systemout = "<html>Carre de " + possibleValue + "<br>";
                betterHand = true;
                foak = true;
                break;
            }
        }

        //getting the full
        if(systemout.contains("Paire") && systemout.contains("Brelan")){
        	systemout = "<html>Full aux " + systemout.substring(systemout.lastIndexOf("Brelan") + 10,  systemout.lastIndexOf("*")) + " par les " + systemout.substring(systemout.lastIndexOf("Paire") + 8);
        	systemout = systemout.replace(systemout.substring(systemout.indexOf("<br>") + 4), "");
        	full = true;
        }


        //suppress if it contains 3 pair
        if(stringOccur(systemout, "Paire") > 2){
        	systemout = "<html>" + systemout.substring(systemout.indexOf("<br>") + 4);
        }

        if(systemout.contains("Brelan")){
	        if(stringOccur(systemout, "Brelan") > 1){
	        	systemout = "<html>" + systemout.substring(systemout.indexOf("<br>") + 4);
	        }
	        systemout = systemout.replace("*", "");
        }

        /*if(systemout.contains("Brelan") > 2 && ){
	        if(stringOccur(systemout, "Brelan") > 1){
	        	systemout = "<html>" + systemout.substring(systemout.indexOf("<br>") + 4);
	        }
	        systemout = systemout.replace("*", "");
        }*/

        //straight
        for(int i=0; i <= 2; i++){

            firstCard = collectionFlop.get(i);
            int flopValue = cardComparator.valueCard(collectionFlop.get(i).getValue());
            int nextCardValue = cardComparator.valueCard(collectionFlop.get(i+1).getValue());
            nbCardSuiv = 0;
            int j = i;
            while(flopValue+1==nextCardValue){

                flopValue = cardComparator.valueCard(collectionFlop.get(j).getValue());
                if(j < 6){
                    nextCardValue = cardComparator.valueCard(collectionFlop.get(j+1).getValue());
                }

                j++;
                nbCardSuiv++;


            }

            if(nbCardSuiv >= 5){
                suite = true;
                betterHand = true;
                lastCard = collectionFlop.get(j-1);
            }
        }

        if(suite || !foak || ! full){
            //flush
            for(String possibleColor : listColor){

                if(CardComparator.frequencyColor(collectionFlop, possibleColor) == 5){
                    logger.log(Level.INFO, "Flush {0} frequence {1}", new Object[]{possibleColor, CardComparator.frequencyColor(collectionFlop, possibleColor)});
                    systemout = "<html>Couleur de " + possibleColor + "<br>";
                    color = true;
                    betterHand = true;
                    break;
                }
            }
        }

        //show the result
        if(suite&&color){
            logger.log(Level.INFO, "Straight Flush last card {0}", lastCard);
            systemout = "<html>Quinteflush premier carte " + firstCard + " derniere Carte " + lastCard + "<br>";
        }
        else if(suite && !foak && !full){
            logger.log(Level.INFO, "Straight last card {0}", lastCard);
            systemout = "<html>Suite premier carte " + firstCard + " derniere Carte " + lastCard + "<br>";
        }

        if(!betterHand){
            logger.log(Level.INFO, "Higher card {0}", Collections.max(collectionFlop, cardComparator));
            systemout += "Carte la plus haute " + Collections.max(collectionFlop, cardComparator);
        }

        systemout += "</html>";

        return systemout;
    }

    /** 
     * Returns an ImageIcon, or null if the path was invalid.
     * @param description
     * @param path
     * @return ImageIcon
     */
    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        URL imgURL = Flop.class.getResource(path);
        logger.log(Level.INFO, "card path {0}", imgURL);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            logger.log(Level.SEVERE,"Couldn't find file: {0}", path);
            return null;
        }
    }

    /**
     * getting the occurrences of a String Sequence
     * @param text
     * @param regex
     * @return int
     */
    public static int stringOccur(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        int occur = 0;
        while(matcher.find()) {
            occur ++;
        }
        return occur;
    }
    
}
