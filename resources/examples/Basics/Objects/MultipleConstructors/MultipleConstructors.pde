###
Multiple constructors are not possible in JavaScript/CoffeeScript

The trick is to check if and what values are passed into a constructor
by accessing it's "arguments" field. The "arguments" field is an array
of all values passed into a function which a constructor is just a 
special version of.
###

setup: ->
    
    @size 640, 360
    @background 204
    @noLoop()
    
    # Run the constructor without parameters
    @sp1 = new Spot this
    
    # Run the constructor with three parameters
    @sp2 = new Spot this, @width*0.5, @height*0.5, 120


draw: ->
    
    @sp1.drawInto this
    @sp2.drawInto this


class Spot 
    
    constructor: ( @p5, @x, @y, @radius ) ->
        
        # handling if no values are given:
        # "arguments" is an array that holds all parameters 
        # passed into a method
        
        if ( arguments.length == 1 ) # called with only one value
            
            @radius = 40
            @x = @p5.width  * 0.25
            @y = @p5.height * 0.5
    
    
    drawInto: (@p5) ->
        
        @p5.ellipse @x, @y, @radius*2, @radius*2
    
    

