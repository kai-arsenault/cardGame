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
	private Pile pot;
	private int rounds = 0;

	public Game() {
		System.out.println("The Game of War");
		System.out.println();
		
		deck = new Deck();
		pot = new Pile();
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
			//System.out.printf("Card %s given to %s%n", currentCard.toString(), players.get(player).getID());
			player++;
			if (player == numPlayers) {
				player = 0;
			}
		}
	} // end Game()

	/**
	 * Each active player plays top card
	 */
	public void play() {
		// Reset pot
		while (!pot.isEmpty()) {
			pot.remove();
		}

		Card topCard = null;
		boolean war = false;
		Player topPlayer = null;
		Player tiedPlayer = null;

		for (int i = 0; i < players.size(); i++) {
			players.get(i).playCard();
			pot.add(players.get(i).getPlayedCard());
			System.out.println("Player " + players.get(i).getID() + ": " + players.get(i).getPlayedCard().toString());

			if (topCard == null) {
				topPlayer = players.get(i);
				topCard = players.get(i).getPlayedCard();
			} else if (players.get(i).getPlayedCard().compareTo(topCard) > 0) {
				topPlayer = players.get(i);
				topCard = players.get(i).getPlayedCard();
				war = false;
			} else if (players.get(i).getPlayedCard().equals(topCard)) {
				tiedPlayer = players.get(i);
				war = true;
			}
		}

		if (war) {
			System.out.println("War between Player " + topPlayer.getID() + " and Player " + tiedPlayer.getID());
			topPlayer = war(topPlayer, tiedPlayer);
		}

		System.out.println("Player " + topPlayer.getID() + " wins the round");
		take(topPlayer);
		rounds++;
	}

	/**
	 * Initiates war sequence
	 *
	 * @param player1 player in war
	 * @param player2 other player in war
	 */
	public Player war(Player player1, Player player2) {
		if(player1.getCardCount() >= 4 && player2.getCardCount() >= 4) {
			for (int i = 0; i < 4; i++) {
				player1.playCard();
				player2.playCard();
				pot.add(player1.getPlayedCard());
				pot.add(player2.getPlayedCard());
			} // end for
			
			System.out.println("Player " + player1.getID() + ": " + player1.getPlayedCard().toString());
			System.out.println("Player " + player2.getID() + ": " + player2.getPlayedCard().toString());
			
			if (player1.getPlayedCard().compareTo(player2.getPlayedCard()) > 0) {
				return player1;
			} else if (player1.getPlayedCard().compareTo(player2.getPlayedCard()) < 0) {
				return player2;
			} else {
				return war(player1, player2);
			} // end if
		} else {
			if(player1.getCardCount()<4) {
				System.out.println("Player " + player1.getID() + " doesn't have enough cards to fight the war.");
				return player2;
			} else {
				System.out.println("Player " + player2.getID() + " doesn't have enough cards to fight the war.");
				return player1;
			} // end if
		} // end if
	} // end war()

	/**
	 * Moves all cards from pot to winner's hand
	 * 
	 * @param winner Winning player
	 */
	public void take(Player winner) {
		// shuffle the pot to avoid a never-ending game
		pot.shuffle();
		while (!pot.isEmpty()) {

			winner.addCard(pot.remove());
		}
	} // end take()

	/**
	 * Eliminates any player with an empty hand
	 */
	public void eliminate() {

		ArrayList<Player> eliminated = new ArrayList<>();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isEmpty()) {
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
			System.out.println("\nPlayer " + players.get(0).getID() + " has won the game in " + rounds + " rounds.");
		}
	} // end isOver()
	
	/**
     	* Prints the players still in the game and the number of cards they have.
     	*/
	public void printGameInfo() {
    	
		System.out.println();
    	for (Player aPlayer : players)
    	System.out.println("Player " + aPlayer.getID() + " has " + aPlayer.getCardCount() + " cards.");
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Game main = new Game();

		while (!donePlaying) {
			main.printGameInfo();
			main.play();
			main.eliminate();
			main.isOver();
		}

	} // end main()

} // end class Game
