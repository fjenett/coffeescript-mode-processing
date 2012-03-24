**CoffeeScript mode for Processing 2.0**

This is a first attempt at a "contributed mode" which is a 
system that is going to be part of Processing 2.0

CoffeeScript was created by "Jeremy Ashkenas":https://github.com/jashkenas, 
inventor of "ruby-processing":https://github.com/jashkenas/ruby-processing

***Usage***

Download Processing 2.0a5 or later:
http://code.google.com/p/processing/downloads/list

Download this mode and rename folder to "CoffeeScriptMode"

Find your sketchbook folder and make a new sub-folder called "modes"

Add "CoffeeScriptMode" to "modes", start Processing 2.0a5+

... in the mode menu (to the left in the editor window) you
now should see "CoffeeScript".

A simple sketch looks like this:

	setup: ->
		@size 200, 200

	draw: ->
		@background 255
	
		@fill 0
		@stroke 120
	
		@rect 20, 20, @width/2, @height/2

