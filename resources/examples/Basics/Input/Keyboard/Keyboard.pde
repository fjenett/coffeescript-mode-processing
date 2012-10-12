###
Keyboard. 

Click on the image to give it focus and press the letter keys 
to create forms in time and space. Each key has a unique identifying 
number. These numbers can be used to position shapes in space. 
###
     
setup: ->
    
    size 640, 360
    noStroke()
    background 0
    @rectWidth = width/4

draw: ->
    
    # keep draw() here to continue looping while waiting for keys

keyPressed: ->
    
    keyIndex = -1
    
    a = @charCode 'a'
    z = @charCode 'z'
    A = @charCode 'A'
    Z = @charCode 'Z'
    k = @charCode key
    
    if ( k >= A && k <= Z ) 
        keyIndex = k - A
    else if ( k >= a && k <= z ) 
        keyIndex = k - a
    
    if ( keyIndex == -1 )
        # If it's not a letter key, clear the screen
        background 0
    else
        # It's a letter key, fill a rectangle
        fill( millis() % 255 )
        x = map keyIndex, 0, 25, 0, width - @rectWidth
        rect x, 0, @rectWidth, height
    

charCode: ( c ) ->
    
    # using Processing.js Character class to get char code from string
    new Character( c ).code


