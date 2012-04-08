
setup: ->
    
    @size 200, 200

draw: ->
    
    @background 0
    
    # Eat lunch.
    @text food, 20, 30+y*20 for food, y in ['toast', 'cheese', 'wine']
    
    # Fine five course dining.
    courses = ['greens', 'caviar', 'truffles', 'roast', 'cake']
    menu i + 1, dish for dish, i in courses
    
    # Health conscious meal.
    foods = ['broccoli', 'spinach', 'chocolate']
    eat food for food in foods when food isnt 'chocolate'

drawText: (t) ->
    @x = 20 if not @x else 
