import Cards.Card;
import Cards.CardDeck;
import Cards.CardType;
import Participant.AI;
import Participant.Player;
import sun.awt.image.ImageWatched;

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
                System.out.println();
            }
            newRound();
        }
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

    private void newRound(){
        round++;
        System.out.println("----- ROUND " + round + " -----");
        for (Player p: players) {
            p.clearHand();
        }
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

    private void printLeadingType(){
        System.out.println("Trumpffarbe: " + CardType.values()[leadingType]);
    }

    private void printPickedCards(){
        System.out.print("Gewählte Karten: ");
        for (Card c: pickedCards) {
            System.out.print(c.getName() +"; ");
        }
        pickedCards.clear();
    }

    private void winningProcess(){
        int highestCardIndex = -1;
        for (int i = 0; i < pickedCards.size(); i++) {
            if (pickedCards.get(i).getCardValue().getIndex() == 14){
                players.get(i).setTimesWon(players.get(i).getTimesWon()+1);
            }
            if (pickedCards.get(i).getCardType().getIndex() == leadingType){
                
            }
        }
    }



}
