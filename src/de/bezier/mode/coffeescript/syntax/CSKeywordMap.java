package de.bezier.mode.coffeescript.syntax;

import processing.app.syntax.KeywordMap;
import processing.app.syntax.Token;
import javax.swing.text.Segment;

/**
 *
 */
public class CSKeywordMap extends KeywordMap
{
	public CSKeywordMap ( boolean ignoreCase )
	{
        super( ignoreCase );
        
		// this( ignoreCase, 52 );
  //       this.ignoreCase = ignoreCase;
	}

	// public CSKeywordMap ( boolean ignoreCase, int mapLength )
	// {
	// 	super( ignoreCase, mapLength );
	// 	this.mapLength = mapLength;
 //        this.ignoreCase = ignoreCase;
 //        map = new Keyword[mapLength];
	// }

	public String getKeyword (Segment text, int offset, int length)
	{
		return new String( map[ getSegmentMapKey(text, offset, length) ].keyword );
	}

    /**
     * Looks up a key.
     * @param text The text segment
     * @param offset The offset of the substring within the text segment
     * @param length The length of the substring
     */
    public byte lookup(Segment text, int offset, int length)
    {
            if(length == 0)
                    return Token.NULL;
            Keyword k = map[getSegmentMapKey(text, offset, length)];
            while(k != null)
            {
                    if(length != k.keyword.length)
                    {
                            k = k.next;
                            continue;
                    }
                    if(regionMatches(ignoreCase,text,offset,
                            k.keyword))
                            return k.id;
                    k = k.next;
            }
            return Token.NULL;
    }

    /**
     * Adds a key-value mapping.
     * @param keyword The key
     * @param id The value
     */
    public void add(String keyword, byte id)
    {
            int key = getStringMapKey(keyword);
            map[key] = new Keyword(keyword.toCharArray(),id,map[key]);
    }

    /**
     * Returns true if the keyword map is set to be case insensitive,
     * false otherwise.
     */
    public boolean getIgnoreCase()
    {
            return ignoreCase;
    }

    /**
     * Sets if the keyword map should be case insensitive.
     * @param ignoreCase True if the keyword map should be case
     * insensitive, false otherwise
     */
    public void setIgnoreCase(boolean ignoreCase)
    {
            this.ignoreCase = ignoreCase;
    }

    // protected members
    protected int mapLength;

    protected int getStringMapKey(String s)
    {
            return (Character.toUpperCase(s.charAt(0)) +
                            Character.toUpperCase(s.charAt(s.length()-1)))
                            % mapLength;
    }

    protected int getSegmentMapKey(Segment s, int off, int len)
    {
            return (Character.toUpperCase(s.array[off]) +
                            Character.toUpperCase(s.array[off + len - 1]))
                            % mapLength;
    }

    // private members
    private static class Keyword
    {
            public Keyword(char[] keyword, byte id, Keyword next)
            {
                    this.keyword = keyword;
                    this.id = id;
                    this.next = next;
            }

            public final char[] keyword;
            public final byte id;
            public final Keyword next;
    }

    private Keyword[] map;
    private boolean ignoreCase;
}