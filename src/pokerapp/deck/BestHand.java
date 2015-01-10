package pokerapp.deck;

public class BestHand {
    
    /*private static final String QUINTEFLUSH = "";
    private static final String CARRE = "";
    private static final String FULL = "";
    private static final String COULEUR = "";
    private static final String SUIT = "";
    private static final String BRELAN = "";
    private static final String DOUBLEPAIR = "";
    private static final String PAIR = "";
    private static final String HIGHERCARD = "";*/

    private int size;
    private Card[] hand = new Card[5];

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }
    
}
