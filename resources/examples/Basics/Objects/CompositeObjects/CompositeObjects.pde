###
Composite Objects

An object can include several other objects. Creating such composite objects 
is a good way to use the principles of modularity and build higher levels of 
abstraction within a program.
###


setup: ->
    
    @size 640, 360
    @er1 = new EggRing this, @width*0.45, @height*0.5, 0.1, 120
    @er2 = new EggRing this, @width*0.65, @height*0.8, 0.05, 180


draw: ->
    
    @background 0
    @er1.transmit()
    @er2.transmit()
