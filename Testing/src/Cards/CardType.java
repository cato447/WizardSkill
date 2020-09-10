package Cards;

public enum CardType {

    rot(0),
    gelb(1),
    blau(2),
    grün(3);

    int index;

    private CardType(int i){
        this.index = i;
    }

    public int getIndex() {
        return index;
    }

}
