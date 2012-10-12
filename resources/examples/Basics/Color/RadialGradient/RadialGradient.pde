###
Radial Gradient. 

Draws are series of concentric circles to create a gradient 
from one color to another.
###

setup: ->
    
    size 640, 360
    @dim = width / 2
    background 0
    colorMode HSB, 360, 100, 100
    noStroke()
    ellipseMode RADIUS
    frameRate 1
    
draw: ->
    
    background 0
    
    for x in [0..width] by @dim
        @drawGradient x, height / 2

drawGradient: ( x, y ) ->
    
    radius = @dim / 2
    h = random 0, 360
    for r in [radius..0] by -1
        fill h, 90, 90
        ellipse x, y, r, r
        h = (h + 1) % 360
 
