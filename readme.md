**CoffeeScript mode for Processing 2.0**

This is a "contributed mode" for Processing 2.0, it allows to write Processing sketches in CoffeeScript

[CoffeeScript](http://coffeescript.org/) was created by [Jeremy Ashkenas](https://github.com/jashkenas), inventor of [ruby-processing](https://github.com/jashkenas/ruby-processing)

*Please note that as Processing.js is not up to date with Processing 2.0 API you should write your code as if for Processing 1.5!*

***Usage***

You need Processing 2.0 for this to run, fetch latest here:
http://processing.org/download/

Download this "latest" zip here:
https://github.com/fjenett/coffeescript-mode-processing/zipball/latest

Find your sketchbook folder and make a new sub-folder called "modes", if not already there

Unzip "latest" and from inside that folder move "CoffeeScriptMode" to "modes", like so:
```
.. path-to ../Processing/
    modes/
        CoffeeScriptMode/
            .. etc ..
	libraries/
	.. other sketches ..
```

Now start Processing 2.0 ..

... in the mode menu (to the right in the editor window) you should now see "CoffeeScript", select it to switch modes.

A simple sketch looks like this:
```coffeescript
setup: ->
    size 200, 200

draw: ->
    background 255
    
    fill 0
    stroke 120
    
    rect 20, 20, width/2, height/2
```

***Some specialties***

**Indentation**

In CoffeeScript [whitespace is meaningful](http://coffeescript.org/#language). Sometimes copy-pasting code or unthoughtfully typing away will result in an exception (normally mentioning "INDENT").

Each sketch stores it's "tabs.size" (how many spaces represent a tab) inside a settings file named sketch.properties. This might differ from what you have set in your editor preferences and if it does you need to either adjust the sketch (including reformatting it's code), or change your editor settings. In any way this will be messy to get right and you will hate me for it. Sorry!

**pixels array**
Beloved "pixels" is an object in CS mode, not an array:
pixels.length 		pixels.getLength()
= pixels[i]			pixels.getPixel(index)
pixels[i] = 		pixels.setPixel(index, color)
...

**Image preloading**
Image preloading does currently not work out of the box, you will need to check if they are loaded:
```coffeescript
img = loadImage "myfunkyimage.jpg"
...
if img.loaded
	doSomethingWithItHere()
```
See Examples > Basic > Image > ...
Or use requestImage().


***How does it work?***

CoffeeScript mode is based on JavaScript mode in the just released Processing 2.0 version.

Pressing "Run" compiles your CoffeeScript code to JavaScript using the standard CS compiler running on [Rhino](https://github.com/mozilla/rhino). Next a server is started and it's address is being opened in your default browser. A generated HTML file along with your compiled CS code, Processing.js and all other files to include is being served from an export folder from inside your sketch's directory. The HTML file contains a short snippet of JS code that creates a blank Processing.js instance and mixes your compiled CS code into it to make it dance. Voilà ... a nice little soup of frameworks.

***Status***

Removed need for '@' upfront Processing API functions, classes, constants and variables. So '@size' becomes 'size', etc ...
(Thanks to [Jeremy Ashkenas](https://github.com/jashkenas) for pointing me to what led to a solution!)

Ideas, suggestions, feedback welcome ...



