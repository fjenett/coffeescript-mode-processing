class Ring
    
    # x, y // X-coordinate, y-coordinate
    # diameter // Diameter of the ring
    # on = false // Turns the display on and off
    
    start: ( @p5, @x, @y ) ->
        
        @on = true
        @diameter = 1
    
    
    grow: ->
        
        if ( @on is yes )
            
            @diameter += 0.5
            @diameter = 0.0 if ( @diameter > @p5.width*2 )
       
    
    display: ->
        
        if ( @on is yes )
            
            @p5.noFill()
            @p5.strokeWeight 4
            @p5.stroke 155, 153
            @p5.ellipse @x, @y, @diameter, @diameter
