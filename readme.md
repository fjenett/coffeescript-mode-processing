**CoffeeScript mode for Processing 2.0**

This is a first attempt at a "contributed mode" which is a 
system that is going to be part of Processing 2.0

[CoffeeScript](http://coffeescript.org/) was created by [Jeremy Ashkenas](https://github.com/jashkenas), inventor of [ruby-processing](https://github.com/jashkenas/ruby-processing)

***Usage***

Download Processing 2.0a5 or later:
http://code.google.com/p/processing/downloads/list

Download this latest zip:
https://github.com/fjenett/coffeescript-mode-processing/zipball/latest

Find your sketchbook folder and make a new sub-folder called "modes" if not already there

Unzip latest and from inside that folder move "CoffeeScriptMode" to "modes", like so:

    .. path-to ../Processing/
        modes/
            CoffeeScriptMode/
                .. etc ..
		libraries/
		.. sketches ..

Start Processing 2.0a5+

... in the mode menu (to the right in the editor window) you now should see "CoffeeScript".

A simple sketch looks like this:

	setup: ->
		@size 200, 200

	draw: ->
		@background 255
	
		@fill 0
		@stroke 120
	
		@rect 20, 20, @width/2, @height/2


***How does it work?***

CoffeeScript mode is based on JavaScript mode in the soon to be released Processing 2.0 version. "Run" starts a server bound to an export folder inside your sketchs folder. It serves a generated html file containing your CS code along with Processing.js, CoffeeScript.js, jQuery and all other files to include from your sketch folder. The html file contains a short snippet of JS code that passes your CS code to the CS compiler to be converted to JS. A blank Processing.js sketch is generated and the generated JS code is mixed in with it through jQuery.extend(). Voilà ... a nice little soup of frameworks.

***Status***

Contributed modes are not officially introduced in Processing yet and so things might change in the future. Consider this experimental.

This is rather a speed project, no proper syntax coloring, no help, no catching errors before export. 
