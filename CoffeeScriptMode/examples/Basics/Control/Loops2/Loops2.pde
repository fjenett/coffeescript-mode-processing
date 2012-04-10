###
Loops and Comprehensions

Comprehensions should be able to handle most places where you otherwise would 
use a loop, each/forEach, map, or select/filter, for example: 

    shortNames = (name for name in list when name.length < 5)

If you know the start and end of your loop, or would like to step through in 
fixed-size increments, you can use a range to specify the start and end of 
your comprehension:

    [9..0]

Note how because we are assigning the value of the comprehensions to a 
variable in the example, CoffeeScript is collecting the result of each 
iteration into an array. Sometimes functions end with loops that are 
intended to run only for their side-effects. Be careful that you're not 
accidentally returning the results of the comprehension in these cases, 
by adding a meaningful return value — like "true" — or "null", to the 
bottom of your function.

To step through a range comprehension in fixed-size chunks, use "by", for example:

    evens = (x for x in [0..10] by 2)

Example was adapted from the CoffeeScript reference
###

setup: ->
    
    @size 400, 400

draw: ->

    @background 90
    
    countdown = (num for num in [9..0])
    
    @drawRect v for v in countdown
    
    @noLoop()

drawRect: (c) ->
    
    @fill 50 + c * 20
    @stroke 255 - c * 20
    @rect c*40, c*40, 40, 40
