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


    public Game() {

    } // end Game()

    public void initialize() {
        deck = new Deck();
        players = new ArrayList<>();

        Random rand = new Random();
        int numPlayers = rand.nextInt(3) + 2;

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player());
        }

        deck.shuffle();
    } // end initialize()
    
        public void take() {
    	// TODO Auto-generated method stub
    } // end take()
    
    public void eliminate() {
    	// TODO Auto-generated method stub
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
        main.initialize();
        
        while (!donePlaying) {
        
        
            main.take();
            main.eliminate();
            main.isOver();
        }
        
    }	// end main()

} // end class Game
