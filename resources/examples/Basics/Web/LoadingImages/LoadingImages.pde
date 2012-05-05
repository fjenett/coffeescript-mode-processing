###
Loading Images. 

Processing applications can only load images from the network
while running in the Processing environment. 

This example will not run in a web broswer and will only work when 
the computer is connected to the Internet. 
###

setup: ->
    
    @size 640, 360
    @img = @loadImage "http://processing.org/img/processing.gif"
    @noLoop()


draw: ->
    
    @background(0)
    
    if @img != null
       
        for i in [0...5]
            
            @image @img, 0, @img.height * i
        
    


