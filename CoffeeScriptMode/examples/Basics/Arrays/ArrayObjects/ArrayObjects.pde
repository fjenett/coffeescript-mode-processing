###
Array Objects. 

Demonstrates the syntax for creating an array of custom objects. 
###

setup: ->
    
    size 640, 360
    noStroke()
    
    @unit = 40
    
    wideCount = width / @unit
    highCount = height / @unit
    
    @count = wideCount * highCount
    @mods = []
    
    for y in [0..highCount-1]
        for x in [0..wideCount-1]
            @mods.push new Module( x*@unit, y*@unit, @unit/2, @unit/2, random(0.05, 0.8), @unit )
    
draw: ->
    
    background 0
    
    for m in @mods
        m.update()
        m.draw( @ )
