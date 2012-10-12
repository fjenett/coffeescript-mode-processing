###
Noise3D. 

Using 3D noise to create simple animated texture. 
Here, the third dimension ('z') is treated as time. 
###

setup: ->
    
    size 640, 360
    frameRate 30
    
    @increment = 0.01
    
    # The noise function's 3rd argument, a global variable that increments once per cycle
    @zoff = 0.0
    
    # We will increment zoff differently than xoff and yoff
    @zincrement = 0.02 


draw: ->
    
    # Optional: adjust noise detail here
    # noiseDetail(8,0.65f)
    
    xoff = 0.0 # Start xoff at 0
    
    # For every x,y coordinate in a 2D space, calculate a noise value and produce a brightness value
    for x in [0...width]
        
        xoff += @increment     # Increment xoff 
        yoff = 0.0              # For every xoff, start yoff at 0
        
        for y in [0...height]
            
            yoff += @increment # Increment yoff
            
            # Calculate noise and scale by 255
            bright = noise( xoff, yoff, @zoff ) * 255

            # Try using this line instead
            #bright = random 0, 255 
            
            # Set each pixel onscreen to a grayscale value
            #stroke bright
            #point x, y
            set x, y, color( bright )
    
    @zoff += @zincrement # Increment zoff




