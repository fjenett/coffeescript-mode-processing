###
Make sure to install library Guido first: https://github.com/fjenett/Guido

Just like libraries in JavaScript mode they can as well be used in CoffeeScript mode.

CoffeeScript has no "import" statement, so they are written as comments to not confuse it:
###

#import de.bezier.guido.*

setup: ->
    @size 200, 200
    
    Interactive.make this
    @mybutton = new MyButton( 20, 20, 20, 20 )
    Interactive.add @mybutton

draw: ->
    @background 255

    @fill if @mybutton.on then 50 else 220
    @stroke 120

    @ellipse @width/2, @height/2, @width/2, @height/2

class MyButton

    constructor: (@x, @y, @width, @height) ->
        @on = false
    
    mousePressed: ->
        @on = !@on
    
    draw: (p)->
        p.fill if @on then 200 else 100
        p.rect( @x, @y, @width, @height )


