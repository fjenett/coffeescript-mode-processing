###
Mixture Grid    
modified from an example by Simon Greenwold. 

Display a 2D grid of boxes with three different kinds of lights. 
###

setup: ->
    
    @size 640, 360, @P3D
    @noStroke()


draw: ->
    
    @defineLights()
    @background 0
    
    for x in [0..@width] by 60
        for y in [0..@height] by 60
            @pushMatrix()
            @translate x, y
            @rotateY @map @mouseX, 0, @width,  0, @PI
            @rotateX @map @mouseY, 0, @height, 0, @PI
            @box 90
            @popMatrix()


defineLights: ->
    
    # Orange point light on the right
    @pointLight 150, 100,  0,  # Color
                200, -150, 0   # Position

    # Blue directional light from the left
    @directionalLight 0, 102, 255,  # Color
                      1, 0,   0     # The x-, y-, z-axis direction

    # Yellow spotlight from the front
    @spotLight 255, 255, 109,    # Color
               0, 40, 200,       # Position
               0, -0.5, -0.5,    # Direction
               @PI / 2, 2        # Angle, concentration

