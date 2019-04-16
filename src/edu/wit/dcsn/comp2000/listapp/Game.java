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
import java.util.TreeMap;

public class Game {
    private Deck deck;
    private Pile inPlay;
    private ArrayList<Player> players;
    private static boolean donePlaying = false;
    private int numPlayers;


    public Game() {
        deck = new Deck();
        inPlay = new Pile();
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
            player++;
            if (player == numPlayers) {
                player = 0;
            }
        }
    } // end Game()

    public void draw() {
        draw(players);
    }
    public void draw(ArrayList<Player> playerSublist) {
        for (int i = 0; i < playerSublist.size(); i++) {
            playerSublist.get(i).playCard();
        }
    }

    public void play() {
        play(players);
    }

    /**
     * Each active player plays top card
     */
    public void play(ArrayList<Player> playerSublist) {
        ArrayList<Player> rankedPlayers = new ArrayList<>(playerSublist);
        int n = rankedPlayers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.println("i: " + i);
                System.out.println("j: " + j);

                Card cardA = rankedPlayers.get(j).getPlayedCard();
                Card cardB = rankedPlayers.get(j+1).getPlayedCard();
                if (cardA.compareTo(cardB) > 0) {
                    Player temp = rankedPlayers.get(j);
                    rankedPlayers.set(j, rankedPlayers.get(j+1));
                    rankedPlayers.set(j+1, temp);
                }
            }
        }

        ArrayList<Player> tied = new ArrayList<>();
        tied.add(rankedPlayers.get(0));
        for (int i = 1; i < numPlayers - 1; i++) {
            if (rankedPlayers.get(i).getPlayedCard().compareTo(rankedPlayers.get(i-1).getPlayedCard()) == 0) {
                tied.add(rankedPlayers.get(i));
            } else { break; }
        }

        for (Player player : rankedPlayers) {
            inPlay.add(player.removePlayedCard());
        }

        System.out.println(tied.toString());
        while (tied.size() > 1) {
            draw(tied);
            play(tied);
        }

        Player winner = tied.get(0);
        giveToPlayer(inPlay, winner);
    }


    /**
     * Initiates war sequence
     *
     * @param player1 player in war
     * @param player2 other player in war
     */
    public Player war(Player player1, Player player2) {
        if (player1.getPlayedCard().compareTo(player2.getPlayedCard()) > 0) {
            return player1;
        } else { return player2; }
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
    	} else if (players.size() == 0) {
    	    donePlaying = true;
    	    System.out.println("The game ends in a draw.");
        }
    } // end isOver()

    public void giveToPlayer(Pile pile, Player player) {
        while (!pile.isEmpty()) {
            player.addCard(pile.remove());
        }
    }

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
