###
Array 2D. 

Demonstrates the syntax for creating a two-dimensional (2D) array.
Values in a 2D array are accessed through two index values.  
2D arrays are useful for storing images. In this example, each dot 
is colored in relation to its distance from the center of the image. 
###

setup: ->
    @size 640, 360
    @maxDistance = @dist @width/2, @height/2, @width, @height
    @distances = []
    for y in [0..@height-1]
        @distances.push []
        i = @distances.length-1
        for x in [0..@width-1]
            distance = @dist @width/2, @height/2, x, y
            @distances[i].push (distance / @maxDistance * 255)
    
    @spacer = 10
    @noLoop()

draw: ->
    @background 0
    for y in [0..@height-1] by @spacer
        for x in [0..@width-1] by @spacer
            @stroke @distances[y][x]
            @point x + @spacer/2, y - @spacer/2
    
    


