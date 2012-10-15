###
Create Image. 

The createImage() function provides a fresh buffer of pixels to play with.
This example creates an image gradient.
###
 
setup: ->
    
    size(640, 360)    
    @img = createImage(230, 230, ARGB)
    
    for i in [0..@img.pixels.getLength()]
        a = map(i, 0, @img.pixels.getLength(), 255, 0)
        @img.pixels.setPixel i, color(0, 153, 204, a)


draw: ->
    
    background(0)
    image(@img, 90, 80)
    image(@img, mouseX-@img.width/2, mouseY-@img.height/2)
