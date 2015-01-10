package pokerapp.deck;

import javax.swing.ImageIcon;

public class Card {

    private String color;

    private String value;

    private ImageIcon imageIcon;

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String toString(){
        String card = "color " + color;
        card += " value " + value;
        return card;
    }

}
