###
Integers Floats. 

In standard Processing integers and floats are two different kinds of 
numerical data. An integer (more commonly called an int) is a number 
without a decimal point. A float is a floating-point number, which 
means it is a number that has a decimal place. Floats are used when
more precision is needed. 

In CoffeeScript, which is based on JavaScript, there is only "number",
one primitive type for all types of numeric values.
###

setup: ->
    
    @a = 0    # Create a variable "a" of the datatype "number" from an integer value
    @b = 0.0  # Create a variable "b" of the datatype "number" from a float value
    
    size 640, 360
    stroke 255
    frameRate 30
    

draw: ->
    
    background 0
    
    @a = @a + 1
    @b = @b + 0.2
    
    line @a, 0, @a, height/2 
    line @b, height/2, @b, height
    
    @a = 0 if @a > width
    @b = 0 if @b > width
    
    fill 255
    text @a, 20, 30
    text @b, 20, 50


