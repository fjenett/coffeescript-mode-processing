###
Easing. 

Move the mouse across the screen and the symbol will follow.    
Between drawing each frame of the animation, the program
calculates the difference between the position of the 
symbol and the cursor. If the distance is larger than
1 pixel, the symbol moves part of the distance (0.05) from its
current position toward the cursor. 
###

setup: ->
    
    size 640, 360 
    noStroke()
    
    @x = 0
    @y = 0
    @easing = 0.05

draw: ->
    
    background 51
    
    @targetX = mouseX
    dx = @targetX - @x
    @x += ( dx * @easing ) if ( abs(dx) > 1 )
    
    @targetY = mouseY
    dy = @targetY - @y
    @y += ( dy * @easing ) if ( abs(dy) > 1 )
    
    ellipse @x, @y, 66, 66
