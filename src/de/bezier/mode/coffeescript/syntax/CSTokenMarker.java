package de.bezier.mode.coffeescript.syntax;

import processing.app.Preferences;

import processing.app.syntax.PdeKeywords;
import processing.app.syntax.KeywordMap;
import processing.app.syntax.Token;

import javax.swing.text.Segment;

/**
 *	This is a simple tokenizer used for syntax highlighting.
 *
 *	@see <a href="http://code.google.com/p/processing/source/browse/trunk/processing/app/src/processing/app/syntax/TokenMarker.java">TokenMarker.java</a>
 */
public class CSTokenMarker extends PdeKeywords
{
	private KeywordMap keywordColoring;
	private int lastOffset;
  	private int lastKeyword;

	private int tabDepth;
	private boolean inClass;

	public CSTokenMarker ()
	{
		keywordColoring = new KeywordMap( false );
	}
	
	/**
   	 * Add a keyword, and the associated coloring.
     * @param coloring one of KEYWORD1, KEYWORD2, LITERAL1, etc.
     */
	public void addColoring ( String keyword, String coloring ) 
	{
    	if ( keywordColoring == null ) 
		{
    		keywordColoring = new CSKeywordMap( false );
    	}
    	// text will be KEYWORD or LITERAL
    	boolean isKey = (coloring.charAt(0) == 'K');

    	// KEYWORD1 -> 0, KEYWORD2 -> 1, etc
    	int num = coloring.charAt(coloring.length() - 1) - '1';
    	byte id = (byte) ((isKey ? Token.KEYWORD1 : Token.LITERAL1) + num);
		
		//System.out.println( keyword + " " + id );

    	keywordColoring.add( keyword, id );
  	}

	/**
	 *	A coffeenated TokenMarker, only changes comment behaviour for now.
	 */
	public byte markTokensImpl ( byte token, Segment line, int lineIndex ) 
	{
		char[] array = line.array;
		int offset = line.offset;
		lastOffset = offset;
		lastKeyword = offset;
		int mlength = line.count + offset;
		boolean backslash = false;
		
		// inClass = false;
		// tabDepth = 0;
		
		loop: for ( int i = offset; i < mlength; i++ ) 
		{
			int i1 = (i + 1);
			
			char c = array[i];
			if (c == '\\') 
			{
				backslash = !backslash;
			 	continue;
			}
			
			// if ( i == offset ) // line begin?
			// {
			// 	for ( int k = i; k < mlength; k++ )
			// 	{
			// 		char m = array[k];
			// 		if ( m != ' ' ) break;
			// 		tabDepth++;
			// 	}
			// 	tabDepth /= Preferences.getInteger( "editor.tabs.size" );
			// }
			// if ( tabDepth == 0 ) inClass = false;
			
			switch (token) 
			{
			 	case Token.NULL:
			   		switch (c) {
						// hex literals are not supported, sadly
			   			// case '#':
			   			// 		if (backslash)
			   			// 			backslash = false;
			   			// 		break;
			   			case '"':
			     			doKeyword(line, i, c);
			     			if (backslash)
			       				backslash = false;
			     			else {
								addToken(i - lastOffset, token);
								token = Token.LITERAL1;
								lastOffset = lastKeyword = i;
			     			}
			     			break;
			   			case '\'':
			     			doKeyword(line, i, c);
			     			if (backslash)
			       				backslash = false;
			     			else {
								addToken(i - lastOffset, token);
								token = Token.LITERAL2;
								lastOffset = lastKeyword = i;
			     			}
			     			break;
						// TODO: change to token for methods?
			   			/*case ':':
			     			if (lastKeyword == offset) {
			       				if (doKeyword(line, i, c))
			         				break;
			       				backslash = false;
			       				addToken(i1 - lastOffset, Token.LABEL);
			       				lastOffset = lastKeyword = i1;
			     			} else if (doKeyword(line, i, c))
			       				break;
			     			break;*/
			   			case '#':
					    	backslash = false;
					        doKeyword(line, i, c);
					        addToken(i - lastOffset, token);
					        if ( mlength-i > 2 && array[i1] == '#' && array[i+2] == '#' ) 
							{
								token = Token.COMMENT2;
						        addToken(mlength - i, token);
					        }
							else
							{	
								token = Token.COMMENT1;
						        addToken(mlength - i, token);
					            token = Token.NULL;
							}
			              	lastOffset = lastKeyword = mlength;
							i = mlength;
		              		break loop;
			   			default:
			     			backslash = false;
			     			if (!Character.isLetterOrDigit(c) && c != '_')
			       				doKeyword(line, i, c);
			     			break;
					}
			   		break;
			 	case Token.COMMENT1:
				case Token.COMMENT2:
			   		backslash = false;
			   		if ( c == '#' ) 
					{
						addToken(mlength - i, token);
			            token = Token.NULL;
						i = mlength;
			            lastOffset = lastKeyword = mlength;
			        }
				 	break;
				case Token.LITERAL1:
					if (backslash)
				    	backslash = false;
				   	else if (c == '"') {
				     	addToken(i1 - lastOffset, token);
				     	token = Token.NULL;
				     	lastOffset = lastKeyword = i1;
				 	}
					break;
				case Token.LITERAL2:
					if (backslash)
				    	backslash = false;
				   	else if (c == '\'') {
				     	addToken(i1 - lastOffset, Token.LITERAL1);
				     	token = Token.NULL;
				     	lastOffset = lastKeyword = i1;
					}
				   	break;
				default:
					throw new InternalError("Invalid state: " + token);
			}
		}

		if (token == Token.NULL)
			doKeyword(line, mlength, '\0');

		switch (token) 
		{
			case Token.LITERAL1:
			case Token.LITERAL2:
		 		addToken(mlength - lastOffset, Token.INVALID);
		 		token = Token.NULL;
		 		break;
			case Token.KEYWORD2:
		 		addToken(mlength - lastOffset, token);
		 		if (!backslash)
		   			token = Token.NULL;
		 		addToken(mlength - lastOffset, token);
		 		break;
			default:
		 		addToken(mlength - lastOffset, token);
		 		break;
		}
		
		return token;
	}
	
	private boolean doKeyword ( Segment line, int i, char c ) 
	{
    	int i1 = i + 1;

    	int len = i - lastKeyword;
    	byte id = keywordColoring.lookup( line, lastKeyword, len );

		if ( id == 10 && tabDepth == 0 ) id = 7; // fix function names
		
		// if ( len > 0 )
		// {
		// 	char[] sub = new char[len];
		// 	System.arraycopy( line.array, lastKeyword, sub, 0, len );
		// 	String kw = new String( sub );
		// 	
		// 	if ( inClass && tabDepth > 0 && id == 7 )
		// 		id = 0;
		// 	
		// 	if ( !inClass && kw.equals("class") ) 
		// 	{
		// 		inClass = true;
		// 	}
		// 	
		// 	System.out.println( kw + " " + id + " " + tabDepth + " " + (inClass ? "Y" : "N") );
		// }
		
    	if ( id != Token.NULL ) 
		{
      		if (lastKeyword != lastOffset)
        		addToken(lastKeyword - lastOffset, Token.NULL);
      		addToken(len, id);
      		lastOffset = i;
    	}
    	lastKeyword = i1;
    	return false;
	}
}