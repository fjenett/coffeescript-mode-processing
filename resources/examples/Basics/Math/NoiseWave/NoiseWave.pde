###
Noise Wave
by Daniel Shiffman.    

Using Perlin Noise to generate a wave-like pattern. 
###
 
setup: ->
    
    @size 640, 360
    @colorMode @RGB, 255, 255, 255, 100
    
    @xspacing = 8     # How far apart should each horizontal location be spaced
    @yoff = 0.0      # 2nd dimension of perlin noise
    @w = @width+16     # Width of entire wave
    @yvalues = new Array(@w/@xspacing) # Using an array to store height values for the wave (not entirely necessary)


draw: ->
    
    @background 0
    @calcWave()
    @renderWave()


calcWave: ->
    
    dx = 0.05
    dy = 0.01
    amplitude = 100.0

    # Increment y ('time')
    @yoff += dy

    #xoff = 0.0   # Option #1
    xoff = @yoff  # Option #2

    for i in [0...@yvalues.length]
        
        # Using 2D noise function
        #@yvalues[i] = (2*@noise(xoff,@yoff)-1)*amplitude  # Option #1
        
        # Using 1D noise function
        @yvalues[i] = (2*@noise(xoff)-1)*amplitude        # Option #2
        xoff+=dx


renderWave: ->
    
    # A simple way to draw the wave with an ellipse at each location
    for x in [0...@yvalues.length]
        
        @noStroke()
        @fill 255, 50
        @ellipseMode @CENTER
        @ellipse x*@xspacing, @width/2+@yvalues[x], 16, 16
    


