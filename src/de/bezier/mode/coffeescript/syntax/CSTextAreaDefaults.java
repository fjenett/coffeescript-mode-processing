package de.bezier.mode.coffeescript.syntax;

import processing.app.syntax.*;
import processing.app.*;

public class CSTextAreaDefaults extends PdeTextAreaDefaults
{
	public CSTextAreaDefaults ( Mode theme )
	{
		super( theme );
		
		document = new CSSyntaxDocument();
	}
}