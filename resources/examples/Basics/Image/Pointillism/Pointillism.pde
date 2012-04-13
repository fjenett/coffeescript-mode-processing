###
Pointillism
by Daniel Shiffman. 

Mouse horizontal location controls size of dots. 
Creates a simple pointillist effect using ellipses colored
according to pixels in an image. 
###

setup: ->
    
    @size 640, 360
    @img = @loadImage "moonwalk.jpg"
    @smallPoint = 4;
    @largePoint = 40;
    @imageMode @CENTER
    @noStroke()
    @background 255


draw: ->
    
    pointillize = @map @mouseX, 0, @width, @smallPoint, @largePoint
    x = @parseInt @random @img.width
    y = @parseInt @random @img.height
    pix = @img.get x, y
    @fill pix, 128
    @ellipse x, y, pointillize, pointillize
