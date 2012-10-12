###
Mouse Press. 

Move the mouse to position the shape. 
Press the mouse button to invert the color.

In CoffeeScript the variable "mousePressed" becomes
the function "isMousePressed()" which returns the
exact same value.
###

setup: ->
    
    size 640, 360
    fill 126
    background 102

draw: ->
    
    if @isMousePressed()
        stroke 255
    else
        stroke 0
    
    line mouseX-66, mouseY,    mouseX+66, mouseY
    line mouseX,    mouseY-66, mouseX,    mouseY+66

