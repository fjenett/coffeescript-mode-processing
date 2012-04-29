###
Random. 

Random numbers create the basis of this image. 
Each time the program is loaded the result is different. 
###

setup: ->
    
    @size 640, 360
    @background 0
    @strokeWeight 20
    @frameRate 2


draw: ->
    
    for i in [0...@width]
        
        r = @random 255
        @stroke r
        @line i, 0, i, @height
