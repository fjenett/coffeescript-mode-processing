
class EggRing
    
    constructor: ( @x, @y, @t, @sp ) ->
        
        @ovoid = new Egg @x, @y, @t, @sp
        @circle = new Ring()
        @circle.start @x, @y - @sp/2
    
    
    transmit: ->
        
        @ovoid.wobble()
        @ovoid.display()
        @circle.grow()
        @circle.display()
        circle.on = yes if @circle.on is false
        
