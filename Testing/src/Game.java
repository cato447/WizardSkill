import Cards.Card;
import Cards.CardDeck;
import Cards.CardType;
import Participant.AI;
import Participant.Player;

import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    private LinkedList<Player> players;
    private LinkedList<Card> pickedCards;
    private CardDeck deck;
    private int leadingType;
    private int round;

    public Game(){
        players = new LinkedList<>();
        pickedCards = new LinkedList<>();
        deck = new CardDeck();

        leadingType = -1;

        round = 1;

        generatePlayers();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.control();
    }

    private void control(){
        while(round <= 60/players.size()){
            dealCards();
            printLeadingType();
            players.get(0).displayHand();
            makePredictions();
            System.out.println();
            while (players.get(0).getHand().size() != 0) {
                pickCards();
                printPickedCards();
                winningProcess();
                pickedCards.clear();
            }
            displayPoints();
            newRound();
        }
    }

    private void generatePlayers(){
        System.out.println("WELCOME TO THE GAME OF WIZARD!");
        System.out.println("What is you name?");

        String name = new Scanner(System.in).nextLine();
        System.out.println("Against how many players do you want to play? (2-5)");

        int playerAmount = new Scanner(System.in).nextInt();

        players.add(new Player(name, false));
        for (int i = 0; i < playerAmount; i++){
            players.add(new AI("Opponent " +(i+1), true));
        }

        System.out.println("----- ROUND 1 -----");
    }

    private void dealCards(){
        deck.shuffle();
        LinkedList<Card> availableCards = (LinkedList<Card>) deck.getCards().clone();
        for (Player p: players) {
            for (int i = 0; i < round; i++){
                p.addToHand(availableCards.get(0));
                availableCards.remove(0);
            }
        }
        try{
            leadingType = availableCards.get(0).getCardType().getIndex();
        } catch (Exception e){
            System.out.println("Letzte Runde: Keine Trumpffarbe mehr");
            leadingType = -1;
        }
    }

    private void printLeadingType(){
        System.out.println("Trumpffarbe: " + CardType.values()[leadingType]);
    }

    private void makePredictions(){
        for (Player p: players) {
            p.predict();
            System.out.print(p.getPrediction()+ "; ");
        }
    }

    private void pickCards(){
        for (int i = 0; i < players.size(); i++) {
            players.get(i).pickCard();
            pickedCards.add(players.get(i).getCurrentCard());
        }
    }

    private void printPickedCards(){
        System.out.print("Gewählte Karten: ");
        for (Card c: pickedCards) {
            System.out.print(c.getName() +"; ");
        }
    }

    private void winningProcess(){
        int highestCardIndex = -1;
        int highestCardValue = 0;
        for (int i = 0; i < pickedCards.size(); i++) {
            int cardValue = pickedCards.get(i).getCardValue().getIndex();
            int cardType = pickedCards.get(i).getCardType().getIndex();

            if (cardValue == 14){
                highestCardIndex = i;
                break;
            } else if (cardType == leadingType){
                if ((cardValue+13) > highestCardValue){
                    highestCardIndex = i;
                    highestCardValue = cardValue+13;
                }
            } else if(cardValue > highestCardValue){
                highestCardIndex = i;
                highestCardValue = cardValue;
            } else if (cardValue == 0){
                highestCardIndex = i;
            }
        }
        players.get(highestCardIndex).setTimesWon(players.get(highestCardIndex).getTimesWon()+1);
        System.out.println();
        System.out.println("Winner: " + players.get(highestCardIndex).getName());
    }

    private void displayPoints(){
        givePoints();
        for (Player p: players) {
            System.out.print(p.getName()+": "+ p.getPoints()+"; ");
            p.setTimesWon(0);
        }
        System.out.println();
    }

    private void givePoints(){
        for (Player p: players) {
            int points = 0;
            points += (p.getTimesWon() * 10);
            points -= (Math.abs(p.getPrediction()-p.getTimesWon())*10);
            if (p.getTimesWon() == p.getPrediction()){
                points += 20;
            }
            p.addPoints(points);
        }
    }
    private void newRound(){
        round++;
        System.out.println();
        System.out.println("----- ROUND " + round + " -----");
    }
}
