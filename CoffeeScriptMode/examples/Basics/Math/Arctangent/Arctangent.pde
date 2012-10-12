###
Arctangent. 

Move the mouse to change the direction of the eyes. 
The atan2() function computes the angle from each eye 
to the cursor. 
###
 
setup: ->
    
    size 640, 360
    noStroke()
    
    @e1 = new Eye 250, 16, 120
    @e2 = new Eye 164, 185, 80    
    @e3 = new Eye 420, 230, 220


draw: ->
    
    background(102)
    
    for e in [@e1,@e2,@e3]
        e.update mouseX, mouseY
        e.display()


class Eye
    
    constructor: ( @x, @y, @size ) ->
        
        @angle = 0.0
        
    update: ( mx, my ) ->
        
        @angle = atan2 my-@y, mx-@x
    
    display: ->
        
        pushMatrix()
        translate @x, @y
        fill 255
        ellipse 0, 0, @size, @size
        rotate @angle
        fill 153, 204, 0
        ellipse @size/4, 0, @size/2, @size/2
        popMatrix()
    

