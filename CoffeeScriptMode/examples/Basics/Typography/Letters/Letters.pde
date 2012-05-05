###
Letters. 

Draws letters to the screen. This requires loading a font, 
setting the font, and then drawing the letters.
###

# The next line is needed if running in JavaScript Mode with Processing.js
# @pjs font="Georgia.ttf"

setup: ->
    
    @size 640, 360
    @background 0

    # Create the font
    @textFont @createFont( "Georgia", 24 )
    @textAlign @CENTER, @CENTER
 

draw: ->
    
    @background 0

    # Set the left and top margin
    margin = 10
    @translate margin*4, margin*4

    gap = 46
    counter = 35
    
    for y in [0...(@height-gap)] by gap
        
        for x in [0...(@width-gap)] by gap
            
            letter = new @Character(counter).toString()
            
            @fill if (letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U') then 255 else 102
            
            # Draw the letter to the screen
            @text letter, x, y
            
            # Increment the counter
            counter++
            
    


