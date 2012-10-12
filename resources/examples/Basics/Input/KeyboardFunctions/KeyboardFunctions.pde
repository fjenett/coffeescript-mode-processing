###
Keyboard Functions. 
Modified from code by Martin. 
Original 'Color Typewriter' concept by John Maeda. 

Click on the window to give it focus and press the letter keys to type colors. 
The keyboard function keyPressed() is called whenever
a key is pressed. keyReleased() is another keyboard
function that is called when a key is released.
###


setup: ->
    
    size 640, 360
    noStroke()
    
    @maxHeight = 40
    @minHeight = 20
    @letterHeight = @maxHeight # Height of the letters
    @letterWidth = 20          # Width of the letter

    @x = -@letterWidth         # X position of the letters
    @y = 0                     # Y position of the letters

    @newsletter = false
    @numChars = 26             # There are 26 characters in the alphabet
    @colors = new Array( @numChars )
    
    colorMode RGB, @numChars
    background @numChars/2
    
    # Set a gray value for each key
    for i in [0..@numChars]
        @colors[i] = color(i)        

draw: ->
    
    if ( @newletter )
        
        # Draw the "letter"
        y_pos = 0
        if ( @letterHeight == @maxHeight )
            y_pos = @y
            rect @x, y_pos, @letterWidth, @letterHeight
        else
            y_pos = @y + @minHeight
            rect @x, y_pos, @letterWidth, @letterHeight
            fill @numChars/2
            rect @x, y_pos-@minHeight, @letterWidth, @letterHeight
            
        @newletter = false

keyPressed: ->
    
    a = @charCode 'a'
    z = @charCode 'z'
    A = @charCode 'A'
    Z = @charCode 'Z'
    k = @charCode key
    
    # If the key is between 'A'(65) and 'z'(122)
    if ( k >= A and k <= z )
        keyIndex = 0
        if ( k <= Z )
            keyIndex = k-A
            @letterHeight = @maxHeight
            fill @colors[k-A]
        else
            keyIndex = k-a
            @letterHeight = @minHeight
            fill @colors[k-a]
        
    else
        fill 0
        @letterHeight = 10
    
    @newletter = true
    
    # Update the "letter" position
    @x = ( @x + @letterWidth ) 
    
    # Wrap horizontally
    if ( @x > width - @letterWidth )
        @x = 0
        @y+= @maxHeight
    
    # Wrap vertically
    @y = 0 if( @y > height - @letterHeight ) # reset y to 0

charCode: (c) ->
    # using Processing.js Character class to get the char code
    new Character( c ).code

