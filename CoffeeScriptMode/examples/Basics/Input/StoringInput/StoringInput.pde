###
Storing Input. 

Move the mouse across the screen to change the position
of the circles. The positions of the mouse are recorded
into an array and played back every frame. Between each
frame, the newest value are added to the end of each array
and the oldest value is deleted. 
###


setup: ->
    
    size 640, 360
    noStroke()
    fill 255, 153
    
    @num = 60
    @mx = new Array(@num)
    @my = new Array(@num)


draw: ->
    
    background 51
    
    # Cycle through the array, using a different entry on each frame. 
    # Using modulo (%) like this is faster than moving all the values over.
    which = frameCount % @num
    @mx[which] = mouseX
    @my[which] = mouseY
    
    for i in [0..@num]
        # which+1 is the smallest (the oldest in the array)
        index = (which+1 + i) % @num
        ellipse @mx[index], @my[index], i, i
    
