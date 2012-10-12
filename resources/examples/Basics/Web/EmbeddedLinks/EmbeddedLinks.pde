###
Loading URLs. 

Click on the left button to open a different URL in the same window (Only
works online). Click on the right button to open a URL in a new browser window.    
###

setup: ->
    
    size 640, 360
    @overLeftButton = false
    @overRightButton = false

draw: ->
    
    background 204
    
    # Left buttom
    if @overLeftButton
        fill 255 
     else
         noFill()
    
    rect 20, 60, 75, 75
    rect 50, 90, 15, 15
    
    # Right button
    if @overRightButton
        fill 255
     else
        noFill()
    
    rect 105, 60,  75,  75
    line 135, 105, 155, 85
    line 140, 85,  155, 85
    line 155, 85,  155, 100


mousePressed: ->
    
    if @overLeftButton
        @link "http://www.processing.org"
     else if @overRightButton
        @link "http://www.processing.org", "_blank"


mouseMoved: ->
    
    @checkButtons() 

    
mouseDragged: ->
    
    @checkButtons() 


checkButtons: ->
    
    if mouseX > 20 && mouseX < 95 && mouseY > 60 && mouseY < 135
        @overLeftButton = yes
     else if mouseX > 105 && mouseX < 180 && mouseY > 60 && mouseY < 135
        @overRightButton = yes
     else
        @overLeftButton = @overRightButton = no
    





