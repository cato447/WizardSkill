package Participant;

import java.util.Random;

public class AI extends Player{

    public AI(String name, boolean isAI) {
        super(name, isAI);
    }

    @Override
    public void predict() {
        int index = new Random().nextInt(this.getHand().size());
        this.setPrediction(index);
    }

    @Override
    public void pickCard() {
        int index = new Random().nextInt(this.getHand().size());
        this.setCurrentCard(this.getHand().get(index));
        this.getHand().remove(index);
    }
}
