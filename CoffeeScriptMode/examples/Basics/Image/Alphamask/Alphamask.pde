###
Alpha Mask. 

Loads a "mask" for an image to specify the transparency 
in different parts of the image. The two images are blended
together using the mask() method of PImage. 
###

setup: ->
    
    size 640, 360
    @imgMask = loadImage "mask.jpg"
    @img = loadImage "moonwalk.jpg"
    imageMode CENTER

draw: ->
    
    background 0, 102, 153
    
    if @img.loaded and @imgMask != null and @imgMask.loaded
        @img.mask @imgMask
    
    image @img, width/2, height/2
    image @img, mouseX, mouseY
