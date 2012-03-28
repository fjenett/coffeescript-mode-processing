###
Move Eye. 
by Simon Greenwold.

The camera lifts up (controlled by mouseY) while looking at the same point.
###

setup: ->
    @size 640, 360, @P3D
    
draw: ->
    @lights()
    @background 0
    
    # Change height of the camera with mouseY
    @camera 30.0, @mouseY, 220.0,    # eyeX, eyeY, eyeZ
            0.0, 0.0, 0.0,           # centerX, centerY, centerZ
            0.0, 1.0, 0.1            # upX, upY, upZ
            
    @noStroke()
    @box 90
    @stroke 255
    @fill 204
    
    @line -100, 0, 0, 100, 0, 0
    @line 0, -100, 0, 0, 100, 0
    @line 0, 0, -100, 0, 0, 100
    
