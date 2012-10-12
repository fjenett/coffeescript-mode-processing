###
Milliseconds. 

A millisecond is 1/1000 of a second. 
Processing keeps track of the number of milliseconds a program has run.
By modifying this number with the modulo(%) operator, 
different patterns in time are created.    
###


setup: ->
    
    size 640, 360
    noStroke()
    @s = width/20


draw: ->
    
    for i in [0..@s]
        colorMode RGB, (i+1) * @s * 10
        fill millis() % ((i+1) * @s * 10)
        rect i*@s, 0, @s, height
