class Egg
    
    # x, y // X-coordinate, y-coordinate
    # tilt // Left and right angle offset
    # angle // Used to define the tilt
    # scalar // Height of the egg

    # Constructor
    constructor: ( @p5, @x, @y, @tilt, s) ->
        
        @scalar = s / 100.0
        @angle = 0.0
    
    
    wobble: ->
        
        @tilt = @p5.cos(@angle) / 8
        @angle += 0.1
    
    
    display: ->
        
        @p5.noStroke()
        @p5.fill 255
        @p5.pushMatrix()
        @p5.translate @x, @y
        @p5.rotate @tilt
        @p5.scale @scalar
        @p5.beginShape()
        @p5.vertex 0, -100
        @p5.bezierVertex 25, -100, 40, -65, 40, -40
        @p5.bezierVertex 40, -15, 25, 0, 0, 0
        @p5.bezierVertex -25, 0, -40, -15, -40, -40
        @p5.bezierVertex -40, -65, -25, -100, 0, -100
        @p5.endShape()
        @p5.popMatrix()

