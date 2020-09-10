package Participant;

import Cards.Card;

import java.util.LinkedList;
import java.util.Scanner;

public class Player {

    private String name;
    private LinkedList<Card> hand;
    private int timesWon;
    private Card currentCard;
    private int prediction;
    private int points;
    private boolean isAI;

    public Player(String name, boolean isAI){
        this.name = name;
        this.isAI = isAI;

        hand = new LinkedList<>();

        timesWon = 0;
        currentCard = null;
        prediction = -1;
        points = 0;
    }

    public void predict(){
        System.out.println("Wie viele Stiche gewinnst du?");
        prediction = new Scanner(System.in).nextInt();
    }

    public void addPoints(int points){
        this.points += points;
    }

    public void addToHand(Card card){
        hand.add(card);
    }

    public void clearHand(){
        hand.clear();
    }

    public void displayHand(){
        System.out.print("Hand: ");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print("(" + (i+1) + ") " + hand.get(i).getName() + "; ");
        }
        System.out.println();
    }

    public void pickCard(){
        displayHand();
        System.out.println("Welche Karte willst du spielen? (Index der Karte)");
        int card = new Scanner(System.in).nextInt();
        currentCard = hand.get(card-1);
        hand.remove(card-1);
    }

    public void setTimesWon(int timesWon) {
        this.timesWon = timesWon;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }

    public int getPrediction() {
        return prediction;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public int getTimesWon() {
        return timesWon;
    }

    public LinkedList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

}
