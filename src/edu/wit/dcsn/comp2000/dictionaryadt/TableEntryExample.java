/*
 * Dave Rosenberg
 * Comp 2000 - Data Structures
 * Lab: Dictionary ADT (Hash Table)
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

package edu.wit.dcsn.comp2000.dictionaryadt;


/**
 * Contains a sample inner class TableEntry - primarily to demonstrate how to
 * implement equals()
 * 
 * <p>
 * WARNING:<br>
 * The Java Class Library's LinkedList class uses K's equals() rather than
 * TableEntry's equals(). This means that the handling of both
 * aTableEntry.equals(anotherTableEntry) and aTableEntry.equals(aKey) are both
 * supported but our equals() will only get called if we give LinkedList's
 * contains() or indexOf() a TableEntry as the parameter - calling them with a
 * key as the parameter always fails to match.
 * 
 * <p>
 * To use a TableEntry as the parameter to LinkedList methods, instantiate a
 * TableEntry with the key and specify null for the value:
 * 
 * <pre>
 *     bucket = hashTable[ hashIndex ];
 *     if( bucket.contains( new TableEntry( key, null ) )
 *          {
 *          // the key is in the bucket
 *          }
 * </pre>
 * 
 * @author David M Rosenberg
 * @version 1.0.0 Example TableEntry inner class - from Dictionary
 *          implementation
 * 
 * @param <K>
 *        the type for keys
 * @param <V>
 *        the type for corresponding values
 */
public class TableEntryExample<K, V>
	{
	/**
	 * Utility construct to represent key/value pairs.
	 */
	private class TableEntry
    	{
    	/*
    	 * instance variables
    	 */
		private final K	key ;
    	private V		value ;
    	
    	
    	/*
    	 * constructors
    	 */
    	
    	/**
    	 * Set up the TableEntry - note that the key cannot be changed.
    	 * 
    	 * @param theKey		setting for the key (immutable)
    	 * @param initialValue	initial corresponding value
    	 */
    	private TableEntry( K theKey, V initialValue )
        	{
        	this.key					= theKey ;
        	this.value					= initialValue ;
        	}	// end 2-arg constructor
    	
    	
    	/*
    	 * public API methods
    	 */
    	
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		@SuppressWarnings( "unchecked" )
		public boolean equals( Object otherObject )
			{
			// if same instance - consider it a match
			if ( this == otherObject )
				{
				return true ;
				}
			
			// if nothing to compare to - no match
			if ( otherObject == null )
				{
				return false ;
				}
			
			// WARNING: Must search with a TableEntry rather than the key
			// because LinkedList uses the key's class' equals() method, not the
			// element's!!! So, for now, the key handling portion of this method 
			// will not execute
			K otherKey ;
			if ( otherObject.getClass().equals( this.getClass() ) )
				{	// the parameter is a TableEntry - get the key from it
				otherKey				= ( ( TableEntry ) otherObject ).key ;
				}
			else if ( otherObject.getClass().equals( this.key.getClass() ) )
				{	// the parameter is a key - use it
				otherKey				= ( K ) otherObject ;
				}
			else	// the parameter is not a supported type - so no match
				{
				return false ;
				}

			// finally, we can compare this TableEntry's key and the parameter
			// Note: key should never be null - if it is, allow the
			// NullPointerException
			return key.equals( otherKey ) ;
			}	// end equals()


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
			{
			return "<" + key + " : " + value + ">" ;
			}	// end toString()
		
    	}	// end inner class TableEntry

	}	// end class TableEntryExample
