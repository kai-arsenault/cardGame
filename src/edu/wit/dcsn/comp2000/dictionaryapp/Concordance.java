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

package edu.wit.dcsn.comp2000.dictionaryapp;

import java.io.File ;
import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.PrintWriter ;
import java.util.HashSet ;
import java.util.Iterator ;
import java.util.Scanner ;

import edu.wit.dcsn.comp2000.dictionaryadt.Dictionary ;
import edu.wit.dcsn.comp2000.dictionaryadt.DictionaryInterface ;
import edu.wit.dcsn.comp2000.dictionaryadt.MetricsLogger ;


/**
 * Generate a concordance from all text files in the data directory.
 * 
 * <p>
 * Note: You must use this class, without modification, in your Comp 2000,
 * Dictionary/Hash Table ADT solution.
 * 
 * @author David M Rosenberg
 * @version 1.0.0 initial version to exercise the Dictionary ADT
 * @version 1.1.0 fix cross-platform issue with data subdirectory and sporadic
 *          failure of Scanner to read text files
 */
public class Concordance
	{

	/**
	 * @param args
	 *        -unused-
	 * @throws FileNotFoundException
	 *         can only happen if a file is deleted or locked by another process
	 *         while executing or the output file can't be created
	 */
	public static void main( String[] args ) throws FileNotFoundException
		{
		// start up the metrics logger
		MetricsLogger.create() ;
		MetricsLogger.write( "Starting Concordance load" ) ;
		MetricsLogger.writeColumnHeaders() ;
		
		
		// we'll process all of the files in the data subdirectory/folder
		File dataDirectory =			new File( "./data" ) ;
		
		// we'll send the output to a file
		PrintWriter concordanceOutput =	new PrintWriter( "concordance.out" ) ;
		
		// the dictionary/hash table
		DictionaryInterface<String, HashSet<Integer>> concordance =
                                		new Dictionary<>() ;		// use for your submission
//                                		new DictionaryHashMap<>() ;	// use for a demo
//                                		new DictionaryTemplate<>() ;// use for a demo
		
		int fileNumber =				0 ;	// the number of the file being processed
		

		// for each file in the data subdirectory
		for( File aFile : dataDirectory.listFiles() )
			{
			fileNumber++ ;					// count this file

			// display info wrt the file number and corresponding name
			System.out.printf( "Processing file %,3d: %s%n",
			                   fileNumber,
			                   aFile.getName()
			                   ) ;
			concordanceOutput.printf( "File %,3d: %s%n",
			                          fileNumber,
			                          aFile.getName()
			                          ) ;
			
			// open the file for input
			Scanner fileInput =			new Scanner( new FileInputStream( aFile ) ) ;
			
			int fileWordCount =			0 ;	// number of words in the current file 
			
			// for each token/word in the input file
			while( fileInput.hasNext() )
				{
				// sanitize the token (trim, convert to lowercase, eliminate all
				// non-alphabetic characters except periods (to keep URIs intact))
				String word = 			fileInput.next().trim().toLowerCase()
													.replaceAll( "[^a-z.]", "" ) ;
				
				// remove trailing period (leave periods within words - probably URIs)
				if( ( word.length() > 0 ) && ( word.charAt( word.length() - 1 ) == '.' ) )
					{
					word =				word.substring( 0, word.length() - 1 ) ;
					}
				
				// if there's anything left, add it to the list of file numbers in the dictionary
				// excludes a 'word' that's only a period
				if( ( word.length() > 0 ) && ( !word.equals( "." ) ) )
					{
					fileWordCount++ ;			// count this word
					
					// if the word is in the dictionary, get its list of file numbers
					// otherwise it's the first time we're seeing it so create a new list
    				HashSet<Integer> fileNumbers =	concordance.contains( word )
    				                 					? concordance.getValue( word )
    				                 					: new HashSet<>() ;
    
    
 					// add this file number to the list
    				// - only adds it once even if seen multiple times
    				fileNumbers.add( fileNumber ) ;
    				
    				// add/update the list of file numbers for this word to the dictionary
    				concordance.add( word, fileNumbers ) ;
					}	// end if() have a word
				}	// end while() words in file
			
			// we hit the end of the input so close the file
			fileInput.close() ;
			
			// display the number of words to the output
			concordanceOutput.printf( "\tfound %,d word%s; now %,d in the dictionary%n",
			                          fileWordCount,
			                          ( fileWordCount == 1
			                        	? ""
			                        	: "s" ),
			                          concordance.getSize()
			                          ) ;
			}	// for() each file
		
		
		// let the dictionary know we're done putting key/value pairs into it
		concordance.doneLoading() ;

		// properly close the metrics logger
		MetricsLogger.write( "\nEnding Concordance load" );
		MetricsLogger.close() ;
		
		// add divider line to the output file
		concordanceOutput.println( "----------" );
		
		// we'll display the concordance to the console in the order words are
		// returned by the key iterator
		Iterator<String> wordIterator =		concordance.getKeyIterator() ;
		while( wordIterator.hasNext() )
			{
			// get the next word
			String word =					wordIterator.next() ;
			
			// write it to the output file
			concordanceOutput.printf( "%-25s: ", word ) ;
			
			boolean firstFile =				true ;	// flag for format control
			for( int inFile : concordance.getValue( word ) )
				{
				// display file numbers as a comma-separated list
				concordanceOutput.printf( "%s%,d",
				                          ( firstFile
    											? ""
    											: ", " ),
				                          inFile ) ;
				
				firstFile =					false ;
				}	// end for() display each word's file numbers
			concordanceOutput.println() ;				// done with this word
			}	// end while() display each word
		
		// done with the output
		concordanceOutput.close() ;
		
		}	// end main()

	}	// end class Concordance
