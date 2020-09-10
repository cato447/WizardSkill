package Cards;

public class Card {

    private CardType cardType;
    private CardValue cardValue;

    public Card(int cardType, int cardValue){
        this.cardType = CardType.values()[cardType];
        this.cardValue = CardValue.values()[cardValue];
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getName(){
        int index = this.getCardValue().getIndex();
        return (0<index && index<14)  ? this.getCardType().name() + "e " + this.getCardValue().name()
        : this.getCardType().name() + "er " + this.getCardValue().name();
    }
}
