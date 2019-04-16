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
 * @author Kai Arsenault
 * @version 1.0.0 initial implementation
 *
 */
public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;
	
	/**
	 * 
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	} // end constructor

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Card o) {
		return rank.getPriority() - o.rank.getPriority();
	} // end compareTo()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if(compareTo((Card) o) == 0)
			return true;
		return false;
	} // end equals()

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String string = rank.toString()+suit.toString();
		return string;
	} // end toString()

	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Card card1 = new Card(Rank.ACE, Suit.CLUBS);
		Card card2 = new Card(Rank.JACK, Suit.HEARTS);
		
		System.out.println(card1.compareTo(card2));
		System.out.println(card1.toString() + " " + card2.toString());
	} // end main()

} // end class Card
