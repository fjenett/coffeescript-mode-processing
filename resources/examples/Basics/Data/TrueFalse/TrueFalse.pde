###
True/False. 

A Boolean variable has only two possible values: true or false.

In CoffeeScript these can have the names:
true,  on,  yes
false, off, no

It is common to use Booleans with control statements to 
determine the flow of a program. In this example, when the
boolean value "x" is true, vertical black lines are drawn and when
the boolean value "x" is false, horizontal gray lines are drawn. 
###

setup: ->
    
    size 640, 360
    background 0
    stroke 255
    
    b = false
    
    d = 20
    middle = width/2
    
    for i in [d..width] by d
        
        b = if ( i < middle ) then yes else no
        
        if ( b )
            # Vertical line
            line i, d, i, height-d
  
        unless ( b )
            # Horizontal line
            line middle, i - middle + d, width-d, i - middle + d
 
