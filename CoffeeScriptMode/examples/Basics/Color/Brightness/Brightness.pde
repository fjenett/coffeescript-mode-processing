###
Brightness 
by Rusty Robison. 

Brightness is the relative lightness or darkness of a color.
Move the cursor vertically over each bar to alter its brightness. 
###

setup: ->
    @size 640, 360
    
    @barWidth = 20
    @lastBar = -1
    
    @colorMode @HSB, @width, 100, @width
    @noStroke()
    @background 0

draw: ->
    @whichBar = @mouseX / @barWidth
    if @whichBar != @lastBar
        barX = @whichBar * @barWidth
        @fill barX, 100, @mouseY
        @rect barX, 0, @barWidth, @height
        @astBar = @whichBar
