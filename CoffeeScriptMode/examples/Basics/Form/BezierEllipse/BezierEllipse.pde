###
Bezier Ellipse    
By Ira Greenberg 

Generates an ellipse using bezier() and
trig functions. Approximately every 1/2 
second a new ellipse is plotted using 
random values for control/anchor points.
###

setup: ->
    
    @size 640, 360
    
    # global variable-points in ellipse
    @pts = 4

    @controlPtCol = 0x222222
    @anchorPtCol = 0xBBBBBB
    
    @setEllipse @pts, 65, 65
    
    @frameRate 1


draw: ->
    
    @background 145
    @drawEllipse()
    @setEllipse( parseInt( @random(3, 12) ), @random(-100, 150), @random(-100, 150) )

# Draw ellipse with anchor/control points
drawEllipse: ->
    
    @strokeWeight 1.125
    @stroke 255
    @noFill()
    
    # Create ellipse
    for i in [0..@pts]
        
        if ( i == @pts-1 )
            @bezier( @px[i], @py[i], @cx[i], @cy[i], @cx2[i], @cy2[i], @px[0], @py[0] )
        else
            @bezier( @px[i], @py[i], @cx[i], @cy[i], @cx2[i], @cy2[i], @px[i+1], @py[i+1] )
    
    @strokeWeight .75
    @stroke 0
    @rectMode @CENTER

    # Control handles and tangent lines
    for i in [0..@pts]
        
        if ( i == @pts-1 ) # Last loop iteration-close path
            @line @px[0], @py[0], @cx2[i], @cy2[i]
        
        if ( i > 0 )
            @line @px[i], @py[i], @cx2[i-1], @cy2[i-1]
        
        @line @px[i], @py[i], @cx[i], @cy[i]

    for i in [0..@pts]
        
        @fill @controlPtCol
        @noStroke()
        
        # Control handles
        @ellipse @cx[i], @cy[i], 4, 4
        @ellipse @cx2[i], @cy2[i], 4, 4

        @fill @anchorPtCol
        @stroke 0
        
        # Anchor points
        @rect @px[i], @py[i], 5, 5


# Fill arrays with ellipse coordinate data
setEllipse: (points, radius, controlRadius) ->
    
    @pts = points
    
    # arrays to hold ellipse coordinate data
    @px = new Array(points)
    @py = new Array(points)
    @cx = new Array(points)
    @cy = new Array(points)
    @cx2 = new Array(points)
    @cy2 = new Array(points)
    
    angle = 360.0/points
    controlAngle1 = angle/3.0
    controlAngle2 = controlAngle1*2.0
    
    for i in [0..points]
        
        @px[i] =  @width/2 +  @cos( @radians angle ) * radius
        @py[i] =  @height/2 + @sin( @radians angle ) * radius
        @cx[i] =  @width/2 +  @cos( @radians angle+controlAngle1 ) * controlRadius/@cos( @radians controlAngle1 )
        @cy[i] =  @height/2 + @sin( @radians angle+controlAngle1 ) * controlRadius/@cos( @radians controlAngle1 )
        @cx2[i] = @width/2 +  @cos( @radians angle+controlAngle2 ) * controlRadius/@cos( @radians controlAngle1 )
        @cy2[i] = @height/2 + @sin( @radians angle+controlAngle2 ) * controlRadius/@cos( @radians controlAngle1 )

        # Increment angle so trig functions keep chugging along
        angle += 360.0/points

