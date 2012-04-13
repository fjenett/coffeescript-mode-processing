###
 * Mouse Signals. 
 * 
 * Move and click the mouse to generate signals. 
 * The top row is the signal from "mouseX", 
 * the middle row is the signal from "mouseY",
 * and the bottom row is the signal from "mousePressed". 
###


setup: ->
    
    @size 640, 360
    
    @xvals = new Array(@width)
    @yvals = new Array(@width)
    @bvals = new Array(@width)
    
    @arrayindex = 0


draw: ->
    
    @background 102
    
    for i in [1..@width]
        @xvals[i-1] = @xvals[i] 
        @yvals[i-1] = @yvals[i]
        @bvals[i-1] = @bvals[i]
    
    # Add the new values to the end of the array 
    @xvals[@width-1] = @mouseX 
    @yvals[@width-1] = @mouseY
    
    @bvals[@width-1] = if ( @mousePressed ) then 0 else 255
    
    @fill(255)
    @noStroke()
    @rect(0, @height/3, @width, @height/3+1)

    for i in [1..@width]
        @stroke(255)
        @point(i, @xvals[i]/3)
        @stroke(0)
        @point(i, @height/3+@yvals[i]/3)
        @stroke(255)
        @line(i, 2*@height/3+@bvals[i]/3, i, (2*@height/3+@bvals[i-1]/3))
    
