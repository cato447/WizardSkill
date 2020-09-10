package Cards;

import java.util.Collections;
import java.util.LinkedList;

public class CardDeck {

    private LinkedList<Card> cards;

    public CardDeck(){
        cards = new LinkedList<>();
        generateCards();
    }

    private void generateCards(){
        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 15; j++){
                cards.add(new Card(i, j));
            }
        }
    }

    public void printDeck(){
        for (Card c: cards) {
            System.out.print(c.getName() + "; ");
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public LinkedList<Card> getCards() {
        return cards;
    }
}
