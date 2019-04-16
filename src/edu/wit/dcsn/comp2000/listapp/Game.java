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
            System.out.printf("Card %s given to %s%n", currentCard.toString(), players.get(player).getID());
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
    
    /**
     * Each active player plays top card
     */
    public void play() {

    }

    /**
     * Initiates war sequence
     *
     * @param player1 player in war
     * @param player2 other player in war
     */
    public void war(Player player1, Player player2) {

    }

    public void take() {
    	// TODO Auto-generated method stub
    } // end take()
    
    /**
     * Eliminates any player with an empty hand
     */
    public void eliminate() {
    	
    	for (Player aPlayer : players) {
    		System.out.println(aPlayer.getID());
    	}

    	ArrayList<Player> eliminated = new ArrayList<>();
    	for (int i = 0; i<players.size(); i++) {
    		if (players.get(i).isEmpty()){
    			System.out.println("Player " + players.get(i).getID() + " is eliminated from the game.");
    			eliminated.add(players.get(i));

    		}
    	}
    	for (Player player : eliminated) {
    	    players.remove(player);
        }
    } // end eliminate()
    
    /**
     * Ends the game if there's one player left
     */
    public void isOver() {
    	
    	if (players.size() == 1) {
    		donePlaying = true;
    		System.out.println("Player " + players.get(0).getID() + " has won the game.");
    	}
    } // end isOver()

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Game main = new Game();
        
        while (!donePlaying) {
        
            main.draw();
        	main.play();

            main.take();
            main.eliminate();
            main.isOver();
        }
        
    }	// end main()

} // end class Game
