package pokerapp.deck;

import java.util.Comparator;
import java.util.List;

public class CardComparator implements Comparator<Card>{

        /**
         * compare to cardValue
         * @param c1
         * @param c2
         * @return int
         */
        public int compare(Card c1, Card c2) {

            int intReturn;

            String firstValueS = c1.getValue();
            String secondValueS = c2.getValue();

            int firstValue = valueCard(firstValueS);
            int secondValue = valueCard(secondValueS);


            if(firstValue > secondValue){
                intReturn = 1;
            }else if (firstValue < secondValue) {
                intReturn = -1;
            }else{
                intReturn = 0;
            }

            return intReturn;
        }

        /*
         * take back the integer value
         * of the card
         */
        public int valueCard(String valueCard){

            int value;

            if(valueCard.contains("A")){
                value = 14;
            }else if(valueCard.contains("R")){
                value = 13;
            }else if(valueCard.contains("D")){
                value = 12;
            }else if(valueCard.contains("V")){
                value = 11;
            }else{
                value = Integer.valueOf(valueCard);
            }

            return value;
        }

        /**
         * override the collections.frequency
         * for value
         * @param c
         * @param value
         * @return
         */
        public static int frequencyValue(List<Card> c, String value) {
            int result = 0;
            if (value == null) {
                for (Card e : c)
                    if (e.getValue() == null)
                        result++;
            } else {
                for (Card e : c)
                    if (value.equals(e.getValue()))
                        result++;
            }
            return result;
        }

        /**
         * override the Collections frequency methode
         * for color
         * @param c
         * @param color
         * @return
         */
        public static int frequencyColor(List<Card> c, String color) {
            int result = 0;
            if (color == null) {
                for (Card e : c)
                    if (e.getColor() == null)
                        result++;
            } else {
                for (Card e : c)
                    if (color.equals(e.getColor()))
                        result++;
            }
            return result;
        }

        /**
         * get the bestHand
         * according the value
         * @param c
         * @param value
         * @param bestHand
         * @return BestHand
         */
        public static BestHand getListValue(List<Card> c, String value, BestHand bestHand) {
           
            Card[] cards = bestHand.getHand();
            int size = 0;
            
            if (value == null) {
                for (Card e : c){
                    if (e.getValue() == null){
                        cards[size] = e;
                        size++;
                    }
                }
            } else {
                for (Card e : c){
                    if (value.equals(e.getValue())){
                        cards[size] = e;
                        size++;
                    }
                }
            }

            System.out.println("bestHand Size " + bestHand.getSize());
            if(size >= 2 & bestHand.getSize() == 0){
                bestHand.setHand(cards);
            }

            return bestHand;
        }

        /**
         * get the bestHand
         * according the color
         * @param c
         * @param color
         * @param bestHand
         * @return BestHand
         */
        public static BestHand getListColor(List<Card> c, String color, BestHand bestHand) {

            Card[] cards = bestHand.getHand();
            int size = 0;

            if (color == null) {
                for (Card e : c){
                    if (e.getColor() == null){
                        cards[size] = e;
                        size++;
                    }
                }
            } else {
                for (Card e : c){
                    if (color.equals(e.getColor())){
                        cards[size] = e;
                        size++;
                    }
                }
            }

            bestHand.setHand(cards);
            bestHand.setSize(size);
            
            return bestHand;
        }
}
