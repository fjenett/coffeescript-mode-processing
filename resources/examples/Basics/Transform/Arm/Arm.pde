###
Arm. 

The angle of each segment is controlled with the mouseX and
mouseY position. The transformations applied to the first segment
are also applied to the second segment because they are inside
the same pushMatrix() and popMatrix() group.
###

setup: ->
    
    size 640, 360
    strokeWeight 30
    stroke 255, 160
    
    @angle1 = 0.0
    @angle2 = 0.0
    @segLength = 100
    @x = width * 0.3
    @y = height * 0.5


draw: ->
    
    background 0
    
    @angle1 = (mouseX/width  - 0.5) * -PI
    @angle2 = (mouseY/height - 0.5) *  PI
    
    pushMatrix()
    @segment @x, @y, @angle1 
    @segment @segLength, 0, @angle2
    popMatrix()


segment: ( x, y, a ) ->
    
    translate x, y
    rotate a
    line 0, 0, @segLength, 0

