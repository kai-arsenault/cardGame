/*
 * Michael Rivnak, Kai Arsenault, Ernest Shedden
 * Comp 2000 - Data Structures
 * Lab: List application - card game
 * Spring, 2019
 *
 */

package edu.wit.dcsn.comp2000.listapp;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private static boolean donePlaying = false;
    private int numPlayers;


    public Game() {
        deck = new Deck();
        players = new ArrayList<>();

        Random rand = new Random();
        numPlayers = rand.nextInt(3) + 2;

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }

        deck.shuffle();

        int player = 0;
        while (!deck.isEmpty()) {
            Card currentCard = deck.deal();
            players.get(player).addCard(currentCard);
            System.out.printf("Card %s given to %s%n", currentCard.toString(), players.get(player).toString());
            player++;
            if (player == numPlayers) {
                player = 0;
            }
        }
    } // end Game()

    public void draw() {
        for (int i = 0; i < numPlayers; i++) {
            players.get(i).playCard();
        }
    }
    
    public void take() {
    	// TODO Auto-generated method stub
    } // end take()
    
    public void eliminate() {
    	
    	for (int i = 0; i<players.size(); i++) {
    		if (players.get(i).isEmpty()){
    			players.remove(i);
    		}
    	}
    } // end eliminate()
    
    public void isOver() {
    	
    	if (players.size() == 1) 
    		donePlaying = true;
    } // end isOver()

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Game main = new Game();
        
        while (!donePlaying) {
        
            main.draw();
            main.take();
            main.eliminate();
            main.isOver();
        }
        
    }	// end main()

} // end class Game
