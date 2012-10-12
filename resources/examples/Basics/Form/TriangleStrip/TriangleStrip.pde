###
TRIANGLE_STRIP Mode
by Ira Greenberg. 

Generate a closed ring using the vertex() function and 
beginShape(TRIANGLE_STRIP) mode. The outerRad and innerRad 
variables control ring's radii respectively.
###

setup: ->
    
    size 640, 360
    background 204
    
    @x = width/2
    @y = height/2
    @outerRad = min( width, height) * 0.4
    @innerRad = @outerRad * 0.6

draw: ->
    
    background 204
    
    pts = parseInt( map( mouseX, 0, width, 6, 60 ) )
    rot = 180.0 / pts
    angle = 0
    
    beginShape TRIANGLE_STRIP
    for i in [0..pts]
        px = @x + cos( radians angle ) * @outerRad
        py = @y + sin( radians angle ) * @outerRad
        angle += rot
        vertex px, py
        px = @x + cos( radians angle ) * @innerRad
        py = @y + sin( radians angle ) * @innerRad
        vertex px, py 
        angle += rot
    endShape()


