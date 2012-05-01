###
Inheritance

A class can be defined using another class as a foundation. In object-oriented
programming terminology, one class can inherit fi elds and methods from another. 
An object that inherits from another is called a subclass, and the object it 
inherits from is called a superclass. A subclass extends the superclass.
###

setup: ->
    
    @size 640, 360
    @arm   = new SpinArm   this, @width/2, @height/2, 0.01
    @spots = new SpinSpots this, @width/2, @height/2, -0.02, 90.0


draw: ->
    
    @background 204
    @arm.update()
    @arm.display()
    @spots.update()
    @spots.display()


class Spin 
    
    constructor: ( @x, @y, @speed ) ->
        @angle = 0.0
    
    update: -> 
        @angle += @speed
    

class SpinArm extends Spin
    
    constructor: ( @p5, @x, @y, @speed ) ->
        super @x, @y, @speed
    
    display: ->
        @p5.strokeWeight 1
        @p5.stroke 0
        @p5.pushMatrix()
        @p5.translate @x, @y
        @p5.rotate @angle
        @p5.line 0, 0, 165, 0
        @p5.popMatrix()


class SpinSpots extends Spin 
    
    constructor: ( @p5, @x, @y, @speed, @dim ) -> 
        super @x, @y, @speed
    
    display: ->
        @p5.noStroke()
        @p5.pushMatrix()
        @p5.translate @x, @y
        @p5.rotate @angle
        @p5.ellipse -@dim/2, 0, @dim, @dim
        @p5.ellipse  @dim/2, 0, @dim, @dim
        @p5.popMatrix()
    

