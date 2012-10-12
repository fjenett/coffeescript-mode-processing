###
Rotate 1. 

Rotating simultaneously in the X and Y axis. 
Transformation functions such as rotate() are additive.
Successively calling rotate(1.0) and rotate(2.0)
is equivalent to calling rotate(3.0). 
###

setup: ->
    
    size 640, 360, P3D
    
    @a = 0.0
    @rSize = width / 6    # rectangle size
    noStroke()
    fill 204, 204


draw: -> 
    
    background 126
    
    @a += 0.005
    @a = 0.0 if @a > TWO_PI
    
    translate width/2, height/2
    
    rotateX @a
    rotateY @a * 2.0
    fill 255
    rect -@rSize, -@rSize, @rSize*2, @rSize*2
    
    rotateX @a * 1.001
    rotateY @a * 2.002
    fill 0
    rect -@rSize, -@rSize, @rSize*2, @rSize*2


