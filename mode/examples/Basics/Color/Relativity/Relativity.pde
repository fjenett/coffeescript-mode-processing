###
Relativity. 

Each color is perceived in relation to other colors. The top and bottom 
bars each contain the same component colors, but a different display order 
causes individual colors to appear differently. 
###

setup: ->
    @size 640, 360
    @noStroke()
    @a = @color 165, 167, 20
    @b = @color 77, 86, 59
    @c = @color 42, 106, 105
    @d = @color 165, 89, 20
    @e = @color 146, 150, 127
    
    @noLoop()

draw: ->
    @drawBand @a, @b, @c, @d, @e, 0, @width/128
    @drawBand @c, @a, @d, @b, @e, @height/2, @width/128


drawBand: ( v, w, x, y, z, ypos, barWidth ) ->
    num = 5
    colorOrder = [ v, w, x, y, z ]
    for i in [0..@width-1] by (barWidth*num)
        for j in [0..num]
            @fill colorOrder[j]
            @rect i+j*barWidth, ypos, barWidth, @height/2


