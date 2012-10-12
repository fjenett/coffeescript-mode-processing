###
Rotate Push Pop. 

The push() and pop() functions allow for more control over transformations.
The push function saves the current coordinate system to the stack 
and pop() restores the prior coordinate system. 
###

setup: ->
    
    size 640, 360, P3D
    noStroke()    
    
    @a = 0                       # Angle of rotation
    @offset = PI/24.0           # Angle offset between boxes
    @num = 12                    # Number of boxes
    @colors = new Array(@num)    # Colors of each box
    @safecolor = 0
    
    for i in [0...@num]
        
        @colors[i] = color( 255 * (i+1)/@num )
    
    lights()


draw: ->
    
    background 0, 0, 26
    translate width/2, height/2
    @a += 0.01
    
    for i in [0...@num]
        
        pushMatrix()
        fill @colors[i]
        rotateY @a   + @offset*i
        rotateX @a/2 + @offset*i
        box 200
        popMatrix()
    
 
