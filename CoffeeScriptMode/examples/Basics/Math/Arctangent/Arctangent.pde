###
Arctangent. 

Move the mouse to change the direction of the eyes. 
The atan2() function computes the angle from each eye 
to the cursor. 
###
 
setup: ->
    
    @size 640, 360
    @noStroke()
    
    @e1 = new Eye this, 250, 16, 120
    @e2 = new Eye this, 164, 185, 80    
    @e3 = new Eye this, 420, 230, 220


draw: ->
    
    @background(102)
    
    for e in [@e1,@e2,@e3]
        e.update @mouseX, @mouseY
        e.display()


class Eye
    
    constructor: ( @p5, @x, @y, @size ) ->
        
        @angle = 0.0
        
    
    update: ( mx, my ) ->
        
        @angle = @p5.atan2 my-@y, mx-@x
    
    
    display: ->
        
        @p5.pushMatrix()
        @p5.translate @x, @y
        @p5.fill 255
        @p5.ellipse 0, 0, @size, @size
        @p5.rotate @angle
        @p5.fill 153, 204, 0
        @p5.ellipse @size/4, 0, @size/2, @size/2
        @p5.popMatrix()
    

