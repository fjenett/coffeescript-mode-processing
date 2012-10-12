###
Datatype Conversion. 

It is sometimes beneficial to convert a value from one type of 
data to another. Each of the conversion functions converts its parameter 
to an equivalent representation within its datatype.

As CoffeeScript compiles to JavaScript it inherits it's 5 primitive types:
boolean, number, string, undefined, null

JavaScript comes with these conversion / parsing methods:
parseInt
parseFloat

Processing.js has additional conversion methods:
parseBoolean
parseByte
parseChar
parseInt
parseFloat
###

setup: ->
    
    size 640, 360
    background 0
    noStroke
    
    textFont createFont "Georgia", 24
    
    # "char" type in Processing is in fact "string" in CoffeeScript
    
    c = 'A'
    
    # to convert to character code use string.charCodeAt()
    
    cc = c.charCodeAt 0  # 65
    
    # "float" is just type "number"
    
    f = parseFloat cc    # 65.0
    
    # "int" is just "number"
    
    i = parseInt ( f * 1.4 ) # Sets i to 91
    
    # "byte" is just "number"
    
    b = parseByte ( cc / 2 ) # Sets b to 32
    
#    println typeof c
#    println typeof cc
#    println typeof f
#    println typeof i
#    println typeof b
    
    text "The value of variable c is " + c, 50, 70
    text "The value of variable cc is " + cc, 50, 120
    text "The value of variable f is " + f, 50, 170
    text "The value of variable i is " + i, 50, 220
    text "The value of variable b is " + b, 50, 270

