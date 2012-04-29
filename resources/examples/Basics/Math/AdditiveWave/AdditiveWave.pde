###
Additive Wave
by Daniel Shiffman. 

Create a more complex wave by adding two waves together. 
###                          

setup: ->
    
    @size 640, 360
    @frameRate 30
    @colorMode @RGB, 255, 255, 255, 100
    
    @xspacing = 8     # How far apart should each horizontal location be spaced
    @w = @width + 16  # Width of entire wave
    @maxwaves = 4     # total # of waves to add together
    @theta = 0.0
    
    @amplitude = []   # Height of wave
    @dx = []          # Value for incrementing X, to be calculated as a function of period and xspacing
    @yvalues = new Array(@w/@xspacing)     # Using an array to store height values for the wave (not entirely necessary)

    for i in [0...@maxwaves]
        @amplitude[i] = @random 10, 30
        period = @random 100, 300  # How many pixels before the wave repeats
        @dx[i] = (@TWO_PI / period) * @xspacing


draw: ->
    
    @background 0
    @calcWave()
    @renderWave()


calcWave: ->
    
    # Increment theta (try different values for 'angular velocity' here
    @theta += 0.02

    # Set all height values to zero
    for i in [0...@yvalues.length]
        @yvalues[i] = 0
 
    # Accumulate wave height values
    for j in [0...@maxwaves]
        
        x = @theta
        
        for i in [0...@yvalues.length]
            
            # Every other wave is cosine instead of sine
            @yvalues[i] += if (j % 2 == 0) then @sin(x)*@amplitude[j] else @cos(x)*@amplitude[j]
            x += @dx[j]

renderWave: ->
    
    # A simple way to draw the wave with an ellipse at each location
    @noStroke()
    @fill 255, 50
    @ellipseMode @CENTER
    
    for x in [0...@yvalues.length]
        
        @ellipse x*@xspacing, @width/2+@yvalues[x], 16, 16


