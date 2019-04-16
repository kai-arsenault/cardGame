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
 * @author 
 * @version
 *
 */
public class Player
	{
	private Hand hand ;
	private Card playedCard = null;
	private static int nextId = 0;
	private final int id;

	/**
	 * 
	 */
	public Player() {
		this.hand = new Hand();
		id = Player.nextId++;
	}	// end constructor

	public int getID() {
		return id;
	}

	public void addCard(Card newCard) {
		hand.add(newCard);
	}

	public Card removeCard() {
		return hand.remove();
	}

	public boolean isEmpty() {
		return hand.isEmpty();
	}

	public void playCard() {
		playedCard = hand.remove();
	}

	public Card getPlayedCard() {
		return playedCard;
	}

	public Card removePlayedCard() {
	    Card output = playedCard;
		playedCard = null;
		return output;
	}


	/**
	 * @param args
	 */
	public static void main( String[] args )
		{
		// TODO Auto-generated method stub

		}	// end main()

	}	// end class Player
