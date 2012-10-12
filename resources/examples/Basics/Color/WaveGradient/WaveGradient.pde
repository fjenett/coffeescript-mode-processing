###
Wave Gradient 
by Ira Greenberg.  

Generate a gradient along a sin() wave.
###

setup: ->
    
    size 640, 360
    background 200
    noLoop()
    
    @ange = 0
    @px = 0
    @py = 0
    @amplitude = 30
    @frequency = 0
    @fillGap = 2.5
    @c
 
draw: ->

    for i in [-75..height+75]
        
        # Reset angle to 0, so waves stack properly
        @angle = 0
        # Increasing frequency causes more gaps
        @frequency += .002
        
        for j in [0..width+75]
            
            @py = i + sin( radians(@angle) ) * @amplitude
            @angle += @frequency
            @c = color( abs( @py-i ) * 255 / @amplitude, 
                         255 - abs( @py-i ) * 255 / @amplitude,
                         j * ( 255 / ( width+50 ) ) )
            # Hack to fill gaps. Raise value of fillGap if you increase frequency
            for filler in [0..@fillGap-1]
                set j-filler, @py-filler, @c
                set j, @py, @c
                set j+filler, @py+filler, @c
           
        

