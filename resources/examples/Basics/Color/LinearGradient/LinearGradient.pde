###
Simple Linear Gradient 

The lerpColor() function is useful for interpolating
between two colors.
###

setup: ->
    
    size 640, 360
    
    @Y_AXIS = 0
    @X_AXIS = 1
    
    @b1 = color 255
    @b2 = color 0
    @c1 = color 204, 102, 0
    @c2 = color 0, 102, 153
    
    noLoop()

draw: ->
    
    @setGradient 0, 0, width/2, height, @b1, @b2, @X_AXIS
    @setGradient width/2, 0, width/2, height, @b2, @b1, @X_AXIS
    
    @setGradient 50, 90, 540, 80, @c1, @c2, @Y_AXIS
    @setGradient 50, 190, 540, 80, @c1, @c2, @X_AXIS

setGradient: ( x, y, w, h, col1, col2, axis ) ->
    
    noFill()
    
    if ( axis == @Y_AXIS )
        for i in [y..y+h]
            inter = map i, y, y+h, 0, 1
            c = lerpColor col1, col2, inter
            stroke c
            line x, i, x+w, i
     else
         for i in [x..x+w]
             inter = map i, x, x+w, 0, 1
             c = lerpColor col1, col2, inter
             stroke c
             line i, y, i, y+h


