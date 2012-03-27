
class Module
    
    constructor: ( @xOffset, @yOffset, @x, @y, @speed, @unit ) ->
        @xDirection = 1
        @yDirection = 1
    
    update: ->
        @x += @speed + @xDirection
        
        if @x >= @unit or @x <= 0
            @xDirection *= -1
            @x += @xDirection
            @y += @yDirection
        
        if @y >= @unit or @y <= 0
            @yDirection *= -1
            @y += @yDirection

    draw: (p) ->
        p.fill 255
        p.ellipse @xOffset + @x, @yOffset + @y, 6, 6


