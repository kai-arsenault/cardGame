/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: List application - card game
 * Spring, 2019
 * 
 * Usage restrictions:
 * 
 * You may use this code for exploration, experimentation, and furthering your
 * learning for this course. You may not use this code for any other
 * assignments, in my course or elsewhere, without explicit permission, in
 * advance, from myself (and the instructor of any other course).
 * 
 * Further, you may not post or otherwise share this code with anyone other than
 * current students in my sections of this course. Violation of these usage
 * restrictions will be considered a violation of the Wentworth Institute of
 * Technology Academic Honesty Policy.
 */

package edu.wit.dcsn.comp2000.listapp;


/**
 * @author Ernest Shedden
 * @version 1.0
 *
 */
public class Player {
	
	private Hand hand;
	private Card playedCard = null;
	private static int nextId = 1;
	private final int id;

	/**
	 * Default constructor
	 */
	public Player() {
		this.hand = new Hand();
		id = Player.nextId++;
	}	// end constructor
	
	/**
	 * Retrieve player's ID
	 * @return the id of the player
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Add a card to bottom of player's hand
	 * @param newCard card to be added
	 */
	public void addCard(Card newCard) {
		hand.add(newCard);
	}
	
	/**
	 * Remove a card from top of player's hand
	 * @return card that was removed
	 */
	public Card removeCard() {
		return hand.remove();
	}

	/**
	 * Checks if player's hand is empty
	 * @return true if hand is empty
	 */
	public boolean isEmpty() {
		return hand.isEmpty();
	}
	
	/**
	 * Removes card from player's hand, sets it as the players playedCard
	 */
	public void playCard() {
		playedCard = hand.remove();
	}

	/**
	 * Gets the player's played card
	 * @return card that was played
	 */
	public Card getPlayedCard() {
		return playedCard;
	}

	/**
	 * Remove player's played card
	 * @return card that was removed
	 */
	public Card removePlayedCard() {
	    Card output = playedCard;
		playedCard = null;
		return output;
	}
	
	/**
	 * Counts the number of cards in a player's hand
	 * @return number of cards
	 */
	public int getCardCount() {
		return hand.getSize();
	}

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		
	}	// end main()
}	// end class Player
