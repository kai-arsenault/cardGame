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

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List ;

/**
 * @author Michael Rivnak
 * @version 0.1
 *
 */
public class Pile
	{
	private List<Card> cards ;
	// instantiate this using either the JCL's ArrayList or LinkedList
	private int numCards;
	

	/**
	 * 
	 */
	public Pile()
		{
		// TODO Auto-generated constructor stub
		cards = new LinkedList<>();
		numCards = 0;
		
		}	// end constructor

	/**
	 *	Shuffles the order of the Pile
	 */
	public void shuffle() {

		Collections.shuffle(cards);

	} // end shuffle()

	/**
	 *	Sorts the pile
	 */
	public void sort() {

		Collections.sort(cards);

	} // end sort()

	/**
	 * Add a new card to the pile
	 */
	public void add(Card newCard) {

		cards.add(newCard);
		numCards++;

	} // end add()

	/**
	 *	Removes a card from the pile
	 * @return Card removed from the deck
	 */
	public Card remove() {
		if (!isEmpty()) {
			Card output;
			output = cards.remove(0);
			numCards--;
			return output;
		} else {
			throw new EmptyStackException();
		}

	} // end remove()

	public Card remove(int index) {
		if (!isEmpty()) {
			Card output;
			output = cards.remove(index);
			numCards--;
			return output;
		} else {
			throw new EmptyStackException();
		}

	} // end remove()

	public boolean isEmpty() {
		return numCards == 0;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
		{
		return null ;
		
		}	// end toString()


	/**
	 * @param args
	 */
	public static void main( String[] args )
		{
		// TODO Auto-generated method stub

		}	// end main()

	}	// end class Pile
