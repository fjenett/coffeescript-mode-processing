###
Pie Chart    
By Ira Greenberg 

Uses the arc() function to generate a pie chart from the data
stored in an array. 
###

setup: ->
    
    size 640, 360
    
    background 100
    noStroke()
    
    @diameter = min( width, height ) * 0.75
    @angles = [ 30, 10, 45, 35, 60, 38, 75, 67 ]
    @lastAngle = 0
    
    noLoop()     # Run once and stop

draw: ->
    
    for i in [0..@angles.length]
        
        fill @angles[i] * 3.0
        arc( width/2, height/2, @diameter, @diameter, @lastAngle, @lastAngle + radians( @angles[i] ) )
        @lastAngle += radians @angles[i]

