###
Logical Operators. 

The logical operators for AND (&&) and OR (||) are used to 
combine simple relational statements into more complex expressions.
The NOT (!) operator is used to negate a boolean statement. 
###

setup: ->
    
    size 640, 360
    background 126
    
    test = false
    
    for i in [5..height] by 5
        
        stroke 0
        
        if ( i > 35 and i < 100 )
            
            line width/4, i, width/2, i
            test = false
        
        stroke 76
        
        if ( i <= 35 or i >= 100 )
        
            line width/2, i, width, i
            test = true
        
        if test
            
            stroke 0
            point width/3, i
        
        if not test
            
            stroke 255
            point width/4, i
        
    

