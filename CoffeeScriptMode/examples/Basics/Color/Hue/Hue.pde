###
Hue. 

Hue is the color reflected from or transmitted through an object 
and is typically referred to as the name of the color (red, blue, yellow, etc.) 
Move the cursor vertically over each bar to alter its hue. 
###

setup: ->
    @size 640, 360
    @colorMode @HSB, @height, @height, @height
    @noStroke()
    @background 0
    
    @barWidth = 20
    @lastBar = -1

draw: ->
    whichBar = @mouseX / @barWidth
    
    if ( whichBar != @lastBar )
        barX = whichBar * @barWidth
        @fill @mouseY, @height, @height
        @rect barX, 0, @barWidth, @height
        @lastBar = whichBar
   

