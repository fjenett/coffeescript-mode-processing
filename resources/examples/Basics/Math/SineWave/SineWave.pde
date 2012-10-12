###
Sine Wave
by Daniel Shiffman.    

Render a simple sine wave. 
###


setup: ->
    
    size 640, 360
    
    @xspacing = 16    # How far apart should each horizontal location be spaced
    @w = width+16    # Width of entire wave
    
    @theta = 0.0      # Start angle at 0
    @amplitude = 75.0 # Height of wave
    @period = 500.0   # How many pixels before the wave repeats
    
    @dx = (TWO_PI / @period) * @xspacing  # Value for incrementing X, a function of period and xspacing
    @yvalues = new Array @w/@xspacing      # Using an array to store height values for the wave


draw: ->
    
    background 0
    @calcWave()
    @renderWave()


calcWave: ->
    
    # Increment theta (try different values for 'angular velocity' here
    @theta += 0.02

    # For every x value, calculate a y value with sine function
    x = @theta
    for i in [0...@yvalues.length]
        
        @yvalues[i] = sin(x) * @amplitude
        x += @dx

renderWave: ->
    
    noStroke()
    fill 255
    
    # A simple way to draw the wave with an ellipse at each location
    for x in [0...@yvalues.length]
        
        ellipse x * @xspacing, height/2+@yvalues[x], 16, 16


