###
Mouse Functions. 

Click on the box and drag it across the screen. 
###

setup: ->
    
    size 640, 360
    @xb = width/2.0
    @yb = height/2.0
    @boxSize = 75
    @overBox = false
    @locked = false
    @xOffset = 0.0 
    @yOffset = 0.0 
    rectMode RADIUS

draw: -> 
    
    background 0
    
    # Test if the cursor is over the box 
    if ( mouseX > @xb-@boxSize and 
         mouseX < @xb+@boxSize and 
         mouseY > @yb-@boxSize and 
         mouseY < @yb+@boxSize )
        @overBox = true    
        if ( !@locked )
            stroke 255 
            fill 153
    else
        stroke 153
        fill 153
        @overBox = false
    
    # Draw the box
    rect @xb, @yb, @boxSize, @boxSize

mousePressed: ->
    
    if ( @overBox ) 
        @locked = true 
        fill 255, 255, 255
    else
        @locked = false
    
    @xOffset = mouseX-@xb 
    @yOffset = mouseY-@yb 


mouseDragged: ->
    
    if ( @locked )
        @xb = mouseX-@xOffset 
        @yb = mouseY-@yOffset


mouseReleased: ->
    @locked = false


