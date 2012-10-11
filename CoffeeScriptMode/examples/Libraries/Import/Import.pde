###
Make sure to install library Guido first: https://github.com/fjenett/Guido

Just like libraries in JavaScript mode they can as well be used in CoffeeScript mode.

CoffeeScript has no "import" statement, so they are written as comments to not confuse it:
###

#import de.bezier.guido.*

setup: ->
    
    size 200, 200
    
    Interactive.make this
    @mybutton = new MyButton( 20, height - 40, width - 40, 20 )
    Interactive.add @mybutton

draw: ->
    background 255

    fill if @mybutton.on then 220 else 100
    noStroke()

    ellipse width/2, (height-40)/2, height*0.6, height*0.6
    
    fill 100
    ellipse width/2 - width/7, (height-40)/2 - 20, 15, 15
    ellipse width/2 + width/7, (height-40)/2 - 20, 15, 15
    rect width/3, height/2, width/3, 10

class MyButton

    constructor: (@x, @y, @width, @height) ->
        @on = @hover = no
    
    mouseEntered: ->
        @hover = yes
     
    mouseExited: ->
        @hover = no
    
    mousePressed: ->
        @on = !@on
    
    draw: (p) ->
        
        p.fill if @hover then 200 else 100
        p.rect( @x, @y, @width, @height )
        
        p.fill if @hover then 0 else 255
        p.textAlign p.CENTER
        p.text 'Click me', @x+@width/2, @y+@height-6


