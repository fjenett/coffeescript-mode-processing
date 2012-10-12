###
Mouse 2D. 

Moving the mouse changes the position and size of each box. 
###
 
setup: ->
    
    size 640, 360
    noStroke()
    rectMode CENTER

draw: ->
    
    background 51
    fill 255, 204
    rect mouseX, height/2, mouseY/2+10, mouseY/2+10
    fill 255, 204
    inverseX = width-mouseX
    inverseY = height-mouseY
    rect inverseX, height/2, (inverseY/2)+10, (inverseY/2)+10


