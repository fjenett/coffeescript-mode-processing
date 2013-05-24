###
This example quickly shows you the use of loop, the CoffeeScript control structure and
noLoop() and doLoop() which control playback. Note that "loop()" from Processing API is 
"doLoop()" here in CoffeeScript mode as "loop" is a reserved keyword!
###

setup: ->
    size 200, 200
    noLoop()

draw: ->
    background( frameCount % 255 )
    
    [x, y] = [20, 20]
    loop
        fill x
        rect x, y, 20, 20
        x += 10
        y += 14
        break unless y < (height-28)

keyPressed: ->
    doLoop()

