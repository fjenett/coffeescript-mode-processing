###
Redraw. 

The redraw() function makes draw() execute once.    
In this example, draw() is executed once every time 
the mouse is clicked. 
###

# The statements in the setup() function 
# execute once when the program begins
setup: ->
    
    size 640, 360    # Size should be the first statement
    stroke 255       # Set line drawing color to white
    noLoop()
    @y = height * 0.5


# The statements in draw() are executed until the 
# program is stopped. Each statement is executed in 
# sequence and after the last line is read, the first 
# line is executed again.
draw: ->
    
    background(0)     # Set the background to black
    @y = @y - 4 
    @y = height if @y < 0  
    line 0, @y, width, @y


mousePressed: ->
    
    redraw()

