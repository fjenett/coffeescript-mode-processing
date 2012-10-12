###
Loops and Comprehensions

Most of the loops you'll write in CoffeeScript will be comprehensions 
over arrays, objects, and ranges. Comprehensions replace (and compile into) 
for loops, with optional guard clauses and the value of the current array 
index. Unlike for loops, array comprehensions are expressions, and can be 
returned and assigned.

Example was adapted from the CoffeeScript reference
###

setup: ->
    
    size 200, 200

draw: ->
    
    background 30
    
    fill 255, 255, 140
    @y = 30
    
    # Eat lunch.
    @drawText food for food in ['toast', 'cheese', 'wine']
    
    fill 190, 255, 190
    @y += 16
    
    # Fine five course dining.
    courses = ['greens', 'caviar', 'truffles', 'roast', 'cake']
    text dish, @x=20, @y+=16 for dish, i in courses
    
    fill 140, 255, 255
    @y += 2*16
    
    # Health conscious meal.
    foods = ['broccoli', 'spinach', 'chocolate']
    @drawText food for food in foods when food isnt 'chocolate'
    
    noLoop()

drawText: (t) ->

    @x = 20 if not @x
    text t, @x, @y
    @x += textWidth t+' '
    
    
