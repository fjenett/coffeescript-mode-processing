###
Background Image. 

This example presents the fastest way to load a background image
into Processing. To load an image as the background, it must be
the same width and height as the program.
###

setup: -> 
    
    size 640, 360
    
    # The background image must be the same size as the parameters
    # into the size    method. In this program, the size of the image
    # is 650 x 360 pixels.
    @bg = loadImage "moonwalk.jpg"
    
    @y = 0
    

draw: ->
    
    if @bg.loaded  # NOTE that "loaded" is not part of Processing API!
        
        background @bg
        
        stroke 226, 204, 0
        line 0, @y, width, @y
        
        @y++
        @y = 0 if @y > height 
         
    

