class EggRing
    
    constructor: ( @p5, @x, @y, @t, @sp ) ->
        
        @ovoid = new Egg @p5, @x, @y, @t, @sp
        @circle = new Ring()
        @circle.start @p5, @x, @y - @sp/2
    
    
    transmit: ->
        
        @ovoid.wobble()
        @ovoid.display()
        @circle.grow()
        @circle.display()
        circle.on = yes if @circle.on is false
        
