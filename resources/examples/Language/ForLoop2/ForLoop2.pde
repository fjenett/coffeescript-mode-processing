

setup: ->
    
    @size 200, 200
    @DIR_UP = 1
    @DIR_RIGHT = 2
    @blockWidth = 20
    
    @noLoop()

draw: ->
    
    @background 255
    
    d = @DIR_UP
    for x in [0..@width] by @blockWidth
        for y in [0..@height] by @blockWidth
            d = if d is @DIR_RIGHT then @DIR_UP else @DIR_RIGHT 
            @drawStripedBlock d, x, y, @blockWidth
    

drawStripedBlock: (dir, x, y, wh) ->
    
    @noStroke()
    @fill 0
    for s in [0..wh] by wh/5
        switch dir
            when @DIR_UP
                @rect x+s, y, wh/10, wh
            when @DIR_RIGHT
                @rect x, y+s, wh, wh/10
