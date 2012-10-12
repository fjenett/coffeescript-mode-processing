
class Ring
    
    # x, y // X-coordinate, y-coordinate
    # diameter // Diameter of the ring
    # on = false // Turns the display on and off
    
    start: ( @x, @y ) ->
        
        @on = true
        @diameter = 1
    
    
    grow: ->
        
        if ( @on is yes )
            
            @diameter += 0.5
            @diameter = 0.0 if ( @diameter > width*2 )
       
    
    display: ->
        
        if ( @on is yes )
            
            noFill()
            strokeWeight 4
            stroke 155, 153
            ellipse @x, @y, @diameter, @diameter
